package org.example;

public class CanadianBuilder {
    boolean candamabekilled = true;
    boolean manyfieldsdama = true;
    boolean bestkill = true;
    boolean canpawnkillbackwards = true;
    boolean turkishflag=false;

    int size = 12;
    boolean bottomleftcorner = false;//false == czarene, nie wyspecyfikowano
    public void build(){
        Plansza pl =new Plansza(size,bottomleftcorner,turkishflag);
        pl.boardbuilder();
    }
}
