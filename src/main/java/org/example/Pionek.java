package org.example;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Pionek extends MouseListener {
    private int x;
    prvate int y;
    private boolean isWhite;
    private boolean isQueen;
    private boolean isActive;
    private static ArrayList<Pionek> pionki = new ArrayList<>();

    public Pionek(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.isQueen = false;
        this.isActive = false;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static ArrayList<Pionek> getPionki() {
        return pionki;
    }

    public void przesun(int x, int y){
        this.x=x;
        this.y=y;
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
            int i
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
        }else{
            return biciaDamka();
        }
        return xandy;
    }

    private ArrayList<Integer> biciaDamka(){

    }
}