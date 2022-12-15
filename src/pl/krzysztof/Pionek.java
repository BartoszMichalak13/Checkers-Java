package pl.krzysztof;

import java.util.ArrayList;
import java.util.List;

public class Pionek {
    boolean isWhite;
    boolean isQueen;
    boolean isActive;
    int x;
    int y;
    //statyczna lista, by można było się dobrać do wsyzstkich pionków.
    // do uzgodnienia czy to dać do osobnej klasy
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

    //automatycznie dodaje pionek do statycznej listy podczas jego tworzenia
    public Pionek(boolean isWhite, int x, int y) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        pionki.add(this);
        this.isQueen=false;
        this.isActive=false;
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


    public static Pionek getPionekByCords(int x, int y){
        for(Pionek pion : pionki){
            if(pion.x==x&&pion.y==y){
                return pion;
            }
        }
        return null;
    }

    public static Pionek getActivePionek(){
        for(Pionek pion:pionki){
            if(pion.isActive()){
                return pion;
            }
        }
        return null;
    }

    //zwracam koordynaty bić, x są na indeksach nieparzystych, y na parzystych.
    //elegancko by bylo gdyby zwracalo liste zlozona z (x,y), ale mysle ze tak sie tez da i bedzie git.
    public static List<Integer> bicia(int x, int y, boolean isWhite, boolean isQueen){
        List<Integer> xandy = new ArrayList<>();
        if(isQueen){

        }
        else{
            if(isWhite){
                for(int i=0;i<pionki.size();i++){
                    if(!pionki.get(i).isWhite){
                        if(pionki.get(i).getY()==y+1){
                            if(pionki.get(i).getX()==x+1){
                                if(getPionekByCords(x+2, y+2)==null&&x+2<8&&y+2<8){
                                    xandy.add(x+2);
                                    xandy.add(y+2);
                                }
                            }else if(pionki.get(i).getX()==x-1){
                                if(getPionekByCords(x-2, y+2)==null&&x-2>=0&&y+2<8){
                                    xandy.add(x-2);
                                    xandy.add(y+2);
                                }
                            }
                        }
                    }
                }
            }
            else if(!isWhite){
                for(int i=0;i<pionki.size();i++){
                    if(pionki.get(i).isWhite){
                        if(pionki.get(i).getY()==y-1){
                            if(pionki.get(i).getX()==x+1){
                                if(getPionekByCords(x+2, y-2)==null&&x+2<8&&y+2<8){
                                    xandy.add(x+2);
                                    xandy.add(y-2);
                                }
                            }else if(pionki.get(i).getX()==x-1){
                                if(getPionekByCords(x-2, y-2)==null&&x-2>=0&&y+2<8){
                                    xandy.add(x-2);
                                    xandy.add(y-2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return xandy;
    }


    //tu chce zrobic zeby funckja zwracala x,y kafelek, na ktore mozna wykonac ruch.
    public int[][] legalneKafelki(Pionek pionek){
        int[][] legalneKafelki;
        if (pionek.isQueen()){

        }else{

        }
        return null;
    }
}
