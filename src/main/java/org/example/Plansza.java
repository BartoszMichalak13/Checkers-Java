package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plansza {
    JFrame jFrame = new JFrame();
    public Plansza() {
    }
    public JFrame getFrame()
    {
        return jFrame;
    }
    public Dimension rozmiar(JFrame jFrame)
    {
        return jFrame.getContentPane().getSize();
    }
    public void boardbuilder() {

        jFrame.setBounds(10, 10, 1024, 720);
        Dimension size_of_window;
        int windowH;
        int windowW;
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
        //jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }
}