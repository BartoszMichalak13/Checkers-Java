package pl.krzysztof;

import java.util.ArrayList;
import java.util.List;

public class Pionek {
    boolean isWhite;
    boolean isQueen;
    boolean isActive;
    int x;
    int y;
    //int id;
    static List<Pionek> pionki = new ArrayList<>();

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Pionek(boolean isWhite, int x, int y) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        pionki.add(this);
        this.isQueen=false;
        this.isActive=false;
       // this.id=pionki.size();
    }

    public void przesun(int x, int y){
        this.x=x;
        this.y=y;
    }
    public static List<Pionek> getPionki(){
        return pionki;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
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

    public static void setPionki(List<Pionek> pionki) {
        Pionek.pionki = pionki;
    }
    public static Pionek getPionekByMouse(int x, int y){
        for(Pionek pion : pionki){
            if(pion.x==x/64&&pion.y==y/64){
                return pion;
            }
        }
        return null;
    }

//    public int getId() {
//        return id;
//    }
//    public static Pionek getPionekByID(int id){
//
//    }

}
