package org.example;

public class SpanishBuilder {
    boolean candamabekilled = true;
    boolean manyfieldsdama = true;
    boolean bestkill = true;
    boolean canpawnkillbackwards = false;
    boolean turkishflag=false;

    int size = 8;
    boolean bottomleftcorner = true;//false == czarne chyba
    public void build(){
        Plansza pl =new Plansza(size,bottomleftcorner,turkishflag);
        pl.boardbuilder();
    }
}
