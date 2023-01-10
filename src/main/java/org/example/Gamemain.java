package org.example;

import org.example.builders.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
//SEND I RECEIVE NIE DO KONCA TAK JAKBYM CHCIAL
//ZROB MENU W CONSTRUKTORZE + NAPRAWIC


public class Gamemain implements Runnable{

    static JFrame jFrame;
    static JLabel beforethegame;
    static JMenuBar menuBar;

    public JFrame getFrame() {return this.jFrame;}

    JLabel msg;

    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    private int player;

    public final static int PLAYER1 = 1;
    public final static int PLAYER2 = 2;

    public final static int ACTIVE = 0;
    public final static int NONACTIVE = 1;
    private  static int actualPlayer = PLAYER1;

    private static int showing = ACTIVE;
    Gamemain(){

    }
    private void receive(){
        try {
            // Odbieranie z serwera pionkow
            /*
            String str = in.readLine();
            output.setText(str);
            msg.setText("My turn");
            send.setEnabled(true);
            input.setText("");
            input.requestFocus();

             */
            jFrame.repaint();
        }
        //catch (IOException e) {
        catch (Exception e) {
            System.out.println("Read failed"); System.exit(1);
        }
    }

    /*
    Połaczenie z socketem
     */
    public void listenSocket() {
        try {
            socket = new Socket("localhost", 4444);
            // Inicjalizacja wysylania do serwera
            out = new PrintWriter(socket.getOutputStream(), true);
            // Inicjalizacja odbierania z serwera
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }

    /*
        Poczatkowe ustawienia klienta. Ustalenie ktory socket jest ktorym kliente
    */
    private void startThread() {
        Thread gTh = new Thread(this);
        gTh.start();
    }
    private void receiveInitFromServer() {
        //jFrame.a
        msg=new JLabel("Status");
        menuBar.add(msg);
        try {
            player = Integer.parseInt(in.readLine());
            if (player == PLAYER1) {
                System.out.println("player");
                System.out.println(player);
                msg.setText("My Turn");
            } else {
                System.out.println("player");
                System.out.println(player);
                msg.setText("Opposite turn");
                //send.setEnabled(false);
            }
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(1);
        }
    }
     private void send(){
        // Wysylanie do serwera
        /*
        out.println(input.getText());
        msg.setText("OppositeTurn");
        send.setEnabled(false);
        input.setText("");
        input.requestFocus();
        */
         jFrame.repaint();
         showing = ACTIVE;
         actualPlayer = player;

        //we will move this below into f1/f2
        //actualPlayer = player;
    }
    @Override
    public void run() {
        if (player==PLAYER1) {
            f1();
        }
        else{
            f2();
        }
    }
    void f1(){
        while(true) {
            synchronized (this) {
                if (actualPlayer== PLAYER1) {
                    try {
                        wait(10);
                    } catch (InterruptedException e) {
                    }
                }
                if (showing ==ACTIVE){
                    //actualPlayer = player;
                    System.out.println("actualPlayer");
                    System.out.println(actualPlayer);
                    receive();
                    showing =NONACTIVE;
                }
                notifyAll();
            }
        }
    }

    /// Metoda uruchamiana w run dla PLAYER2
    void f2(){
        while(true) {
            synchronized (this) {
                if (actualPlayer== PLAYER2) {
                    try {
                        wait(10);
                    } catch (InterruptedException e) {
                    }
                }
                if (showing ==ACTIVE){
                    //actualPlayer = player;
                    System.out.println("actualPlayer");
                    System.out.println(actualPlayer);
                    receive();
                    showing =NONACTIVE;
                }
                notifyAll();
            }
        }
    }
    ///CHWILA CHWILA A TO NIE WYSTARCZY PRZED RUCHEM REPAINT ZROBIC?
    class CheckerBoardHandler implements MouseListener {
        Plansza pl= new Plansza();
        @Override
        public void mouseClicked(MouseEvent e) {

            for (Pionek pion : Pionek.getPionki()) {
                if (pion.isActive()) {
                    pion.przesun(e.getX() / pl.getwindowW(), e.getY() / pl.getwindowH());
                    pion.setActive(false);
                    jFrame.repaint();
                    // Okey przesunelismy wiec generalnie to wyslalem pionki i
                    // robimy repainta u innego gracza
                    send();
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
        jFrame = new JFrame();
        jFrame.setBounds(10, 10, 800, 800);


        beforethegame = new JLabel(
                "WYBIERZ TRYB GRY",
                SwingConstants.CENTER
        );
        beforethegame.setFont(beforethegame.getFont().deriveFont(50.0F));



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
                menu.setEnabled(false);

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
        Gamemain gmain = new Gamemain();
        gmain.listenSocket();
        gmain.receiveInitFromServer();
        gmain.startThread();
    }

}
