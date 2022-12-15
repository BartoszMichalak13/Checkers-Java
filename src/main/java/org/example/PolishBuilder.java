package org.example;
/*
    JFrame z Menu, po wybraniu trybu gry tworzony jest odpowiedni builder i robi plansze oraz przesyla flagi do logiki
 */
public class PolishBuilder implements CheckersBuilder{
    int size = 0;
    boolean candamabekilled = false;
    boolean manyfieldsdama = false;
    boolean bestkill = false;
    boolean canpawnkillbackwards = false;
    boolean topleftcorner = false;
    static void build(){
        new Plansza().boardbuilder();
    }
}
