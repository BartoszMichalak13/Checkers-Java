package org.example;

public class GermanBuilder {
    boolean candamabekilled = true;
    boolean manyfieldsdama = true;
    boolean bestkill = true;
    boolean canpawnkillbackwards = true;

    int size = 8;
    boolean bottomleftcorner = true;//false == czarne chyba
    public void build(){
        Plansza pl =new Plansza(size,bottomleftcorner);
        pl.boardbuilder();
    }
}
