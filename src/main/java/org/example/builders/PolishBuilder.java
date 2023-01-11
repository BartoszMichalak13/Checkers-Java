package org.example.builders;

import org.example.Gamemain;
import org.example.Gamev2;
import org.example.Plansza;

public class PolishBuilder{
    boolean isPolish=true;

    int size = 10;
    public void build(Boolean werecreated, String s){
        if(!werecreated) {
            System.out.println("Ile razy tu weszlismy");
            new Gamev2().assignvalues(size);
            ///PLAN, PRZESLIJ BOOTMLEFT ITD TUTAJ W IFIE, I WTEDY ZWYKLY SEN JEST OK

            Gamemain gamemain = new Gamemain().getGamemain();
            s+=" Stworz Pionki ";
            s+=Integer.toString(size);
            s+=" ";
            s+=Boolean.toString(isPolish);
            gamemain.send(s);
        }
        Plansza pl =new Plansza(size);
        pl.boardbuilder();
    }
}
