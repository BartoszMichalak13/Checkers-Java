package org.example;

public class DecoderServer implements Decoder{
    boolean isWhiteTurn=true;


    /////Gracz będzie wysyłał wiadomości: wyglądające tak: "W C 08 09 S" -  "im White, check x - 8, y - 9, stop."
    ////Jeśli jest tura gracza białego, serwer bedzie dalej sprawdzal, jeśli nie, to zignoruje zapytanie.
    ///znajdzie pionek na tym polu, jesli jest pusty to zignoruje zapytanie.
    //jeśli jest na nim pionek to wywoła na nim operacje legalne kafelki i odeśle odpowiedź oraz uaktywni ten pionek

    /////Gdy wiadomość bedzie wyglądała "W M 08 09 S" - im white, move active pionek to x08 y09
    ////serwer sprawdzi, czy te koordynaty są w "legalnych kafelkach", jesli nie to zignoruje zapytanie
    ///jesli są, to wykona ruch
    //potem poleci każdemu klientowi przemalowanie tablicy.
    @Override
    public void decode(String message) {

    }


    /////Serwer bedzie wysylal wiadomosci wygladajace tak "W C 07 10 08 10 09 08 09 11 S" -
    ////Sprawdziłem, legalne kafelki to x07 y10 oraz x08 y10......, jesli jestes bialy to te kafelki zaznacz.
    ///"A W 01 02 W 03 02... B 10 02 B 10 02 S" - do wszystkich klientów, Bialy na x01, y02... czarny na 10 02..., koniec wiadomosci"
    // to bedzie wysylane po kazdym wykonanym ruchu.

    @Override
    public String encode() {
        return null;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
}
