package org.example.builders;

import org.example.Gamemain;
import org.example.Gamev2;
import org.example.Plansza;

public class EnglishBuilder {

    boolean candamabekilled = true;
    boolean manyfieldsdama = false;
    boolean bestkill = false;
    boolean canpawnkillbackwards = false;
    boolean turkishflag=false;
    boolean isPolish = false;

    int size = 8;
    boolean bottomleftcorner = false;//false == czarne chyba
    public void build(Boolean werecreated, String s){
        if(!werecreated) {
            System.out.println("Ile razy tu weszlismy");
                new Gamev2().assignvalues(turkishflag,bottomleftcorner,size, isPolish);
                ///PLAN, PRZESLIJ BOOTMLEFT ITD TUTAJ W IFIE, I WTEDY ZWYKLY SEN JEST OK

                Gamemain gamemain = new Gamemain().getGamemain();
                s+="Stworz Pionki";
                gamemain.send(s);
        }
        Plansza pl =new Plansza(size,bottomleftcorner,turkishflag);
        pl.boardbuilder();
    }
}
