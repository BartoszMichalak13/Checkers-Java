package org.example.decoders;

import org.example.Pionek;
import org.example.decoders.Decoder;

public class DecoderServer implements Decoder {
    boolean isWhiteTurn=true;
    boolean wasBicie=false;

    /////Gracz będzie wysyłał wiadomości: wyglądające tak: "W C 08 09 S" -  "im White, check x - 8, y - 9, stop."
    ////Jeśli jest tura gracza białego, serwer bedzie dalej sprawdzal, jeśli nie, to zignoruje zapytanie.
    ///znajdzie pionek na tym polu, jesli jest pusty to zignoruje zapytanie.
    //jeśli jest na nim pionek to wywoła na nim operacje legalne kafelki i odeśle odpowiedź

    /////Gdy wiadomość bedzie wyglądała "W M 08 09 S" - im white, move active pionek to x08 y09, stop.
    ////serwer sprawdzi, czy te koordynaty są w "legalnych kafelkach", jesli nie to zignoruje zapytanie
    ///jesli są, to uaktywni pionek i wykona ruch
    //potem poleci każdemu klientowi przemalowanie tablicy.

    /////Serwer bedzie wysylal wiadomosci wygladajace tak "W C 07 10 08 10 09 08 09 11 S" -
    ////Sprawdziłem, legalne kafelki to x07 y10 oraz x08 y10......, jesli jestes bialy to te kafelki zaznacz, stop.
    ///"A W P 01 02 W Q 03 02... B Q 10 02 B P 10 02 S" - do wszystkich klientów, Bialy pion na x01, y02... czarna krolowa na 10 02..., stop"
    // to bedzie wysylane po kazdym wykonanym ruchu.

    // "I" - invalid action. Klient odbierze "W C I" znaczy - biały, nieprawidłowa akcja sprawdzania możliwych ruchów

    @Override
    public String decode(String message) {
        String[] commands = message.split(" ");
        String response="";
        boolean whitePlayer;
        if(commands[0]=="W"){
            whitePlayer=true;
            response+="W ";
        }else{
            whitePlayer=false;
            response+="B ";
        }
        int x = Integer.parseInt(commands[2]), y = Integer.parseInt(commands[3]);
        switch (commands[1]){
            case "C":
                response+="C ";
                if(Pionek.getPionekByCords(x,y)!=null) {
                    if (isWhiteTurn == Pionek.getPionekByCords(x, y).isWhite() && isWhiteTurn == whitePlayer) {

                        for(int i = 0; i<Pionek.getPionekByCords(x, y).legalneKafelki().size(); i+=2){
                            response += Pionek.getPionekByCords(x, y).legalneKafelki().get(i).toString();
                            response +=" ";
                            response += Pionek.getPionekByCords(x, y).legalneKafelki().get(i+1).toString();
                            response +=" ";
                        }
                        Pionek.getPionekByCords(x, y).setActive(true);
                        response+="S";
                        return response;
                    }else{
                        response+="I S";
                        return response;
                    }
                }else{
                    response+="I S";
                    return response;
                }
            case "M":
                boolean validMove=false;
                if(isWhiteTurn==whitePlayer){
                    for(int i = 0; i<Pionek.getActivePionek().legalneKafelki().size(); i+=2){

                        if(Pionek.getActivePionek().legalneKafelki().get(i)==x&&
                                Pionek.getActivePionek().legalneKafelki().get(i+1)==y){
                            validMove=true;
                            int amountOfPionki = Pionek.getPionki().size();
                            Pionek.getActivePionek().move(x,y);
                            //zakodowac wysylke kordów pionków
                            response = "A "; //trzeba wyslac kazdemu, nadpisuje zapisanie koloru gracza z zapytaniem.
                            for(Pionek pionek:Pionek.getPionki()){
                                if(pionek.isWhite()){
                                    response+="W ";
                                }else{
                                    response+="B ";
                                }
                                if(pionek.isQueen()){
                                    response+="Q ";
                                }else{
                                    response+="P ";
                                }
                                response+=pionek.getX();
                                response+=" ";
                                response+=pionek.getY();
                                response+=" ";
                            }
                            boolean isWin=true;
                            for(Pionek pionek: Pionek.getPionki()){
                                if(!pionek.legalneKafelki().isEmpty()&&pionek.isWhite()!=isWhiteTurn){
                                    isWin=false;
                                }
                            }
                            if(isWin){
                                if(isWhiteTurn){
                                    response+="A W WIN";
                                }else{
                                    response+="A B WIN";
                                }
                            }

                            if(amountOfPionki-Pionek.getPionki().size()==1&&
                                    !Pionek.bicia(x,y,Pionek.getActivePionek().isWhite(), Pionek.getActivePionek().isQueen()).isEmpty()){
                                response += "HIT ";
                                for(int j=0; j<Pionek.getActivePionek().legalneKafelki().size(); j+=2){
                                    response+=Pionek.getActivePionek().legalneKafelki().get(j).toString();
                                    response+=" ";
                                    response+=Pionek.getActivePionek().legalneKafelki().get(j+1).toString();
                                    response+=" ";
                                }

                            }else{
                                this.isWhiteTurn=!isWhiteTurn;
                            }
                        }
                    }if(!validMove){
                        response +="I S";
                    }
                }else{
                    response+="I S";
                }
        }
        return response;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
}
