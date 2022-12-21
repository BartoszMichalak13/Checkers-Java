package org.example;

public class EnglishBuilder {

    boolean candamabekilled = true;
    boolean manyfieldsdama = false;
    boolean bestkill = false;
    boolean canpawnkillbackwards = false;

    int size = 8;
    boolean bottomleftcorner = false;//false == czarne chyba
    public void build(){
        Plansza pl =new Plansza(size,bottomleftcorner);
        pl.boardbuilder();
    }
}
