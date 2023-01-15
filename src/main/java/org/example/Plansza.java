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

    int[] x= new int[pawnnumber];
    int[] y= new int[pawnnumber];
    boolean[] isWhite= new boolean[pawnnumber];
    boolean[] isActive= new boolean[pawnnumber];
    boolean[] isDamka= new boolean[pawnnumber];

    int[] xLegal;
    int[] yLegal;

    Gamemain gamemain;
    final Dimension[] size_of_window = new Dimension[1];
    final static int[] windowH = new int[1];
    final static int[] windowW = new int[1];
    int xActive=-1;
    int yActive=-1;

    /**
     *  Gets Active pionek
     * @param xActive x of pionek
     * @param yActive y of pionek
     */
    public void getActive(int xActive, int yActive){
        this.xActive=xActive;
        this.yActive=yActive;
    }

    /**
     *  Gets Legalkafelki
     * @param xLegal x of kafelka
     * @param yLegal y of kafelka
     */
    public void getLegalKaf(int[] xLegal, int[] yLegal){
        this.xLegal=xLegal;
        this.yLegal=yLegal;
    }

    /**
     * Gets info to move the Pionek
     * @param isWhite is Pionek White
     * @param isDamka is Pionek Damka
     * @param x Pionek's x
     * @param y Pionek's y
     * @param xLegal Legal kafelek's x
     * @param yLegal Legal kafelek's x
     */
    public void getMove(boolean[] isWhite, boolean[] isDamka, int[] x, int[] y, int[] xLegal, int[] yLegal){
        this.isWhite=isWhite;
        this.isDamka=isDamka;
        this.x=x;
        this.y=y;
        this.xLegal=xLegal;
        this.yLegal=yLegal;
    }

    /**
     * Plansza constructor
     * @param size size of plansza
     * @param x x of pionek
     * @param y y of pionek
     * @param isWhite is Pionek White
     * @param isDamka is Pionek Damka
     * @param pawnnumber number of pawns
     */
    public Plansza(int size, int[] x, int[] y,boolean[] isWhite, boolean[] isDamka, int pawnnumber) {
        this.size=size;
        this.pawnnumber=pawnnumber;
        this.isWhite=isWhite;
        this.isDamka=isDamka;
        this.x = x;
        this.y = y;
    }

    /**
     *  gets width of window
     * @return windowW
     */
    public int getwindowW(){return this.windowW[0];}

    /**
     * gets height of window
     * @return windowH
     */
    public int getwindowH(){return this.windowH[0];}

    /**
     *  builds checker board
     */
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
             /**
              *  Paints our checkerboard (colors, pawns and stuff)
              * @param g  the <code>Graphics</code> context in which to paint
              */
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
                        if(isDamka[i]){
                            g.setColor(Color.LIGHT_GRAY);
                        }else{
                            g.setColor(Color.WHITE);
                        }
                    }else{
                        if(isDamka[i]){
                            g.setColor(Color.DARK_GRAY);
                        }else {
                            g.setColor(Color.BLACK);
                        }
                    }
                    g.fillOval(
                            x[i]* windowW[0],
                            y[i]* windowH[0],
                            windowW[0], windowH[0]
                    );
                }
                //TO BE MOVED TO SERVER or not
                if(xActive!=-1) {
                    for (int i = 0; i < x.length; i++) {
                        if(x[i]==xActive && y[i]==yActive) {
                            g.setColor(Color.YELLOW);
                            g.fillRect(
                                    xActive * windowW[0],
                                    yActive * windowH[0],
                                    windowW[0],
                                    windowH[0]
                            );
                            if (isWhite[i]) {
                                g.setColor(Color.WHITE);
                            } else {
                                g.setColor(Color.BLACK);
                            }
                            g.fillOval(
                                    xActive * windowW[0],
                                    yActive * windowH[0],
                                    windowW[0],
                                    windowH[0]
                            );
                        }
                    }
//                    xActive=-1;
//                    yActive=-1;
                }
                //change is inevitable
                if (xLegal != null) {
                    for (int i = 0; i < xLegal.length; ++i) {
                        g.setColor(Color.green);
                        g.fillRect(
                                xLegal[i] * windowW[0],
                                yLegal[i] * windowH[0],
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
}