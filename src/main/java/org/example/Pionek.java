package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pionek {
    final boolean isWhite;
    boolean isQueen;
    boolean isActive;
    int x;
    int y;
    final boolean isPolish;
    final int sizeOfPlansza;
    static List<Pionek> pionki = new ArrayList();

    public boolean isWhite() {
        return this.isWhite;
    }


    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        if(active){
            for(Pionek pion:pionki){
                pion.isActive = false;
            }
        }
        this.isActive = active;
    }
    //jezeli biale sa na dole (na odwrot), podswitlanie/logika nie dziala
    //trzeba zmienic construktor zeby logika dzialala dla roznych trybow

    public Pionek(boolean isWhite, int x, int y, int sizeOfPlansza, boolean isPolish) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        pionki.add(this);
        this.isQueen = false;
        this.isActive = false;
        this.sizeOfPlansza = sizeOfPlansza;
        this.isPolish = isPolish;
    }

    //ta metoda działa i dla polskich damek i angielskich
    //w polskich damka porusza się po przekątnej i gdy znajdzie pionek przeciwnika, za którym na tej przekątnej jest pusto
    //to go zbije, i wyląduje za nim.
    public void move(int x, int y) {
        if(x>this.x){
            if(y>this.y){
                if(getPionekByCords(x-1, y-1)!=null&&getPionekByCords(x-1, y-1).isWhite()!=this.isWhite()){
                    pionki.remove(getPionekByCords(x-1, y-1));
                }
            }else if(y<this.y){
                if(getPionekByCords(x-1, y+1)!=null&&getPionekByCords(x-1, y+1).isWhite()!=this.isWhite()){
                    pionki.remove(getPionekByCords(x-1, y+1));
                }
            }
        }else if(x<this.x){
            if(y>this.y){
                if(getPionekByCords(x+1, y-1)!=null&&getPionekByCords(x+1, y-1).isWhite()!=this.isWhite()){
                    pionki.remove(getPionekByCords(x+1, y-1));
                }
            }else if(y<this.y){
                if(getPionekByCords(x+1, y+1)!=null&&getPionekByCords(x+1, y+1).isWhite()!=this.isWhite()){
                    pionki.remove(getPionekByCords(x+1, y+1));
                }
            }
        }

        this.x = x;
        this.y = y;

        if(this.isWhite()&&y==this.sizeOfPlansza){
            this.setQueen(true);
        }else if((!this.isWhite())&&y==0){
            this.setQueen(true);
        }
    }

//    public void zbij(int x, int y){
//        if(this.x<x){
//            if(this.y<y){
//
//            }
//        }
//    }

    public static List<Pionek> getPionki() {
        return pionki;
    }

    public boolean isQueen() {
        return this.isQueen;
    }

    public void setQueen(boolean queen) {this.isQueen = queen;}

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

            if(!getPionekByCords(x, y).isPolish){
                if (isWhite) {
                    for (i = 0; i < pionki.size(); ++i) {
                        if (!((Pionek) pionki.get(i)).isWhite && ((Pionek) pionki.get(i)).getY() == y + 1) {
                            if (((Pionek) pionki.get(i)).getX() == x + 1) {
                                if (getPionekByCords(x + 2, y + 2) == null && x + 2 < pionki.get(i).sizeOfPlansza && y + 2 < pionki.get(i).sizeOfPlansza) {
                                    xandy.add(x + 2);
                                    xandy.add(y + 2);
                                }
                            } else if (((Pionek) pionki.get(i)).getX() == x - 1 && getPionekByCords(x - 2, y + 2) == null && x - 2 >= 0 && y + 2 < pionki.get(i).sizeOfPlansza) {
                                xandy.add(x - 2);
                                xandy.add(y + 2);
                            }
                        }
                    }
                } else if (!isWhite) {
                    for (i = 0; i < pionki.size(); ++i) {
                        if (((Pionek) pionki.get(i)).isWhite && ((Pionek) pionki.get(i)).getY() == y - 1) {
                            if (((Pionek) pionki.get(i)).getX() == x + 1) {
                                if (getPionekByCords(x + 2, y - 2) == null && x + 2 < pionki.get(i).sizeOfPlansza && y - 2 >= 0) {
                                    xandy.add(x + 2);
                                    xandy.add(y - 2);
                                }
                            } else if (((Pionek) pionki.get(i)).getX() == x - 1 && getPionekByCords(x - 2, y - 2) == null && x - 2 >= 0 && y - 2 >= 0) {
                                xandy.add(x - 2);
                                xandy.add(y - 2);
                            }
                        }
                    }
                }
            }else{
                if(getPionekByCords(x+1, y+1).isWhite!=isWhite&&
                        getPionekByCords(x+2, x+2)==null&&
                        x+2<getPionekByCords(x, y).sizeOfPlansza&&y+2<getPionekByCords(x, y).sizeOfPlansza ){
                    xandy.add(x+2);
                    xandy.add(y+2);
                }else if(getPionekByCords(x-1, y+1).isWhite!=isWhite&&
                        getPionekByCords(x-2, x+2)==null&&
                        x-2>=0&&y+2<getPionekByCords(x, y).sizeOfPlansza ){
                    xandy.add(x-2);
                    xandy.add(y+2);
                }else if(getPionekByCords(x+1, y-1).isWhite!=isWhite&&
                        getPionekByCords(x+2, x-2)==null&&
                        x+2<getPionekByCords(x, y).sizeOfPlansza&&y-2>=0 ){
                    xandy.add(x+2);
                    xandy.add(y-2);
                }else if(getPionekByCords(x-1, y-1).isWhite!=isWhite&&
                        getPionekByCords(x-2, x-2)==null&&
                        x-2>=0&&y-2>=0 ){
                    xandy.add(x-2);
                    xandy.add(x-2);
                }
            }
        }else{
            if(getPionekByCords(x,y).isPolish){
                int xk = x, yk = y;
                while(xk<getPionekByCords(x,y).sizeOfPlansza&&yk<getPionekByCords(x,y).sizeOfPlansza){
                    xk++;
                    yk++;
                    if(getPionekByCords(xk,yk)!=null) {
                        if (getPionekByCords(xk, yk).isWhite == isWhite) {
                            break;
                        } else if(getPionekByCords(xk+1, yk+1)==null&&xk+1<getPionekByCords(x,y).sizeOfPlansza&&yk+1<getPionekByCords(x,y).sizeOfPlansza){
                            xandy.add(xk+1);
                            xandy.add(yk+1);
                            break;
                        }
                    }
                }
                xk = x;
                yk = y;
                while(xk<getPionekByCords(x,y).sizeOfPlansza&&yk>=0){
                    xk++;
                    yk--;
                    if(getPionekByCords(xk,yk)!=null) {
                        if (getPionekByCords(xk, yk).isWhite == isWhite) {
                            break;
                        } else if(getPionekByCords(xk+1, yk-1)==null&&xk+1<getPionekByCords(x,y).sizeOfPlansza&&yk-1>=0){
                            xandy.add(xk+1);
                            xandy.add(yk-1);
                            break;
                        }
                    }
                }
                xk = x;
                yk = y;
                while(xk>=0&&yk<getPionekByCords(x,y).sizeOfPlansza){
                    xk--;
                    yk++;
                    if(getPionekByCords(xk,yk)!=null) {
                        if (getPionekByCords(xk, yk).isWhite == isWhite) {
                            break;
                        } else if(getPionekByCords(xk-1, yk+1)==null&&xk-1>=0&&yk+1<getPionekByCords(x,y).sizeOfPlansza){
                            xandy.add(xk-1);
                            xandy.add(yk+1);
                            break;
                        }
                    }
                }
                xk = x;
                yk = y;
                while(xk>=0&&yk>=0){
                    xk--;
                    yk--;
                    if(getPionekByCords(xk,yk)!=null) {
                        if (getPionekByCords(xk, yk).isWhite == isWhite) {
                            break;
                        } else if(getPionekByCords(xk-1, yk-1)==null&&xk-1>=0&&yk-1>=0){
                            xandy.add(xk-1);
                            xandy.add(yk-1);
                            break;
                        }
                    }
                }
            }else{
                if(getPionekByCords(x+1, y+1).isWhite!=isWhite&&
                        getPionekByCords(x+2, x+2)==null&&
                        x+2<getPionekByCords(x, y).sizeOfPlansza&&y+2<getPionekByCords(x, y).sizeOfPlansza ){
                    xandy.add(x+2);
                    xandy.add(y+2);
                }else if(getPionekByCords(x-1, y+1).isWhite!=isWhite&&
                        getPionekByCords(x-2, x+2)==null&&
                        x-2>=0&&y+2<getPionekByCords(x, y).sizeOfPlansza ){
                    xandy.add(x-2);
                    xandy.add(y+2);
                }else if(getPionekByCords(x+1, y-1).isWhite!=isWhite&&
                        getPionekByCords(x+2, x-2)==null&&
                        x+2<getPionekByCords(x, y).sizeOfPlansza&&y-2>=0 ){
                    xandy.add(x+2);
                    xandy.add(y-2);
                }else if(getPionekByCords(x-1, y-1).isWhite!=isWhite&&
                        getPionekByCords(x-2, x-2)==null&&
                        x-2>=0&&y-2>=0 ){
                    xandy.add(x-2);
                    xandy.add(x-2);
                }
            }
        }
        return xandy;
    }

    public ArrayList<Integer> legalneKafelki(){
        boolean isBicie=false;
        for(Pionek pionek:pionki){
            if(pionek.isWhite()==isWhite){
                if(!bicia(pionek.getX(), pionek.getY(), pionek.isWhite(), pionek.isQueen()).isEmpty()){
                    isBicie=true;
                }
            }
        }
        ArrayList<Integer> legalneKafelki = new ArrayList<>();
        if(Pionek.bicia(this.getX(), this.getY(), this.isWhite(), this.isQueen()).isEmpty()&&isBicie==false) {
            if (this.isQueen()) {
                if(this.isPolish){
                    int x = this.getX(), y= this.getY();
                    while(x<this.sizeOfPlansza&&y<this.sizeOfPlansza){
                        x++;
                        y++;
                        if(getPionekByCords(x,y)==null&&x<this.sizeOfPlansza&&y<this.sizeOfPlansza){
                            legalneKafelki.add(x);
                            legalneKafelki.add(y);
                        }
                    }
                    x = this.getX();
                    y= this.getY();
                    while(x>=0&&y<this.sizeOfPlansza){
                        x--;
                        y++;
                        if(getPionekByCords(x,y)==null&&x>=0&&y<this.sizeOfPlansza){
                            legalneKafelki.add(x);
                            legalneKafelki.add(y);
                        }
                    }
                    x = this.getX();
                    y= this.getY();
                    while(x<this.sizeOfPlansza&&y>=0){
                        x++;
                        y--;
                        if(getPionekByCords(x,y)==null&&x<this.sizeOfPlansza&&y>=0){
                            legalneKafelki.add(x);
                            legalneKafelki.add(y);
                        }
                    }
                    x = this.getX();
                    y= this.getY();
                    while(x>=0&&y>=0) {
                        x--;
                        y--;
                        if (getPionekByCords(x, y) == null && x>=0&&y>=0) {
                            legalneKafelki.add(x);
                            legalneKafelki.add(y);
                        }
                    }
                } else{
                    if(getPionekByCords(this.getX()+1, this.getY()+1)==null&&this.getX()+1<this.sizeOfPlansza&&this.getY()+1<this.sizeOfPlansza){
                        legalneKafelki.add(this.getX()+1);
                        legalneKafelki.add(this.getY()+1);
                    }
                    if(getPionekByCords(this.getX()-1, this.getY()+1)==null&&this.getX()-1>=0&&this.getY()+1<this.sizeOfPlansza){
                        legalneKafelki.add(this.getX()-1);
                        legalneKafelki.add(this.getY()+1);
                    }
                    if(getPionekByCords(this.getX()+1, this.getY()-1)==null&&this.getX()+1<this.sizeOfPlansza&&this.getY()-1>=0){
                        legalneKafelki.add(this.getX()+1);
                        legalneKafelki.add(this.getY()-1);
                    }
                    if(getPionekByCords(this.getX()-1, this.getY()-1)==null&&this.getX()-1>=0&&this.getY()-1>=0){
                        legalneKafelki.add(this.getX()-1);
                        legalneKafelki.add(this.getY()-1);
                    }
                }
            } else {
                if (this.isWhite()) {
                    if ((getPionekByCords(this.getX() + 1, this.getY() + 1) == null) && (this.getX() + 1 < this.sizeOfPlansza && this.getY() + 1 < this.sizeOfPlansza)) {
                        legalneKafelki.add(this.getX() + 1);
                        legalneKafelki.add(this.getY() + 1);
                    }
                    if ((getPionekByCords(this.getX() - 1, this.getY() + 1) == null) && (this.getX() - 1 >= 0 && this.getY() + 1 < this.sizeOfPlansza)) {
                        legalneKafelki.add(this.getX() - 1);
                        legalneKafelki.add(this.getY() + 1);
                    }
                } else if (!this.isWhite()) {
                    if ((getPionekByCords(this.getX() + 1, this.getY() - 1) == null) && (this.getX() + 1 < this.sizeOfPlansza && this.getY() - 1 >= 0)) {
                        legalneKafelki.add(this.getX() + 1);
                        legalneKafelki.add(this.getY() - 1);
                    }
                    if ((getPionekByCords(this.getX() - 1, this.getY() - 1) == null) && (this.getX() - 1 >= 0 && this.getY() - 1 >= 0)) {
                        legalneKafelki.add(this.getX() - 1);
                        legalneKafelki.add(this.getY() - 1);
                    }
                }
            }

        }else if(!Pionek.bicia(this.getX(), this.getY(), this.isWhite(), this.isQueen()).isEmpty()){
            return Pionek.bicia(this.getX(), this.getY(), this.isWhite(), this.isQueen());
        }
        return legalneKafelki;
    }

}