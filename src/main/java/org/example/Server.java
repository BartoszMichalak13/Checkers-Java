package org.example;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class from which all starts
 */
public class Server {
//    public JFrame jFrame;
//
//    public void setjFrame(){
//        GameCreator gameCreator = new GameCreator();
//        gameCreator.setjFrame();
//        jFrame=gameCreator.getFrame();
//    }

    /**
     * Creates serwer for players
     * @param args arguments
     */
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            System.out.println("Server is listening on port 4444");
            //setjFrame();
            while (true) {
                Socket firstClient = serverSocket.accept();
                System.out.println("First client connected");
                System.out.println("Waiting for the second player");

                Socket secondClient = serverSocket.accept();
                System.out.println("Second client connected");

                //change it to gamev2 when all's ready
                Gamev2 g = new Gamev2(firstClient, secondClient);
                Thread gTh = new Thread(g);
                gTh.start();

                // TO DO: Musi byc dokldnie dwoch klientow

            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
//    public static JFrame getFrame(){
//        return jFrame;
//    }
}


