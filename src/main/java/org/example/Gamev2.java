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
            DecoderServer decoderServer = new DecoderServer();
            String message="";
            String line;
            do {

                if (turn == SECOND) {
                    long time= System.currentTimeMillis();
                    System.out.println("HI 2");

                    boolean GO=true;
                    String s="";

                    int x, y;
                    boolean isWhite;
                    while(GO) {
                        line = inS.readLine();
                        System.out.println(message);
                        if(!message.isEmpty()){
                            outS.println(message);
                            System.out.println("2");
                        }
                        message="";
                        System.out.println(line);
                        String[] str = line.split(" ");
                        if(!(Long.parseLong(str[0]) <= time)){
                            line = "";
                            for(int i = 0; i+1 < str.length; i++){
                                line = line + str[i+1];
                                line = line + " ";
                            }
                            s = decoderServer.decode(line);
                            str = s.split(" ");
                            if (str[0].equals("A")) {
                                message=s;
                                System.out.println(message);


                                //outF.println(s);
                            }
                            outS.println(s);
                            if (decoderServer.isWhiteTurn()) {
                                GO = false;
                                System.out.println("GO 2");
                            }
                        }
                    }

                    turn = FIRST;
                }
//DODAC DO KONSTRUKTORA SIZE I IS POLISH
                if (turn == FIRST) {
                    long time= System.currentTimeMillis();

                    System.out.println("HI 1");
//ZAGLEBIMY SIE I DODAMY WIECEJ INF OUTF DLA RUCHU
                    int whichtime=0;
                    boolean GO=true;
                    String s="";
                    int x, y;
                    boolean isWhite;
                    while(GO) {
                        line = inF.readLine();
                        System.out.println(message);
                        if(!message.isEmpty()){
                            outF.println(message);
                            System.out.println("2");
                        }
                        message="";
                        System.out.println(line);
                        if (line.contains("warcaby")) {
                            werecreated = true;
                            if (line.contains("Stworz Pionki")) {
                                String[] str= line.split(" ");
                                line = "";
                                for(int i = 0; i+1 < str.length; i++){
                                    line = line + str[i+1];
                                    line = line + " ";
                                }
                                str=line.split(" ");
                                size1=Integer.parseInt(str[4]);
                                isPolish=Boolean.parseBoolean(str[5]);
                                for (int i = 0; i < size1 / 2 - 1; ++i) {
                                    fillrow(i, size1, isPolish);
                                }
                            }

                            s="Leca pionki ";
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
                            }
                            outF.println(s);
                            outS.println(line+s);
                        }else {
                            String[] str = line.split(" ");
                            if(!(Long.parseLong(str[0]) <= time)) {
                                line = "";
                                for (int i = 0; i + 1 < str.length; i++) {
                                    line = line + str[i + 1];
                                    line = line + " ";
                                }
                                //str = line.split(" ");
                                System.out.println(line);
                                s = decoderServer.decode(line);
                                str = s.split(" ");
                                if (str[0].equals("A")) {
                                    //outS.println(s);
                                    message=s;
                                    System.out.println(message);
                                }
                                outF.println(s);
                            }
                        }
                        if(!decoderServer.isWhiteTurn()){
                            GO=false;
                            System.out.println("GO 1");
                        }
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