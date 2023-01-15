package org.example;

import org.example.builders.*;
import org.example.decoders.DecoderServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
//ZROB TWORZENIE PIONKOW NA SERWERZE, DASZ RADE

public class Gamev2 implements Runnable {
     static Gamev2 gamev2 = new Gamev2();
     Boolean isPolish;
    Boolean werecreated;

    public void setWerecreated(Boolean wc){
        this.werecreated=wc;
        System.out.println(werecreated);
    }
    public Boolean getWerecreated(){
        return werecreated;
    }
    private Socket firstPlayer;
    private Socket secondPlayer;

    public static JFrame jFrame;

    private final static int FIRST = 1;
    private final static int SECOND = 2;
    private static int turn = FIRST;
    int size1=0;
    public Gamev2() {
    }
    public Gamev2(Socket firstPlayer, Socket secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
    //DOEST WORK
    public void assignvalues( int size){
        this.size1=size;
    }
    @Override
    public void run() {
        //int i,


        try {
            //Inicjalizacja pobieranie od socketa dla player1
            InputStream inputF = firstPlayer.getInputStream();
            BufferedReader inF = new BufferedReader(new InputStreamReader(inputF));

            //Inicjalizacja pobieranie od socketa dla player2
            InputStream inputS = secondPlayer.getInputStream();
            BufferedReader inS = new BufferedReader(new InputStreamReader(inputS));

            //Inicjalizacja Wysylania do socketa dla player1
            OutputStream outputF = firstPlayer.getOutputStream();
            PrintWriter outF = new PrintWriter(outputF, true);

            //Inicjalizacja Wysylania do socketa dla player2
            OutputStream outputS = secondPlayer.getOutputStream();
            PrintWriter outS = new PrintWriter(outputS, true);

            outF.println("1");
            outS.println("2");
            String line;
            do {

                if (turn == SECOND) {
                    System.out.println("Jest DUZO");

                    line = inS.readLine();
                    System.out.println("Jest B DUZO");


                    System.out.println(line);

                    outF.println(line);

                    turn = FIRST;
                }
//DODAC DO KONSTRUKTORA SIZE I IS POLISH
                if (turn == FIRST) {
//ZAGLEBIMY SIE I DODAMY WIECEJ INF OUTF DLA RUCHU
                    Boolean GO=true;
                    String s="";
                    int x, y;
                    boolean isWhite;
                    while(GO) {
                        line = inF.readLine();
                        System.out.println(line);
                        if (line.contains("warcaby")) {
                            werecreated = true;
                            System.out.println("Jest1");
                            if (line.contains("Stworz Pionki")) {
                                String[] str= line.split(" ");
                                size1=Integer.parseInt(str[5]);
                                isPolish=Boolean.parseBoolean(str[6]);
                                for (int i = 0; i < size1 / 2 - 1; ++i) {
                                    fillrow(i, size1, isPolish);
                                    System.out.println("Halo"+i);

                                }
                                System.out.println("Jest2");
                            }
                            s="Leca pionki ";
                            //int x, y;
                            //boolean isWhite;
                            for (Pionek pionek : Pionek.getPionki()) {
                                isWhite=pionek.isWhite();
                                x = pionek.getX();
                                y = pionek.getY();
                                s += isWhite;
                                s += " ";
                                s += Integer.toString(x);
                                s += " ";
                                s += Integer.toString(y);
                                s += " ";
                                System.out.println("Jest5");

                            }
                            outF.println(s);
                            outS.println(line+s);
                        }else {
                            DecoderServer decoderServer = new DecoderServer();
                            s=decoderServer.decode(line);
                            outF.println(s);
                        }
//                        if(line.contains("Active")){
//                            String[]str= line.split(" ");
//                            int s1=Integer.parseInt(str[1]);
//                            int s2=Integer.parseInt(str[2]);
//                            //TODO: Dorobic przesylanie danych na plansze (tj wspolrzedne pionkow, czy active, czy damka, czy white)
//                            for (Pionek pion : Pionek.getPionki()) {
//                                if (pion.isActive()) {
//                                    pion.move(s1, s2);
//                                    pion.setActive(false);
//                                    //wyslij wiadomosc o repaincie
//                                    //heh nie tak prosto info tez musimy wyslac odnosnie podswietlenia pionka
//                                    /////tu wysylamy pionki jeszcze raz
//                                    s="R ";
//                                    for (Pionek pionek : Pionek.getPionki()) {
//                                        isWhite=pionek.isWhite();
//                                        x = pionek.getX();
//                                        y = pionek.getY();
//                                        s += isWhite;
//                                        s += " ";
//                                        s += Integer.toString(x);
//                                        s += " ";
//                                        s += Integer.toString(y);
//                                        s += " ";
//                                        System.out.println("Jest5");
//
//                                    }
//                                    outF.println(s);
//                                    //gamemain.repaint();
//                                    return;
//                                }
//                            }
//                            if (Pionek.getPionekByCords(s1, s2) != null) {
//
//                                for (Pionek pion : Pionek.getPionki()) {
//                                    pion.setActive(false);
//                                }
//
//                                Pionek.getPionekByCords(s1, s2).setActive(true);
//                                ///wysylamy ze jest active
//
//                                outF.println("R");
//                                //gamemain.repaint();
//                            }
//                        }

                        System.out.println("Jest3");
                        //outS.println(line);
                        System.out.println("Jest4");
                        //Pionek pionek = null;
                        //pionek.getpionki();


                    System.out.println("Jest6");


                    System.out.println("Jest7");


                        //System.out.println(line);
                    }
                    turn = SECOND;
                }
            } while (true);

        } catch (IOException ex) {
            System.err.println("ex");
        }
    }

    private void sendMove(DataOutputStream out, String text) throws IOException {
        out.writeChars(text);

    }
    public void fillrow(int i, int size, boolean isPolish){
        System.out.println("Am I here?");
            for(int j=0; j<size;j++){
                if((i%2==0&&j%2==1)||(i%2==1&&j%2==0)){
                    //(isWhite, x, y, size1, isPolish)
                        new Pionek(true, j, i, size1, isPolish);
                }else if(((i%2==0)&&j%2==0)||(i%2==1&&j%2==1)){
                        new Pionek(false, j, size-1-i, size1, isPolish);
                }
            }

    }
}