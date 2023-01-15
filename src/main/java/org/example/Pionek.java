package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Pionek {

    final boolean isWhite;
    boolean isQueen;
    boolean isActive;
    int x;
    int y;
    final boolean isPolish;
    final int sizeOfPlansza;
    static List<Pionek> pionki = new ArrayList();

    /**
     * getter of pawns colour
     * @return true if pawn is white, fals elsewhere.
     */
    public boolean isWhite() {
        return this.isWhite;
    }

    /**
     * getter of pawns activeness
     * @return true if pawn is active, fals elsewhere.
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * setter of pawns activeness. If it sets pawn to be active, it disactivates every other pawn.
     * @param active
     */
    public void setActive(boolean active) {
        if(active){
            for(Pionek pion:pionki){
                pion.isActive = false;
            }
        }
        this.isActive = active;
    }

    /**
     * Pawn's constructor, it's setting flags of pawns and adding each one to list of pawns on which logic will be operated
     * @param isWhite
     * @param x
     * @param y
     * @param sizeOfPlansza
     * @param isPolish
     */

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

    /**
     * sets coordinates of pawn. Also deletes enemy if enemy was captured during move and sets pawn to become a queen
     * @param x coordinate of targeting field
     * @param y coordinate of targeting field
     */
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

    /**
     * getter of static pawns list
     * @return list of existing pawns in game
     */
    public static List<Pionek> getPionki() {
        return pionki;
    }

    /**
     * getter of whether the pawn is queen
     * @return true if pawn is queen, false elsewhere
     */
    public boolean isQueen() {
        return this.isQueen;
    }

    /**
     * setter of queen status
     * @param queen boolean status if pawn is queen
     */
    public void setQueen(boolean queen) {this.isQueen = queen;}

    /**
     * getter of x coordinate of pawn
     * @return x coordinate of pawn
     */
    public int getX() {
        return this.x;
    }

    /**
     * setter of x coordinate of pawn
     * @param x targeting x coordinate of pawn
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter of y coordinate of pawn
     * @return y coordinate of pawn
     */
    public int getY() {
        return this.y;
    }

    /**
     * setter of y coordinate of pawn
     * @param y targeting y coordinate of pawn
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * getter of pawn at specific coordinates
     * @param x targeting x coordinate
     * @param y targeting y coordinate
     * @return pawn at this coordinates
     */
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

    /**
     * searches for legal captures of pawn of specific type
     * @param x x coordinate of pawn
     * @param y y coordinate of pawn
     * @param isWhite if pawn is white
     * @param isQueen if pawn is queen
     * @return array of coordinates of fields pawn can go to make a capture
     */
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
            }else {
                if (getPionekByCords(x + 1, y + 1) != null) {
                    if (Objects.requireNonNull(getPionekByCords(x + 1, y + 1)).isWhite != isWhite &&
                            getPionekByCords(x + 2, y + 2) == null &&
                            x + 2 < Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza && y + 2 < Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza) {
                        xandy.add(x + 2);
                        xandy.add(y + 2);
                    }
                } else if (getPionekByCords(x - 1, y + 1) != null) {
                    if (Objects.requireNonNull(getPionekByCords(x - 1, y + 1)).isWhite != isWhite &&
                        getPionekByCords(x - 2, y + 2) == null &&
                        x - 2 >= 0 && y + 2 < Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza) {
                        xandy.add(x - 2);
                        xandy.add(y + 2);
                }
            }else if(getPionekByCords(x+1, y-1)!=null){
                if(Objects.requireNonNull(getPionekByCords(x + 1, y - 1)).isWhite!=isWhite&&
                        getPionekByCords(x+2, y-2)==null&&
                        x+2< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza&&y-2>=0 ) {
                    xandy.add(x + 2);
                    xandy.add(y - 2);
                }
                }else if(getPionekByCords(x-1, y-1)!=null) {
                    if (Objects.requireNonNull(getPionekByCords(x - 1, y - 1)).isWhite != isWhite &&
                            getPionekByCords(x - 2, y - 2) == null &&
                            x - 2 >= 0 && y - 2 >= 0) {
                        xandy.add(x - 2);
                        xandy.add(x - 2);
                    }
                }
            }
        }else{
            if(Objects.requireNonNull(getPionekByCords(x, y)).isPolish){
                int xk = x, yk = y;
                while(xk< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza&&yk< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza){
                    xk++;
                    yk++;
                    if(getPionekByCords(xk,yk)!=null) {
                        if (Objects.requireNonNull(getPionekByCords(xk, yk)).isWhite == isWhite) {
                            break;
                        } else if(getPionekByCords(xk+1, yk+1)==null&&xk+1< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza&&yk+1< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza){
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
                if(getPionekByCords(x+1, y+1)!=null){
                if(Objects.requireNonNull(getPionekByCords(x + 1, y + 1)).isWhite!=isWhite&&
                        getPionekByCords(x+2, x+2)==null&&
                        x+2< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza&&y+2< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza ) {
                    xandy.add(x + 2);
                    xandy.add(y + 2);
                }
                }else if(getPionekByCords(x-1, y+1)!=null){
                    if(Objects.requireNonNull(getPionekByCords(x - 1, y + 1)).isWhite!=isWhite&&
                        getPionekByCords(x-2, x+2)==null&&
                        x-2>=0&&y+2< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza ) {
                    xandy.add(x - 2);
                    xandy.add(y + 2);
                }
                }else if(getPionekByCords(x+1, y-1)!=null){
                    if(Objects.requireNonNull(getPionekByCords(x + 1, y - 1)).isWhite!=isWhite&&
                        getPionekByCords(x+2, x-2)==null&&
                        x+2< Objects.requireNonNull(getPionekByCords(x, y)).sizeOfPlansza&&y-2>=0 ) {
                    xandy.add(x + 2);
                    xandy.add(y - 2);
                }
                }else if(getPionekByCords(x-1, y-1)!=null){
                    if(Objects.requireNonNull(getPionekByCords(x - 1, y - 1)).isWhite!=isWhite&&
                            getPionekByCords(x-2, x-2)==null&&
                            x-2>=0&&y-2>=0 ){
                        xandy.add(x-2);
                        xandy.add(x-2);
                    }
                }
            }
        }
        return xandy;
    }

    /**
     * searches for fields pawn can go without violating rules of checkers
     * @return array of coordinates of fields
     */
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