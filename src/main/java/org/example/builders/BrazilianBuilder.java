package org.example.builders;

import org.example.Gamemain;
import org.example.Gamev2;
import org.example.Plansza;

public class BrazilianBuilder {
    boolean candamabekilled = true;
    boolean manyfieldsdama = true;
    boolean bestkill = true;
    boolean canpawnkillbackwards = true;
    boolean turkishflag=false;

    int size = 8;
    boolean bottomleftcorner = false;//false == czarne chyba
    public void build(Boolean werecreated, String s){
        if(!werecreated) {
            System.out.println("Ile razy tu weszlismy");
            new Gamev2().assignvalues(turkishflag,bottomleftcorner,size);
            ///PLAN, PRZESLIJ BOOTMLEFT ITD TUTAJ W IFIE, I WTEDY ZWYKLY SEN JEST OK

            Gamemain gamemain = new Gamemain().getGamemain();
            s+="Stworz Pionki";
            gamemain.send(s);
        }
        Plansza pl =new Plansza(size,bottomleftcorner,turkishflag);
        pl.boardbuilder();
    }
}