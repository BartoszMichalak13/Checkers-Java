package org.example;

public class TurkishBuilder {

    boolean candamabekilled = true;
    boolean manyfieldsdama = true;
    boolean bestkill = true;
    boolean canpawnkillbackwards = false;

    int size = 8;
    boolean bottomleftcorner = false;//false == czarne chyba
    public void build(){
        Plansza pl =new Plansza(size,bottomleftcorner);
        pl.boardbuilder();
    }
}
