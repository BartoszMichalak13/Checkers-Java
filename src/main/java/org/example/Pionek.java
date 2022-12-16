package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pionek {
    boolean isWhite;
    boolean isQueen;
    boolean isActive;
    int x;
    int y;
    static List<Pionek> pionki = new ArrayList();

    public boolean isWhite() {
        return this.isWhite;
    }

    public void setWhite(boolean white) {
        this.isWhite = white;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public Pionek(boolean isWhite, int x, int y) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        pionki.add(this);
        this.isQueen = false;
        this.isActive = false;
    }

    //zbijanie dziala narazie tylko dla pionkow i damek angielskich. dla damek polskich trzeba pokombinowac
    public void przesun(int x, int y) {
        if(getPionekByCords((this.x+x)/2,(this.y+y)/2)!=null&&getPionekByCords((this.x+x)/2,(this.y+y)/2).isWhite()!=this.isWhite()){
            pionki.remove(getPionekByCords((this.x+x)/2,(this.y+y)/2));
        }

        this.x = x;
        this.y = y;

        if(this.isWhite()&&y==8){
            this.setQueen(true);
        }else if((!this.isWhite())&&y==0){
            this.setQueen(true);
        }


    }

    public void zbij(int x, int y){
        if(this.x<x){
            if(this.y<y){

            }
        }
    }

    public static List<Pionek> getPionki() {
        return pionki;
    }

    public boolean isQueen() {
        return this.isQueen;
    }

    public void setQueen(boolean queen) {
        this.isQueen = queen;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
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

    public static ArrayList<Integer> bicia(int x, int y, boolean isWhite, boolean isQueen) {
        ArrayList<Integer> xandy = new ArrayList();
        if (!isQueen) {
            int i;
            if (isWhite) {
                for(i = 0; i < pionki.size(); ++i) {
                    if (!((Pionek)pionki.get(i)).isWhite && ((Pionek)pionki.get(i)).getY() == y + 1) {
                        if (((Pionek)pionki.get(i)).getX() == x + 1) {
                            if (getPionekByCords(x + 2, y + 2) == null && x + 2 < 8 && y + 2 < 8) {
                                xandy.add(x + 2);
                                xandy.add(y + 2);
                            }
                        } else if (((Pionek)pionki.get(i)).getX() == x - 1 && getPionekByCords(x - 2, y + 2) == null && x - 2 >= 0 && y + 2 < 8) {
                            xandy.add(x - 2);
                            xandy.add(y + 2);
                        }
                    }
                }
            } else if (!isWhite) {
                for(i = 0; i < pionki.size(); ++i) {
                    if (((Pionek)pionki.get(i)).isWhite && ((Pionek)pionki.get(i)).getY() == y - 1) {
                        if (((Pionek)pionki.get(i)).getX() == x + 1) {
                            if (getPionekByCords(x + 2, y - 2) == null && x + 2 < 8 && y - 2 >=0) {
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
        }

        return xandy;
    }

    public ArrayList<Integer> legalneKafelki(){
        ArrayList<Integer> legalneKafelki = new ArrayList<>();
        if(Pionek.bicia(this.getX(), this.getY(), this.isWhite(), this.isQueen()).isEmpty()){
            if (this.isQueen()){

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
            return Pionek.bicia(this.getX(), this.getY(), this.isWhite(), this.isQueen());
        }
        return legalneKafelki;
    }

}