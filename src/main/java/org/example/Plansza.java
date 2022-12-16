package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plansza implements MouseListener{
    private final int size;
    private boolean isWhiteTurn;

    public Plansza(int size, boolean isPolish) {
        this.size = 8;
        this.isWhiteTurn = true;
        if(size==8){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (((i == 0 || i == 2) && j % 2 == 1) || (i == 1 && j % 2 == 0)) {
                        new Pionek(i, j, true, isPolish, this);
                    } else if (((i == 7 || i == 5) && j % 2 == 0) || (i == 6 && j % 2 == 1)) {
                        new Pionek(i, j, false, isPolish, this);
                    }
                }
            }
        }
        else if(size==10){
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (((i == 0 || i == 2 ) && j % 2 == 1) || ((i == 1||i==3) && j % 2 == 0)) {
                        new Pionek(i, j, true, isPolish, this);
                    } else if (((i == 9 || i == 7) && j % 2 == 0) || ((i == 8 ||i==6)&& j % 2 == 1)) {
                        new Pionek(i, j, false, isPolish, this);
                    }
                }
            }
        }
    }

    JPanel jPanel = new JPanel(){
        public void paint(){
            
        }
    }



    public int getSize() {
        return size;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}