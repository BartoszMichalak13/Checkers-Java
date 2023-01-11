package org.example.builders;

import org.example.Gamemain;
import org.example.Gamev2;
import org.example.Plansza;

public class EnglishBuilder {

    boolean candamabekilled = true;
    boolean manyfieldsdama = false;
    boolean bestkill = false;
    boolean canpawnkillbackwards = false;
    int size = 8;
    public void build(Boolean werecreated, String s){
        if(!werecreated) {
            System.out.println("Ile razy tu weszlismy");
            new Gamev2().assignvalues(size);
                ///PLAN, PRZESLIJ BOOTMLEFT ITD TUTAJ W IFIE, I WTEDY ZWYKLY SEN JEST OK

                Gamemain gamemain = new Gamemain().getGamemain();
                s+=" Stworz Pionki ";
            s+=Integer.toString(size);
            gamemain.send(s);
        }
        Plansza pl =new Plansza(size);
        pl.boardbuilder();
    }
}
