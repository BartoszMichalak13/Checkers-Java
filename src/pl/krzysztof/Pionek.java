package pl.krzysztof;

import java.util.ArrayList;
import java.util.List;

public class Pionek {
    boolean isWhite;
    boolean isQueen;
    int x;
    int y;
    static List<Pionek> pionki = new ArrayList<>();

    public Pionek(boolean isWhite, int x, int y, List<Pionek> pionki) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        pionki.add(this);
        this.isQueen=false;
    }

    public void przesun(int x, int y){
        this.x=x;
        this.y=y;
    }
}
