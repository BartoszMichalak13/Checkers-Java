package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plansza {

    private int size;
    private boolean bottomleftcorner;

    JFrame jFrame;
    final Dimension[] size_of_window = new Dimension[1];
    final static int[] windowH = new int[1];
    final static int[] windowW = new int[1];
    public Plansza(int size, boolean bottomleftcorner) {
        this.size=size;
        this.bottomleftcorner=bottomleftcorner;
    }
    public Plansza(){}
    //public JFrame getFrame() {return this.jFrame;}
    //public Dimension rozmiar() {return this.jFrame.getContentPane().getSize();}
    public int getwindowW(){return this.windowW[0];}
    public int getwindowH(){return this.windowH[0];}
    public void boardbuilder() {
        Main main =  new Main();
        jFrame = main.getFrame();


        jFrame.setBounds(10, 10, 1024, 720);
        //size_of_window = jFrame.getContentPane().getSize();
        //windowH = (int) (size_of_window.getHeight() / 8);
        //windowW = (int) (size_of_window.getWidth() / 8);
        //Ustawiam pionki
        for(int i = 0; i<size;i++){
            for(int j=0; j<size;j++){
                if(((i==0||i==2)&&j%2==1)||(i==1&&j%2==0)){
                    new Pionek(true, j, i);
                }else if(((i==7||i==5)&&j%2==0)||(i==6&&j%2==1)){
                    new Pionek(false, j, i);
                }
            }
        }
        //tworzę szachownice, myśle że to wszystko trzeba będzie dać do osobnych klas, ale narazie
        //jestem w programistycznym cugu i robie cokolwiek byle działało, potem to sie ogarnie.
        JPanel jPanel = new JPanel() {
            public void paint(Graphics g) {
                size_of_window[0] = jFrame.getContentPane().getSize();
                windowH[0] = (int) (size_of_window[0].getHeight() / size);
                windowW[0] = (int) (size_of_window[0].getWidth() / size);
                for(int y=0; y<size;y++){
                    for(int x=0; x<size; x++){
                        if((x%2==0&&y%2==1)||(y%2==0&&x%2==1)){
                            g.setColor(Color.DARK_GRAY);
                        }
                        else{
                            g.setColor(Color.lightGray);
                        }
                        g.fillRect(
                                x* windowW[0],
                                y* windowH[0],
                                windowW[0],
                                windowH[0]
                        );
                    }
                }
                for(Pionek pion : Pionek.getPionki() ){
                    if(pion.isWhite()){
                        g.setColor(Color.WHITE);
                    }else{
                        g.setColor(Color.BLACK);
                    }
                    g.fillOval(
                            pion.getX()* windowW[0],
                            pion.getY()* windowH[0],
                            windowW[0], windowH[0]
                    );
                }
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
        Main.CheckerBoardHandler CBH= new Main.CheckerBoardHandler();
        jPanel.addMouseListener(CBH);
        //jFrame.setPreferredSize(new Dimension(1024, 720));
        ///jFrame.pack();
        //jFrame.setLayout(gridLayout);
        //jFrame.setDefaultCloseOperation(3);
        //jFrame.setVisible(true);
        jFrame.add(jPanel);
        jFrame.repaint();
        jFrame.setVisible(true);
    }
}