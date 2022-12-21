package org.example;

public class RussianBuilder {
    boolean candamabekilled = true;
    boolean manyfieldsdama = true;
    boolean bestkill = false;
    boolean canpawnkillbackwards = true;

    int size = 8;
    boolean bottomleftcorner = false;//false == czarne chyba
    public void build(){
        Plansza pl =new Plansza(size,bottomleftcorner);
        pl.boardbuilder();
    }
}
