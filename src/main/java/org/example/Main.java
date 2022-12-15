package org.example;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        //new Plansza().boardbuilder();
    //}
        JFrame jFrame = new JFrame();
        jFrame.setBounds(10, 10, 512, 540);
        final Dimension[] size_of_window = new Dimension[1];
        final int[] windowH = new int[1];
        final int[] windowW = new int[1];
        //Ustawiam pionki
        for(int i = 0; i<8;i++){
            for(int j=0; j<8;j++){
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
                windowH[0] = (int) (size_of_window[0].getHeight() / 8);
                windowW[0] = (int) (size_of_window[0].getWidth() / 8);
                for(int y=0; y<8;y++){
                    for(int x=0; x<8; x++){
                        if((x%2==0&&y%2==1)||(y%2==0&&x%2==1)){
                            g.setColor(Color.DARK_GRAY);
                        }
                        else{
                            g.setColor(Color.lightGray);
                        }
                        g.fillRect(x* windowW[0], y* windowH[0], windowW[0], windowH[0]);
                    }
                }
                for(Pionek pion : Pionek.getPionki() ){
                    if(pion.isActive()){
                        g.setColor(Color.YELLOW);
                        g.fillRect(pion.getX()* windowW[0], pion.getY()* windowH[0], windowW[0], windowH[0]);
                    }
                    if(pion.isWhite()){
                        g.setColor(Color.WHITE);
                    }else{
                        g.setColor(Color.BLACK);
                    }
                    g.fillOval(pion.getX()* windowW[0], pion.getY()* windowH[0], windowW[0], windowH[0]);
                }
            }
        };
        jPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                for(Pionek pion:Pionek.getPionki()){
                    if(pion.isActive()){
                        pion.przesun(e.getX()/windowW[0],e.getY()/windowH[0]);
                        pion.setActive(false);
                        jFrame.repaint();
                        return;
                    }
                }
                if(Pionek.getPionekByCords(e.getX()/windowW[0],e.getY()/windowH[0])!=null){

                    for(Pionek pion:Pionek.getPionki()){
                        pion.setActive(false);
                    }

                    Pionek.getPionekByCords(e.getX()/windowW[0],e.getY()/windowH[0]).setActive(true);

                    System.out.println(Pionek.bicia(Pionek.getPionekByCords(e.getX()/windowW[0],e.getY()/windowH[0]).getX(),
                            Pionek.getPionekByCords(e.getX()/windowW[0],e.getY()/windowH[0]).getY(),
                            Pionek.getPionekByCords(e.getX()/windowW[0],e.getY()/windowH[0]).isWhite(),
                            Pionek.getPionekByCords(e.getX()/windowW[0],e.getY()/windowH[0]).isQueen()));
                    jFrame.repaint();
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
        });

        class ActionHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        }

        JMenuBar menuBar;
        JMenu menu;
        JRadioButtonMenuItem rbMenuItem;


//Create the menu bar.
        menuBar = new JMenuBar();

//Build the first menu.
        menu = new JMenu("Checkers");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

//a group of radio button menu items

        
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("warcaby angielskie");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());
        menu.addSeparator();
        rbMenuItem = new JRadioButtonMenuItem("warcaby włoskie");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());
        menu.addSeparator();
        rbMenuItem = new JRadioButtonMenuItem("warcaby hiszpańskie");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());
        menu.addSeparator();
        rbMenuItem = new JRadioButtonMenuItem("warcaby niemieckie");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());
        menu.addSeparator();
        rbMenuItem = new JRadioButtonMenuItem("warcaby rosyjskie");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());
        menu.addSeparator();
        rbMenuItem = new JRadioButtonMenuItem("warcaby polskie");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());
        menu.addSeparator();
        rbMenuItem = new JRadioButtonMenuItem("warcaby kanadyjskie");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());
        menu.addSeparator();
        rbMenuItem = new JRadioButtonMenuItem("warcaby tureckie");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {

            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        jFrame.setJMenuBar(menuBar);
        jFrame.add(jPanel);

        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }

}
