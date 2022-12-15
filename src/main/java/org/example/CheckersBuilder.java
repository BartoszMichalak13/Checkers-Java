package org.example;

public class CheckersBuilder {
    int size;
    boolean candamabekilled;
    boolean manyfieldsdama;
    boolean bestkill;
    boolean canpawnkillbackwards;
    boolean topleftcorner;
    public CheckersBuilder(int size, boolean candamabekilled, boolean manyfieldsdama, boolean bestkill, boolean canpawnkillbackwards, boolean topleftcorner){
        this.size=size;
        this.candamabekilled=candamabekilled;
        this.manyfieldsdama=manyfieldsdama;
        this.bestkill=bestkill;
        this.canpawnkillbackwards=canpawnkillbackwards;
        this.topleftcorner=topleftcorner;
    }
}
