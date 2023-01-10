package org.example;

import org.example.builders.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
//KOMUNIKACJA MIEDZY GRACZAMI: BAZOWO TU MA BYC MAIN A NIE U GRACZA,


public class Gamev2 implements Runnable {

    private Socket firstPlayer;
    private Socket secondPlayer;

    public static JFrame jFrame;

    private final static int FIRST = 1;
    private final static int SECOND = 2;
    private static int turn = FIRST;

    public Gamev2() {

    }

    public Gamev2(Socket firstPlayer, Socket secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;


    }


//        public void fillrow(int i){
//            if(!turkishflag)
//                for(int j=0; j<size;j++){
//
//                    if((i%2==0&&j%2==1)||(i%2==1&&j%2==0)){
//                        if(bottomleftcorner){
//                            if(j+1==size)
//                                new Pionek(true, 0, i);
//                            new Pionek(true, j+1, i);
//                        }
//                        else
//                            new Pionek(true, j, i);
//                    }else if(((i%2==0)&&j%2==0)||(i%2==1&&j%2==1)){
//                        if(bottomleftcorner) {
//                            if(j+1==size)
//                                new Pionek(false, 0, size - 1 - i);
//                            new Pionek(false, j + 1, size - 1 - i);
//
//                        }
//                        else
//                            new Pionek(false, j, size-1-i);
//                    }
//                }
//            else if(i!=2){
//                for(int j=0; j<size;j++){
//                    new Pionek(true, j, i+1);
//                    //new Pionek(true, j, 2);
//
//                    new Pionek(false, j, size-(i+2));
//                    //new Pionek(false, j, 6);
//                }
//            }
//        }
    @Override
    public void run() {

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

            String line;
            do {
                //no dobra trzeba tu wrzucic wysylanie listy pionkow a nie wiadomosci

                if (turn == SECOND) {
                    // Odbieranie od socketa
                    //pionki
                    //repaint
                    line = inS.readLine();
                    // Wypisywanie na serwerze
                    System.out.println(line);
                    // Wysylanie do socketa
                    // pionki
                    outF.println("-> (" + line + ")");
                    //Gamemain main = new Gamemain();
                    //Gamemain.jFrame.repaint();
                    outS.println("STOP");
                    outF.println("RUN");
                    turn = FIRST;
                }

                if (turn == FIRST) {
                    // IDEA: Client samsobie wyświetla
                    // Odbieranie od socketa
                    line = inF.readLine();
                    // Wypisywanie na serwerze
                    System.out.println(line);
                    // Wysylanie do socketa
                    outS.println("-> (" + line + ")");
                    //Gamemain.jFrame.repaint();
                    outF.println("STOP");
                    outS.println("RUN");
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
}