package org.example.decoders;

import org.example.decoders.Decoder;

public class DecoderClient implements Decoder {
    boolean ask;

    boolean isWhite;

    //gdy tworzymy klientowi dekoder, musimy okreslic czy dekoder jest do gry bialymi czy czarnymi.
    public DecoderClient(boolean isWhite) {
        this.ask=true; //domyslnie najpierw zadajemy pytanie
        this.isWhite = isWhite;
    }




    /////Gracz będzie wysyłał wiadomości: wyglądające tak: "W C 08 09 S" -  "im White, check x - 8, y - 9, stop."
    /////Gdy wiadomość bedzie wyglądała "W M 08 09 S" - im white, move active pionek to x08 y09, stop.

    /////Serwer bedzie wysylal wiadomosci wygladajace tak "W C 07 10 08 10 09 08 09 11 S"
    ///"A W 01 02 W 03 02... B 10 02 B 10 02 S" - do wszystkich klientów, Bialy na x01, y02... czarny na 10 02..., stop"

    //"A W WIN" - do wszystkich, biale wygraly.

    // "I" - invalid action. Klient odbierze "W C I" znaczy - biały, nieprawidłowa akcja sprawdzania możliwych ruchów



    //w przeciwienstwie do dekodera serwerowego, ten dekoduje wiadomość i ją wysyła do klienta, a klient dalej z nią coś robi
    //przy akcji myszką wywołane zostanie String encrypt(int x int y), po czym zostanie to wyslane do serwera.
    //mozna tez poprawic zeby nie zwracalo stringa tylko void i od razu dzialalo na jPanelu, ale wtedy trzeba bedzie
    //zrezygnowac z interfejsu. Obgadajmy to rano, powiedz jak wg ciebie bedzie wygodniej.


    //x,y, wspolrzedne kafelkow na ktore sie kliknie
    public String encrypt(int x, int y){
        String encryption="";
        if(isWhite){
            encryption+="W ";
            if(ask){
                encryption=encryption+"C "+x+" "+y+" ";
                ask=false;
            }else{
                encryption=encryption+"M "+x+" "+y+" ";
            }
        }else{
            encryption+="B ";
            if(ask){
                encryption=encryption+"C "+x+" "+y+" ";
                ask=false;
            }else{
                encryption=encryption+"M "+x+" "+y+" ";
            }
        }
        return encryption+"S";
    }


    @Override
    public String decode(String message) {
        String[] commands = message.split(" ");
        if(isWhite&&commands[0]=="W"){
        }
        //linijka nizje jest po to bo wywalalo blad
        return message;
    }

    //TODO: zroic tak, zeby po wyslaniu zlego zapytania, np klikniecia w zly kafelek dezaktywowac pionek i nie podsiwtlac
    // tego kafelka, generalnei pomyslec co moze klient zrobic i to uwzglednic. ja lece spac, powodzenia

}


