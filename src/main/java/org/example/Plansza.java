package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plansza {
    JFrame jFrame = new JFrame();
    int windowH;
    int windowW;
    Dimension size_of_window;
    public Plansza() {
    }
    public JFrame getFrame()
    {
        return this.jFrame;
    }
    public Dimension rozmiar() {return this.jFrame.getContentPane().getSize();}
    public int getwindowW(){return this.windowW;}
    public int getwindowH(){return this.windowH;}
    public void boardbuilder() {

        jFrame.setBounds(10, 10, 1024, 720);
        size_of_window = jFrame.getContentPane().getSize();
        windowH = (int) (size_of_window.getHeight() / 8);
        windowW = (int) (size_of_window.getWidth() / 8);
        //Ustawiam pionki
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i == 0 || i == 2) && j % 2 == 1) || (i == 1 && j % 2 == 0)) {
                    new Pionek(true, j, i);
                } else if (((i == 7 || i == 5) && j % 2 == 0) || (i == 6 && j % 2 == 1)) {
                    new Pionek(false, j, i);
                }
            }
        }

        //tworzę szachownice, myśle że to wszystko trzeba będzie dać do osobnych klas, ale narazie
        //jestem w programistycznym cugu i robie cokolwiek byle działało, potem to sie ogarnie.

        //GridLayout gridLayout = new GridLayout(0,1);
        //gridLayout.add(jPanel);
        jFrame.setPreferredSize(new Dimension(1024, 720));
        jFrame.pack();
        //jFrame.setLayout(gridLayout);
        jFrame.add(new Pole());
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }
}