package org.example;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pionek {
    private int x;
    private int y;
    private boolean isWhite;
    private boolean isQueen;
    private boolean isActive;
    private final boolean isPolish;
    private final Plansza plansza;
    private int sizeOfPlansza;
    private static ArrayList<Pionek> pionki = new ArrayList<>();

    public Pionek(int x, int y, boolean isWhite, boolean isPolish, Plansza plansza) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.isQueen = false;
        this.isActive = false;
        this.isPolish=isPolish;
        this.plansza = plansza;
        this.sizeOfPlansza = plansza.getSize();
        pionki.add(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
    }

    public boolean isPolish(){
        return isPolish;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Plansza getPlansza() {
        return plansza;
    }

    public static ArrayList<Pionek> getPionki() {
        return pionki;
    }

    public void przesun(int x, int y){
        this.x=x;
        this.y=y;

        if(this.isWhite()&&y==this.sizeOfPlansza-1){
            this.setQueen(true);
        }else if((!this.isWhite())&&y==0){
            this.setQueen(true);
        }
    }

    public static Pionek getPionekByCords(int x, int y) {
        Iterator var2 = pionki.iterator();

        Pionek pion;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            pion = (Pionek)var2.next();
        } while(pion.x != x || pion.y != y);

        return pion;
    }

    public static Pionek getActivePionek() {
        Iterator var0 = pionki.iterator();

        Pionek pion;
        do {
            if (!var0.hasNext()) {
                return null;
            }

            pion = (Pionek)var0.next();
        } while(!pion.isActive());

        return pion;
    }

    //TODO: ogarnięcie bić damką, w trybie gry polskim

    private static ArrayList<Integer> biciaDamka(int x, int y, boolean isWhite, boolean isPolish){
        ArrayList<Integer> xandy = new ArrayList<>();
        if(isPolish){

        }else{
            for(Pionek pion:pionki){
                if(isWhite!=pion.isWhite()){
                    if(pion.getX()==x+1&&pion.getY()==y+1&&
                            getPionekByCords(x+2, y+2)==null&&
                            x+2<pion.sizeOfPlansza&&y+2< pion.sizeOfPlansza){
                        xandy.add(x+2, y+2);
                    }
                    if(pion.getX()==x+1&&pion.getY()==y-1&&
                            getPionekByCords(x+2, y-2)==null
                            &&x+2<pion.sizeOfPlansza&&y-2>=0){
                        xandy.add(x+2, y-2);
                    }
                    if(pion.getX()==x-1&&pion.getY()==y+1 &&
                            getPionekByCords(x-2, y+2)==null&&
                            x-2>=0&&y+2< pion.sizeOfPlansza){
                        xandy.add(x-2, y+2);
                    }
                    if(pion.getX()==x-1&&pion.getY()==y-1&&
                            getPionekByCords(x-2, y-2)==null&&
                            x-2>=0&&y-2>=0){
                        xandy.add(x-2, y+2);
                    }
                }
            }
        }
        return xandy;
    }

    public static ArrayList<Integer> bicia(int x, int y, boolean isWhite, boolean isQueen, boolean isPolish) {
        ArrayList<Integer> xandy = new ArrayList();
        if (!isQueen) {
            int i;
            if (isWhite) {
                for(i = 0; i < pionki.size(); ++i) {
                    if (!((Pionek)pionki.get(i)).isWhite && ((Pionek)pionki.get(i)).getY() == y + 1) {
                        if (((Pionek)pionki.get(i)).getX() == x + 1) {
                            if (getPionekByCords(x + 2, y + 2) == null && x + 2 < pionki.get(i).sizeOfPlansza && y + 2 < pionki.get(i).sizeOfPlansza) {
                                xandy.add(x + 2);
                                xandy.add(y + 2);
                            }
                        } else if (((Pionek)pionki.get(i)).getX() == x - 1 && getPionekByCords(x - 2, y + 2) == null && x - 2 >= 0 && y + 2 < pionki.get(i).sizeOfPlansza) {
                            xandy.add(x - 2);
                            xandy.add(y + 2);
                        }
                    }
                }
            } else if (!isWhite) {
                for(i = 0; i < pionki.size(); ++i) {
                    if (((Pionek)pionki.get(i)).isWhite && ((Pionek)pionki.get(i)).getY() == y - 1) {
                        if (((Pionek)pionki.get(i)).getX() == x + 1) {
                            if (getPionekByCords(x + 2, y - 2) == null && x + 2 < pionki.get(i).sizeOfPlansza && y - 2 >=0) {
                                xandy.add(x + 2);
                                xandy.add(y - 2);
                            }
                        } else if (((Pionek)pionki.get(i)).getX() == x - 1 && getPionekByCords(x - 2, y - 2) == null && x - 2 >= 0 && y -2>= 0) {
                            xandy.add(x - 2);
                            xandy.add(y - 2);
                        }
                    }
                }
            }
        }else{
            return biciaDamka(x, y, isWhite, isPolish);
        }
        return xandy;
    }

    //TODO: ogarniecie ruchów damka w trybie gry polskim.

    public ArrayList<Integer> ruchyDamka(boolean isPolish){
        ArrayList<Integer> xandy = new ArrayList<>();
        if(isPolish){

        }else{
            if(getPionekByCords(x+1, y+1)==null&&x+1<this.sizeOfPlansza&&y+1<this.sizeOfPlansza){
                xandy.add(x+1, y+1);
            }
            if(getPionekByCords(x+1, y-1)==null&&y-1>=0&&x+1<this.sizeOfPlansza){
                xandy.add(x+1, y-1);
            }
            if(getPionekByCords(x-1, y+1)==null&&y+1<this.sizeOfPlansza&&x-1>=0){
                xandy.add(x-1, y+1);
            }
            if(getPionekByCords(x-1, y-1)==null&&y-1>=0&&x-1>=0){
                xandy.add(x-1, y-1);
            }
        }
        return xandy;
    }

    public ArrayList<Integer> legalneKafelki(){
        ArrayList<Integer> legalneKafelki = new ArrayList<>();
        if(Pionek.bicia(this.getX(), this.getY(), this.isWhite(), this.isQueen(), this.isPolish()).isEmpty()){
            if (this.isQueen()){
                return this.ruchyDamka(isPolish);
            }else{
                if(this.isWhite()){
                    if((getPionekByCords(this.getX()+1, this.getY()+1)==null)&&(this.getX()+1<8&&this.getY()+1<8)){
                        legalneKafelki.add(this.getX()+1);
                        legalneKafelki.add(this.getY()+1);
                    }
                    if((getPionekByCords(this.getX()-1, this.getY()+1)==null)&&(this.getX()-1>=0&&this.getY()+1<8)){
                        legalneKafelki.add(this.getX()-1);
                        legalneKafelki.add(this.getY()+1);
                    }
                }else if(!this.isWhite()){
                    if((getPionekByCords(this.getX()+1, this.getY()-1)==null)&&(this.getX()+1<8&&this.getY()-1>=0)){
                        legalneKafelki.add(this.getX()+1);
                        legalneKafelki.add(this.getY()-1);
                    }
                    if((getPionekByCords(this.getX()-1, this.getY()-1)==null)&&(this.getX()-1>=0&&this.getY()-1>=0)){
                        legalneKafelki.add(this.getX()-1);
                        legalneKafelki.add(this.getY()-1);
                    }
                }
            }
        }else{
            if(!this.isQueen()) {
                return Pionek.bicia(this.getX(), this.getY(), this.isWhite(), this.isQueen(), this.isPolish());
            }else{
                return Pionek.biciaDamka(this.getX(), this.getY(), this.isWhite(), this.isPolish());
            }
        }
        return legalneKafelki;
    }
}