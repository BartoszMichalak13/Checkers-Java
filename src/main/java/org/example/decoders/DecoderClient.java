package org.example.decoders;

import org.example.decoders.Decoder;

public class DecoderClient implements Decoder {
    boolean ask=true; //klient na poczatku sie pyta, jak uzyska odpowiedz to sie nie pyta tylko wykonuje ruch.

    /////Gracz będzie wysyłał wiadomości: wyglądające tak: "W C 08 09 S" -  "im White, check x - 8, y - 9, stop."
    /////Gdy wiadomość bedzie wyglądała "W M 08 09 S" - im white, move active pionek to x08 y09, stop.

    /////Serwer bedzie wysylal wiadomosci wygladajace tak "W C 07 10 08 10 09 08 09 11 S"
    ///"A W 01 02 W 03 02... B 10 02 B 10 02 S" - do wszystkich klientów, Bialy na x01, y02... czarny na 10 02..., stop"

    //"A W WIN" - do wszystkich, biale wygraly.

    // "I" - invalid action. Klient odbierze "W C I" znaczy - biały, nieprawidłowa akcja sprawdzania możliwych ruchów

    @Override
    public String decode(String message) {

    }
}
