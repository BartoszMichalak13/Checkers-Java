package org.example;

public class TurkishBuilder {

    boolean candamabekilled = true;
    boolean manyfieldsdama = true;
    boolean bestkill = true;
    boolean canpawnkillbackwards = false;

    boolean turkishflag=true;
    int size = 8;
    boolean bottomleftcorner = false;//false == czarne chyba
    public void build(){
        Plansza pl =new Plansza(size,bottomleftcorner, turkishflag);
        pl.boardbuilder();
    }
}
