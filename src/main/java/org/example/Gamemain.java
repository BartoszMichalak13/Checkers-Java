package org.example;

import com.sun.tools.javac.Main;
import org.example.builders.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
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

//ZAMIAST TWORZYC PIONKI W BUILDERZE ROB TO NA SERWERZE, TJ WYSLIJ WIADOMOSC NA SERWER I NIECH ON STWORZY PIONKI


public class Gamemain extends JFrame implements Runnable{

    static JFrame jFrame;
    Boolean werecreated=false;
    public Boolean setWerecreated(){
        this.werecreated= new Gamev2().getWerecreated();
        return this.werecreated;
    }
    static Gamemain gamemain = new Gamemain();
    static JLabel beforethegame;
    static JMenuBar menuBar;

    //public JFrame getFrame() {return this.jFrame;}

    JLabel msg;

    Socket socket;
    PrintWriter out;
    BufferedReader in;

    private int player;

    public final static int PLAYER1 = 1;
    public final static int PLAYER2 = 2;

    public final static int ACTIVE = 0;
    public final static int NONACTIVE = 1;
    private  static int actualPlayer = PLAYER1;

    private static int showing = ACTIVE;
    public Gamemain(){

    }

    public Gamemain getGamemain(){
        return this.gamemain;
    }
    public void removebeforethegame(){
        gamemain.remove(beforethegame);
    }

    public void send(String s){
        // Wysylanie do serwera
        out.println(s);
        showing = ACTIVE;
        actualPlayer = player;
    }
    private void receive(){
        try {
            String str = in.readLine();
            System.out.println(str);
            if(str.equals("STOP")){
                //gamemain.setEnabled(false);
            }
            if(str.equals("RUN")){
                //gamemain.setEnabled(true);
            }
            if(str.contains("warcaby")){
                System.out.println("TU1");
                gamemain.removebeforethegame();
                System.out.println("TU2");
                if (str.contains("warcaby angielskie"))
                    new EnglishBuilder().build(true,str);
                else if (str.contains("warcaby włoskie"))
                    new ItalianBuilder().build(true,str);
                else if (str.contains("warcaby hiszpańskie"))
                    new SpanishBuilder().build(true,str);
                else if (str.contains("warcaby niemieckie"))
                    new GermanBuilder().build(true,str);
                else if (str.contains("warcaby rosyjskie"))
                    new RussianBuilder().build(true,str);
                else if (str.contains("warcaby polskie"))
                    new PolishBuilder().build(true,str);
                else if (str.contains("warcaby kanadyjskie"))
                    new CanadianBuilder().build(true,str);
                else if (str.contains("warcaby tureckie"))
                    new TurkishBuilder().build(true,str);
                else if (str.contains("warcaby brazylijskie"))
                    new BrazilianBuilder().build(true,str);
                System.out.println("TU3");
                //new MenuCreator().getMenu().setEnabled(false);
                System.out.println("TU4");
            }
            //gamemain.repaint();
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
                    gamemain.repaint();
                    out.flush();
                    // Okey przesunelismy wiec generalnie to wyslalem pionki i
                    // robimy repainta u innego gracza
                    send("pion");
                    return;
                }
            }
            if (Pionek.getPionekByCords(e.getX() / pl.getwindowW(), e.getY() / pl.getwindowH()) != null) {

                for (Pionek pion : Pionek.getPionki()) {
                    pion.setActive(false);
                }

                Pionek.getPionekByCords(e.getX() / pl.getwindowW(), e.getY() / pl.getwindowH()).setActive(true);
                gamemain.repaint();
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }


    public static void main(String[] args) {
        //Gamemain gamemain= new Gamemain();
        //jFrame = new JFrame();
        gamemain.setBounds(10, 10, 800, 800);


        beforethegame = new JLabel(
                "WYBIERZ TRYB GRY",
                SwingConstants.CENTER
        );
        beforethegame.setFont(beforethegame.getFont().deriveFont(50.0F));
        MenuCreator menuCreator = new MenuCreator();
        //idk czy ok
        //ZAMIAST TEGO SPROBUJMY MOZE KOMUNIKACJI Z SERWEREM
        //THATSS NOT THE POINT, MENU ROIBIMY ZAWSZE, ALE NIE PIONKI, ALE Z KOLEJ KLIENT POTRZEBUJE MIEC STWORZONE KONKRETNE MENU

        menuCreator.createmenu();

        menuBar=menuCreator.getMenuBar();



        gamemain.setJMenuBar(menuBar);
        gamemain.setLayout(new BorderLayout());
        gamemain.add(beforethegame, BorderLayout.CENTER);
        //jFrame.add(jPanel);

        gamemain.setDefaultCloseOperation(3);
        gamemain.setVisible(true);
        gamemain.listenSocket();
        gamemain.receiveInitFromServer();
        gamemain.startThread();

    }

}
