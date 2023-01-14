package org.example;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plansza {
    JPanel jPanel;

    private int size;
    int pawnnumber;

    int[] x= new int[pawnnumber];;
    int[] y= new int[pawnnumber];;
    boolean[] isWhite= new boolean[pawnnumber];;

    Gamemain gamemain;
    final Dimension[] size_of_window = new Dimension[1];
    final static int[] windowH = new int[1];
    final static int[] windowW = new int[1];
    public Plansza(int size, int[] x, int[] y,boolean[] isWhite, int pawnnumber) {
        System.out.println("TEST1");
        this.size=size;
        this.pawnnumber=pawnnumber;
        System.out.println("TEST2");
        //this.isWhite = new boolean[pawnnumber];
        //this.x= new int[pawnnumber];
        //this.y= new int[pawnnumber];
//        for(int i=0; i<x.length; i++){
//            System.out.println("TEST3");
//            this.isWhite[i]=isWhite[i];
//            System.out.println("TEST4");
//
//            this.x[i] = x[i];
//            System.out.println("TEST5");
//
//            this.y[i] = y[i];
//            System.out.println("TEST6");
//
//        }
        System.out.println("TEST7");

        this.isWhite=isWhite;
        System.out.println("TEST8");
        this.x = x;
        System.out.println("TEST9");
        this.y = y;
        System.out.println("TEST10");
    }
    public Plansza(int size) {
        this.size=size;
        this.pawnnumber=size*(size/2-1);
        //this.x=x;
        //this.y=y;
    }
    public Plansza(){}
    public int getwindowW(){return this.windowW[0];}
    public int getwindowH(){return this.windowH[0];}

    public void boardbuilder() {
        //System.out.println(x[9]);
        Gamemain main =  new Gamemain();
        gamemain=main.getGamemain();
        if(gamemain.isAncestorOf(jPanel)) {
            gamemain.remove(jPanel);//IF THERE IS ANY
        }
        gamemain.setBounds(10, 10, 1024, 720);
        //gamemain.setsize(size);
         jPanel = new JPanel() {
            public void paint(Graphics g) {
                size_of_window[0] = gamemain.getContentPane().getSize();
                windowH[0] = (int) (size_of_window[0].getHeight() / size);
                windowW[0] = (int) (size_of_window[0].getWidth() / size);
                for(int y=0; y<size;y++){
                    for(int x=0; x<size; x++){
                        if((x%2==0&&y%2==1)||(y%2==0&&x%2==1)){
                                g.setColor(Color.getHSBColor(25.F,1.F,0.5F));
                        }
                        else{
                                g.setColor(Color.getHSBColor(360.F,0.2F,0.7F));
                        }
                        g.fillRect(
                                x* windowW[0],
                                y* windowH[0],
                                windowW[0],
                                windowH[0]
                        );
                    }
                }
                //BELOWE LOOPS PION DEPENDENT
                //TO BE MOVED TO SERVER or not?
                for(int i=0; i<x.length;i++){
                    //System.out.println(pion);
                    if(isWhite[i]){
                        g.setColor(Color.WHITE);
                    }else{
                        g.setColor(Color.BLACK);
                    }
                    g.fillOval(
                            x[i]* windowW[0],
                            y[i]* windowH[0],
                            windowW[0], windowH[0]
                    );
                }
                //TO BE MOVED TO SERVER or not
                if(Pionek.getActivePionek()!=null){
                    g.setColor(Color.YELLOW);
                    g.fillRect(
                            Pionek.getActivePionek().getX()* windowW[0],
                            Pionek.getActivePionek().getY()* windowH[0],
                            windowW[0],
                            windowH[0]
                    );
                    if(Pionek.getActivePionek().isWhite()){
                        g.setColor(Color.WHITE);
                    }else{
                        g.setColor(Color.BLACK);
                    }
                    g.fillOval(
                            Pionek.getActivePionek().getX()* windowW[0],
                            Pionek.getActivePionek().getY()* windowH[0],
                            windowW[0],
                            windowH[0]
                    );
                    for(int i=0; i<Pionek.getActivePionek().legalneKafelki().size(); i+=2){
                        g.setColor(Color.green);
                        g.fillRect(
                                Pionek.getActivePionek().legalneKafelki().get(i)*windowW[0],
                                Pionek.getActivePionek().legalneKafelki().get(i+1)* windowH[0],
                                windowW[0],
                                windowH[0]
                        );
                    }
                }
            }
        };
        Gamemain.CheckerBoardHandler CBH= gamemain.new CheckerBoardHandler();
        jPanel.addMouseListener(CBH);
        gamemain.add(jPanel);
        gamemain.repaint();
        gamemain.setVisible(true);
    }
    //Wysylam i maluje Pionki
    //LATER aktywny pionek + legale kafelki
//    public void pionekpainter(){
//        jPanel {
//            public void paint(Graphics g) {
//                for (Pionek pion : Pionek.getPionki()) {
//                    //System.out.println(pion);
//                    if (pion.isWhite()) {
//                        g.setColor(Color.WHITE);
//                    } else {
//                        g.setColor(Color.BLACK);
//                    }
//                    g.fillOval(
//                            pion.getX() * windowW[0],
//                            pion.getY() * windowH[0],
//                            windowW[0], windowH[0]
//                    );
//                }
//            }
//        }
//    }
}