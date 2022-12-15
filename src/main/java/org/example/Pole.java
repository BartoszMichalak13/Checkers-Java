package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Pole extends JPanel implements MouseListener{
    public Pole(){
        addMouseListener(this);
    }
//    public Pole getPole()
//    {
//        return
//    }
    Plansza plansza = new Plansza() {
        public void paint(Graphics g) {
            //Dimension size_of_window = jFrame.getContentPane().getSize();
            //size_of_window = plansza.rozmiar(plansza.getFrame());
            //windowH = (int) (size_of_window.getHeight() / 8);
            //windowW = (int) (size_of_window.getWidth() / 8);
            for (int y = 0; y < 8; y++) {      //zmienić na n - rozmiar planszy z buildera
                for (int x = 0; x < 8; x++) {
                    if ((x % 2 == 0 && y % 2 == 1) || (y % 2 == 0 && x % 2 == 1)) {
                        g.setColor(Color.GREEN);
                    } else {
                        g.setColor(Color.lightGray);
                    }

                    //g.fillRect(x*64, y*64, 64, 64);//pobierac rozmiar okan i na podst tego dzielic
                    g.fillRect(x * windowW, y * windowH, windowW, windowH);
                }
            }
            for (Pionek pion : Pionek.getPionki()) {
                if (pion.isActive()) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(pion.getX() * windowW, pion.getY() * windowH, windowW, windowH);
                    //g.fillRect(pion.getX()*64, pion.getY()*64, 64, 64);
                }
                if (pion.isWhite()) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                //g.fillOval(pion.getX()*64, pion.getY()*64, 64, 64);
                g.fillOval(pion.getX() * windowW, pion.getY() * windowH, windowW, windowH);

            }
        }
    };
    Dimension size_of_window = plansza.rozmiar(plansza.getFrame());
    int windowH = (int) (size_of_window.getHeight() / 8);
    int windowW = (int) (size_of_window.getWidth() / 8);

    @Override
    public void mouseClicked (MouseEvent e){
        for (Pionek pion : Pionek.getPionki()) {
            if (pion.isActive()) {
                pion.przesun(e.getX() / windowW, e.getY() / windowH);
                pion.setActive(false);
                plansza.getFrame().repaint();
                return;
            }
        }
        if (Pionek.getPionekByCords(e.getX() / windowW, e.getY() / windowH) != null) {

            for (Pionek pion : Pionek.getPionki()) {
                pion.setActive(false);
            }

            Pionek.getPionekByCords(e.getX() / windowW, e.getY() / windowH).setActive(true);

            System.out.println(Pionek.bicia(Pionek.getPionekByCords(e.getX() / windowW, e.getY() / windowH).getX(),
                    Pionek.getPionekByCords(e.getX() / windowW, e.getY() / windowH).getY(),
                    Pionek.getPionekByCords(e.getX() / windowW, e.getY() / windowH).isWhite(),
                    Pionek.getPionekByCords(e.getX() / windowW, e.getY() / windowH).isQueen()));
            plansza.getFrame().repaint();
        }
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