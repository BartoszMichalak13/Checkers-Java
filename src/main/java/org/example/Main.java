package org.example;

import org.example.builders.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main {
    static JFrame jFrame;
    static JLabel beforethegame;
    public JFrame getFrame() {return this.jFrame;}
    static class CheckerBoardHandler implements MouseListener {
        Plansza pl= new Plansza();
        @Override
        public void mouseClicked(MouseEvent e) {

            for (Pionek pion : Pionek.getPionki()) {
                if (pion.isActive()) {
                    pion.przesun(e.getX() / pl.getwindowW(), e.getY() / pl.getwindowH());
                    pion.setActive(false);
                    jFrame.repaint();
                    return;
                }
            }
            if (Pionek.getPionekByCords(e.getX() / pl.getwindowW(), e.getY() / pl.getwindowH()) != null) {

                for (Pionek pion : Pionek.getPionki()) {
                    pion.setActive(false);
                }

                Pionek.getPionekByCords(e.getX() / pl.getwindowW(), e.getY() / pl.getwindowH()).setActive(true);
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
    }


    public static void main(String[] args) {
        //new Plansza().boardbuilder();
    //}
        jFrame = new JFrame();
        jFrame.setBounds(10, 10, 800, 800);

        //PLAN:
        /* DUŻY NAPIS WYBIERZ TRYB
        *  PO WYBRANIU TRYBU Z MENU ODPALA SIE ODPOWIEDNI BUILDER
        *  PRZESYLA DANE DO PLANSZY GDZIE TWORZY SIE PLANSZA I PIONKI
        *  I DO PIONKA ZEBY WIADOMO BYLO JAKIE SA ZASADY (jak bic, co
        *  moze damka itd.)
        *
        * */
        beforethegame = new JLabel(
                "WYBIERZ TRYB GRY",
                SwingConstants.CENTER
        );
        beforethegame.setFont(beforethegame.getFont().deriveFont(50.0F));



        JMenuBar menuBar;
        JMenu menu;
        JRadioButtonMenuItem rbMenuItem;


//Create the menu bar.
        menuBar = new JMenuBar();

//Build the first menu.
        menu = new JMenu("Game Modes");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

//a group of radio button menu items

        class ActionHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s= e.getActionCommand();
                System.out.println(s);
                jFrame.remove(beforethegame);
                if(s.equals("warcaby angielskie"))
                    new EnglishBuilder().build();
                else if(s.equals("warcaby włoskie"))
                    new ItalianBuilder().build();
                else if(s.equals("warcaby hiszpańskie"))
                    new SpanishBuilder().build();
                else if(s.equals("warcaby niemieckie"))
                    new GermanBuilder().build();
                else if(s.equals("warcaby rosyjskie"))
                    new RussianBuilder().build();
                else if(s.equals("warcaby polskie"))
                    new PolishBuilder().build();
                else if(s.equals("warcaby kanadyjskie"))
                    new CanadianBuilder().build();
                else if(s.equals("warcaby tureckie"))
                    new TurkishBuilder().build();
                else if(s.equals("warcaby brazylijskie"))
                    new BrazilianBuilder().build();

            }
        }
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
        menu.addSeparator();
        rbMenuItem = new JRadioButtonMenuItem("warcaby brazylijskie");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem.addActionListener(new ActionHandler());

        jFrame.setJMenuBar(menuBar);
        jFrame.setLayout(new BorderLayout());
        jFrame.add(beforethegame, BorderLayout.CENTER);
        //jFrame.add(jPanel);

        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }

}
