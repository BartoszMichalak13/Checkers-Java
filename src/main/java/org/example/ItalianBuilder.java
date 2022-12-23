package org.example;

public class ItalianBuilder {
    boolean candamabekilled = false;
    boolean manyfieldsdama = false;
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
