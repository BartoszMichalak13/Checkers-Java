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

/**
 * Client application
 */
public class Gamemain extends JFrame implements Runnable{


    /**
     * size of plansza
     */
    int size;
    /**
     * instance of plansza
     */
    Plansza pl;
    /**
     * number of pawns
     */
    int pawnnumber;
    static Gamemain gamemain = new Gamemain();
    static JLabel beforethegame;
    static JMenuBar menuBar;

    /**
     * which player are we?
     */
    JLabel msg;

    /**
     * our socket
     */
    Socket socket;
    /**
     * our printwriter
     */
    PrintWriter out;
    /**
     * our bufferedreader
     */
    BufferedReader in;

    /**
     * what player are we
     */
    private int player;

    /**
     * number for player 1
     */
    public final static int PLAYER1 = 1;
    /**
     * number for player 2
     */
    public final static int PLAYER2 = 2;

    /**
     * instance of client decoder
     */
    DecoderClient decoderClient;
    /**
     * State of being active
     */
    public final static int ACTIVE = 0;
    /**
     * State of being nonactive
     */

    public final static int NONACTIVE = 1;
    private  static int actualPlayer = PLAYER1;

    private static int showing = ACTIVE;

    /**
     * gets Player number
     * @return player number
     */
    public int getPlayer(){
        return this.player;
    }

    /**
     * builder
     */
    public Gamemain(){

    }

    /**
     * sets plansza size and pawnnumber
     * @param size size of plansza
     */
    public void setsize(int size){
        this.size=size;
        this.pawnnumber=size*(size/2-1);
    }

    /**
     * gets this class object
     * @return gamemain
     */
    public Gamemain getGamemain(){
        return this.gamemain;
    }

    /**
     *  removes "wybierz gre"
     */
    public void removebeforethegame(){
        gamemain.remove(beforethegame);
    }

    /**
     * Send message to server
     * @param s message
     */
    public void send(String s){
        s= System.currentTimeMillis() +" "+ s;
        System.out.println(s);
        out.println(s);
        showing = ACTIVE;
        actualPlayer = player;
    }

    /**
     * receives and handles message from server
     */
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
                        boolean[] isWhite = new boolean[var / 4];
                        boolean[] isDamka = new boolean[var / 4];
                        int[] x = new int[var / 4];
                        int[] y = new int[var / 4];
                        int Li = 0;
                        int hit=0;
                        for (int i = 1; i < var + 1; i = i + 4) {
                            if(s[i].equals("HIT")){
                                hit=i;
                                break;
                            }
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
                        int[] xLegal=null;
                        int[] yLegal=null;
                        if(hit!=0) {
                            xLegal = new int[var / 2];
                            yLegal = new int[var / 2];
                            int i=hit;
                            int j=0;
                            while (!s[i].equals("S")) {
                                xLegal[j]= Integer.parseInt(s[i]);
                                yLegal[j]= Integer.parseInt(s[i+1]);
                                j++;
                                i=i+2;
                            }
                        }
                        pl.getMove(isWhite, isDamka, x, y, null, null);
                        pl.getLegalKaf(xLegal,yLegal);
                        pl.getActive(-1,-1);
                        gamemain.repaint();

                        //showing = ACTIVE;
                    }
                    if (str.contains("Leca")) {
                        double v = s.length;
                        int offset;
                        if (player == 2) {
                            offset = 8;
                            v = (v - 8) / 4;
                        } else {
                            offset = 2;
                            v = (v - 2) / 4;
                        }
                        //REPAINT JPANEL?
                        int[] x = new int[pawnnumber];
                        int[] y = new int[pawnnumber];
                        boolean[] isWhite = new boolean[pawnnumber];
                        boolean[] isDamka = new boolean[pawnnumber];
                        for (int i = 0; i < v; i++) {
                            String s1 = s[(i * 4) + offset];
                            String s2 = s[(i * 4) + offset + 1];
                            String s3 = s[(i * 4) + offset + 2];
                            String s4 = s[(i * 4) + offset + 3];
                            isWhite[i] = Boolean.parseBoolean(s1);
                            isDamka[i] = Boolean.parseBoolean(s2);
                            x[i] = Integer.parseInt(s3);
                            y[i] = Integer.parseInt(s4);
                        }
                        pl = new Plansza(size, x, y, isWhite, isDamka, pawnnumber);
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
        catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
    }

    /*
    Połaczenie z socketem
     */

    /**
     * Connects with socket
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

    /**
     * starts thread
     */
    private void startThread() {
        Thread gTh = new Thread(this);
        gTh.start();
    }

    /**
     * receives init from server
     */
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
                msg.setText("Player1");
            } else {
                decoderClient = new DecoderClient(false);
                System.out.println("player");
                System.out.println(player);
                msg.setText("Player2");
                //send.setEnabled(false);
            }
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(1);
        }
    }

    /**
     * runs multithreading
     */
    @Override
    public void run() {
        if (player==PLAYER1) {
            f1();
        }
        else{
            f2();
        }
    }

    /**
     * function to run for player1
     */
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
    /**
     * function to run for player1
     */
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

    /**
     * this is listenr of our moves on the checkerboard
     */
    class CheckerBoardHandler implements MouseListener {
        /**
         * checks if mouse was clicked
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Klik");
            String act="";
            int x=e.getX()/pl.getwindowW();
            int y=e.getY()/ pl.getwindowH();
            act += decoderClient.encrypt(x,y);
            pl.getActive(x,y);
            gamemain.repaint();
            System.out.println(actualPlayer);
//            if((actualPlayer==PLAYER2 && act.contains("B"))||(actualPlayer==PLAYER1 && act.contains("W"))) {
//                System.out.println("hej tu " + player);
//                System.out.println(actualPlayer);
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


    /**
     * it runs the show, creats menu etc
     * @param args arguments
     */
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
