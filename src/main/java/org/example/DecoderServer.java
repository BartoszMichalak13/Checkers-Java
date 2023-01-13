package org.example;

public class DecoderServer implements Decoder{
    boolean isWhiteTurn=true;

    /////Gracz będzie wysyłał wiadomości: wyglądające tak: "W C 08 09 S" -  "im White, check x - 8, y - 9, stop."
    ////Jeśli jest tura gracza białego, serwer bedzie dalej sprawdzal, jeśli nie, to zignoruje zapytanie.
    ///znajdzie pionek na tym polu, jesli jest pusty to zignoruje zapytanie.
    //jeśli jest na nim pionek to wywoła na nim operacje legalne kafelki i odeśle odpowiedź

    /////Gdy wiadomość bedzie wyglądała "W M 08 09 S" - im white, move active pionek to x08 y09, stop.
    ////serwer sprawdzi, czy te koordynaty są w "legalnych kafelkach", jesli nie to zignoruje zapytanie
    ///jesli są, to uaktywni pionek i wykona ruch
    //potem poleci każdemu klientowi przemalowanie tablicy.

    @Override
    public void decode(String message) {
        String[] commands = message.split(" ");
        boolean whitePlayer;
        if(commands[0]=="W"){
            whitePlayer=true;
        }else{
            whitePlayer=false;
        }
        int x = Integer.parseInt(commands[2]), y = Integer.parseInt(commands[3]);
        switch (commands[1]){
            case "C":
                if(Pionek.getPionekByCords(x,y)!=null) {
                    if (isWhiteTurn == Pionek.getPionekByCords(x, y).isWhite() && isWhiteTurn == whitePlayer) {
                        Pionek.getPionekByCords(x, y).legalneKafelki();
                        ///i to tera zakodowac trzeba.
                        Pionek.getPionekByCords(x, y).setActive(true);
                    }
                }else{
                    //invalid action
                }
            case "M":
                if(isWhiteTurn==whitePlayer){
                    for(int i = 0; i<Pionek.getActivePionek().legalneKafelki().size(); i+=2){

                        if(Pionek.getActivePionek().legalneKafelki().get(i)==x&&
                                Pionek.getActivePionek().legalneKafelki().get(i+1)==y){
                            int amountOfPionki = Pionek.getPionki().size();
                            Pionek.getActivePionek().move(x,y);
                            //zakodowac wysylke kordów pionków
                            boolean isWin=true;
                            for(Pionek pionek: Pionek.getPionki()){
                                if(!pionek.legalneKafelki().isEmpty()&&pionek.isWhite()!=isWhiteTurn){
                                    isWin=false;
                                }
                            }
                            if(isWin){
                                //zakodowac ogloszenie zwyciezcy, ktorego tura byla teraz, koniec gry.
                            }



                            if(amountOfPionki-Pionek.getPionki().size()==1&&
                                    !Pionek.bicia(x,y,Pionek.getActivePionek().isWhite(), Pionek.getActivePionek().isQueen()).isEmpty()){

                                //zakodowac wysylke legalych kafelkow

                            }else{
                                this.isWhiteTurn=!isWhiteTurn;
                            }
                        }else{
                            // invalid move
                        }
                    }
                }
        }
    }

    /////Serwer bedzie wysylal wiadomosci wygladajace tak "W C 07 10 08 10 09 08 09 11 S" -
    ////Sprawdziłem, legalne kafelki to x07 y10 oraz x08 y10......, jesli jestes bialy to te kafelki zaznacz, stop.
    ///"A W 01 02 W 03 02... B 10 02 B 10 02 S" - do wszystkich klientów, Bialy na x01, y02... czarny na 10 02..., stop"
    // to bedzie wysylane po kazdym wykonanym ruchu.

    @Override
    public String encode() {
        return null;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
}
