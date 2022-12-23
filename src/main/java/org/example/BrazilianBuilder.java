package org.example;

public class BrazilianBuilder {
    boolean candamabekilled = true;
    boolean manyfieldsdama = true;
    boolean bestkill = true;
    boolean canpawnkillbackwards = true;
    boolean turkishflag=false;

    int size = 8;
    boolean bottomleftcorner = false;//false == czarne chyba
    public void build(){
        Plansza pl =new Plansza(size,bottomleftcorner,turkishflag);
        pl.boardbuilder();
    }
}
