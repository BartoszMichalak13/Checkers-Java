package org.example;

import org.example.builders.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
//ZROB TWORZENIE PIONKOW NA SERWERZE, DASZ RADE

public class Gamev2 implements Runnable {
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
    boolean turkishflag1=false;
    boolean bottomleftcorner1=false;
    int size1=0;

    public Gamev2() {

    }

    public Gamev2(Socket firstPlayer, Socket secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;


    }

    public void assignvalues(boolean turkishflag, boolean bottomleftcorner, int size){
        this.turkishflag1=turkishflag;
        this.bottomleftcorner1=bottomleftcorner;
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
            //IDEA: TWORZYMY PLANSZE ALE JEJ NIE WYSWIETLAMY
            //GRACZ WYSWIETLA I DODAJE LISTENER, JAK AKCJA TO REAKCJA


            //FUNKCJA WERYFIKUJE RUCHU
            //WYSYLA KLIENTOWI

            //TO WRZUCIMY DO OSOBNEJ FUNKCJI/NA DOL, ALE MUSI BYC ZALEZNE OD WYBORU MENU
//            for(int i =0; i<size/2-1; ++i)
//            gamev2.fillrow(i,turkishflag,bottomleftcorner,size);
            //boardstatus();
            String line;
            do {
                //no dobra trzeba tu wrzucic wysylanie listy pionkow a nie wiadomosci

                if (turn == SECOND) {
                    System.out.println("Jest5");

                    // Odbieranie od socketa
                    //pionki
                    //repaint
                    line = inS.readLine();
                    System.out.println("Jest6");

                    // Wypisywanie na serwerze
                    System.out.println(line);
                    // Wysylanie do socketa
                    // pionki
                    //outF.println("-> (" + line + ")");
                    outF.println(line);
                    //Gamemain main = new Gamemain();
                    //Gamemain.jFrame.repaint();
                    // NIE ROBIMY WIELU AUTOW BO APKA PRZYJMUJE TYLKO JEDEN,MOZNA INFO DO
                    // STRINGA DODAC KTORY PRZESLEMY W ODP MOM
//                    outS.println("STOP");
//                    outF.println("RUN");
                    turn = FIRST;
                }
//DODAC DO KONSTRUKTORA SIZE I IS POLISH
                if (turn == FIRST) {
                    // IDEA: Client samsobie wyświetla
                    // Odbieranie od socketa
                    line = inF.readLine();
                    if(line.contains("warcaby")) {
                        werecreated = true;
                        System.out.println("Jest1");
                        if (line.contains("Stworz Pionki")) {
                            for (int i = 0; i < size1 / 2 - 1; ++i)
                                fillrow(i, turkishflag1, bottomleftcorner1, size1);
                            System.out.println("Jest2");
                        }
                    }
                    else{}
                        //werecreated=true;


                        System.out.println("Jest3");

                    outS.println(line);
                    System.out.println("Jest4");

                    // Wypisywanie na serwerze
                    System.out.println(line);
                    // Wysylanie do socketa

                    //Gamemain.jFrame.repaint();
                    //outF.println("STOP");
                    //outS.println("RUN");
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
    public void fillrow(int i,boolean turkishflag, boolean bottomleftcorner, int size){
        if(!turkishflag)
            for(int j=0; j<size;j++){

                if((i%2==0&&j%2==1)||(i%2==1&&j%2==0)){
                    if(bottomleftcorner){
                        if(j+1==size)
                            new Pionek(true, 0, i);
                        new Pionek(true, j+1, i);
                    }
                    else
                        new Pionek(true, j, i);
                }else if(((i%2==0)&&j%2==0)||(i%2==1&&j%2==1)){
                    if(bottomleftcorner) {
                        if(j+1==size)
                            new Pionek(false, 0, size - 1 - i);
                        new Pionek(false, j + 1, size - 1 - i);

                    }
                    else
                        new Pionek(false, j, size-1-i);
                }
            }
        else if(i!=2){
            for(int j=0; j<size;j++){
                new Pionek(true, j, i+1);
                //new Pionek(true, j, 2);

                new Pionek(false, j, size-(i+2));
                //new Pionek(false, j, 6);
            }
        }
    }
}