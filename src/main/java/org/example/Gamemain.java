package org.example;

import org.example.builders.*;
import org.example.decoders.DecoderClient;

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

//ZAMIAST TWORZYC PIONKI W BUILDERZE ROB TO NA SERWERZE, TJ WYSLIJ WIADOMOSC NA SERWER I NIECH ON STWORZY PIONKI


public class Gamemain extends JFrame implements Runnable{


    //REMOVE THIS VARIABLE LATER
    boolean wasclicked=false;
    int size;
    Plansza pl;
    int pawnnumber;
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

    DecoderClient decoderClient;
    public final static int ACTIVE = 0;
    public final static int NONACTIVE = 1;
    private  static int actualPlayer = PLAYER1;

    private static int showing = ACTIVE;
    public int getPlayer(){
        return this.player;
    }
    public Gamemain(){

    }
    public void setsize(int size){
        this.size=size;
        this.pawnnumber=size*(size/2-1);
    }
    public Gamemain getGamemain(){
        return this.gamemain;
    }
    public void removebeforethegame(){
        gamemain.remove(beforethegame);
    }

    public void send(String s){
        out.println(s);
        showing = ACTIVE;
        actualPlayer = player;
    }
    private void receive(){
        try {
            boolean wasA=true;
            while(wasA){
                String str = in.readLine();
                System.out.println(str);
                decoderClient.decode(str);
                String[] s = str.split(" ");
                if (!str.contains("I")) {
                    if (str.contains("warcaby")) {
                        gamemain.removebeforethegame();
                        if (str.contains("warcaby angielskie"))
                            new EnglishBuilder().build(true, str, player);
                        else if (str.contains("warcaby polskie"))
                            new PolishBuilder().build(true, str, player);
                        else if (str.contains("warcaby brazylijskie"))
                            new BrazilianBuilder().build(true, str, player);
                        wasA=false;
                    }
                    if (str.contains("C")) {
                        int var = s.length;
                        var = var - 3;
                        int[] xLegal = new int[var / 2];
                        int[] yLegal = new int[var / 2];
                        int Li = 0;
                        for (int i = 2; i < var + 2; i = i + 2) {
                            xLegal[Li] = Integer.parseInt(s[i]);
                            yLegal[Li] = Integer.parseInt(s[i + 1]);
                            Li++;
                        }
                        //siema plansza mam legalne kafelki
                        pl.getLegalKaf(xLegal, yLegal);
                        gamemain.repaint();
                        wasA=false;
                    } else if (str.contains("A")) {
                        //mozna ustalic legal kafelki na puste

                        int var = s.length;
                        var = var - 1;
                        System.out.println(var);
                        System.out.println(var / 4);
                        boolean[] isWhite = new boolean[var / 4];
                        boolean[] isDamka = new boolean[var / 4];
                        int[] x = new int[var / 4];
                        int[] y = new int[var / 4];
                        int Li = 0;
                        for (int i = 1; i < var + 1; i = i + 4) {
                            if (s[i].equals("W")) {
                                isWhite[Li] = true;
                            } else {
                                isWhite[Li] = false;
                            }
                            if (s[i + 1].equals("P")) {
                                isDamka[Li] = false;
                            } else {
                                isDamka[Li] = true;
                            }
                            x[Li] = Integer.parseInt(s[i + 2]);
                            y[Li] = Integer.parseInt(s[i + 3]);
                            ++Li;
                        }
                        pl.getMove(isWhite, isDamka, x, y, null, null);
                        pl.getActive(-1,-1);
                        gamemain.repaint();

                        //showing = ACTIVE;
                    }
                    if (str.contains("Leca")) {
                        double v = s.length;
                        int offset;
                        if (player == 2) {
                            offset = 8;
                            v = (v - 8) / 3;
                        } else {
                            offset = 2;
                            v = (v - 2) / 3;
                        }
                        //REPAINT JPANEL?
                        int[] x = new int[pawnnumber];
                        int[] y = new int[pawnnumber];
                        boolean[] isWhite = new boolean[pawnnumber];

                        for (int i = 0; i < v; i++) {
                            System.out.println(s.length);
                            System.out.println(pawnnumber);
                            System.out.println(v);
                            String s1 = s[(i * 3) + offset];
                            System.out.println(s1);
                            String s2 = s[(i * 3) + offset + 1];
                            System.out.println(s2);
                            String s3 = s[(i * 3) + offset + 2];
                            System.out.println(s3);
                            isWhite[i] = Boolean.parseBoolean(s1);
                            System.out.println("HEJ");
                            x[i] = Integer.parseInt(s2);
                            System.out.println("HEJ");
                            y[i] = Integer.parseInt(s3);
                            System.out.println("HEJ");
                        }
                        pl = new Plansza(size, x, y, isWhite, pawnnumber);
                        pl.boardbuilder();
                        wasA=false;
                    }
                } else {
                    //usuwamy legalne kafelki
                    pl.getLegalKaf(null, null);
                    gamemain.repaint();
                    wasA=false;
                    //No nie wiem. Ruch byl niepoprawny, a to są warcaby... Może warto się nad sobą zastanowić?
                }
            }
        }
        //catch (IOException e) {
        catch (IOException e) {
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
                decoderClient = new DecoderClient(true);
                System.out.println("player");
                System.out.println(player);
                msg.setText("My Turn");
            } else {
                decoderClient = new DecoderClient(false);
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
                //showing=ACTIVE;
                if (actualPlayer== PLAYER1) {
                    try {
                        wait(10);
                        //receive();
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
                        //receive();
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
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Klik");
            String act="";
            int x=e.getX()/pl.getwindowW();
            int y=e.getY()/ pl.getwindowH();
            act += decoderClient.encrypt(x,y);
            pl.getActive(x,y);
            gamemain.repaint();
            gamemain.send(act);
            //wysylamy zapytanie czy pionek

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
