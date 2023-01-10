//package org.example;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.net.UnknownHostException;
////MAIN + CLIENT OD MACYNY
////APLIKACJA KLIENCKA PLAN: BRAC FRAME Z GAMEV2, DAC TU LISTENER,
////bez extends jFrame?
//public class Client  implements ActionListener, Runnable {
//    Label msg;
//    Label output;
//    Button send;//ZAMIAST TEGO MAMY NASZA PLANSZE
//
//
//    TextField input;
//    Socket socket = null;
//    PrintWriter out = null;
//    BufferedReader in = null;
//
//    private int player;
//
//    public final static int PLAYER1 = 1;
//    public final static int PLAYER2 = 2;
//
//    public final static int ACTIVE = 0;
//    public final static int NONACTIVE = 1;
//    private  static int actualPlayer = PLAYER1;
//    static JFrame jFrame;
//    private static int showing = ACTIVE;
//
//    //PLAN DZIALANIA:
//    // MAMY FRAME Z GRAMEV2
//    //LISTENER, ZWRACAMY PO RUCHU PIONKI
//    //BD GIT
//    Client( ) {
////        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
////        msg = new Label("Status");
////        input = new TextField(20);
////        output = new Label();
////        output.setBackground(Color.white);
////        send = new Button("Send");
////        send.addActionListener(this);
////        setLayout(new GridLayout(4, 1));
////        add(msg);
////        add(input);
////        add(send);
////        add(output);
//    }
//
//    public void frameinit(JFrame gameframe){
//        Gamev2 gamev2= new Gamev2();
//        jFrame=gamev2.sendFrame();
//    }
//    public void actionPerformed(ActionEvent event) {
//        if (event.getSource() == send) {
//            send();
//        }
//    }
//
//    private void send(){
//        // Wysylanie do serwera
////        out.println(input.getText());
////        msg.setText("OppositeTurn");
////        send.setEnabled(false);
////        input.setText("");
////        input.requestFocus();
//        showing = ACTIVE;
//        actualPlayer = player;
//    }
//
//    private void receive(){
//        try {
//            // Odbieranie z serwera
////            String str = in.readLine();
////            output.setText(str);
////            msg.setText("My turn");
////            send.setEnabled(true);
////            input.setText("");
////            input.requestFocus();
//        }//IOEXCEPTION
//        catch (Exception e) {
//            System.out.println("Read failed"); System.exit(1);}
//    }
//
//    /*
//    Połaczenie z socketem
//     */
//    public void listenSocket() {
//        try {
//            socket = new Socket("localhost", 4444);
//            // Inicjalizacja wysylania do serwera
//            out = new PrintWriter(socket.getOutputStream(), true);
//            // Inicjalizacja odbierania z serwera
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        } catch (UnknownHostException e) {
//            System.out.println("Unknown host: localhost");
//            System.exit(1);
//        } catch (IOException e) {
//            System.out.println("No I/O");
//            System.exit(1);
//        }
//    }
//
//    /*
//        Poczatkowe ustawienia klienta. Ustalenie ktory socket jest ktorym kliente
//    */
//    private void receiveInitFromServer() {
//        //JFRAME
//        try {
//            player = Integer.parseInt(in.readLine());
//            if (player == PLAYER1) {
//                msg.setText("My Turn");
//            } else {
//                msg.setText("Opposite turn");
//                send.setEnabled(false);
//            }
//        } catch (IOException e) {
//            System.out.println("Read failed");
//            System.exit(1);
//        }
//    }
//
//    public static void main(String[] args) {
//        Gamev2 gamev2 = new Gamev2();
//        Client client = new Client();
//        jFrame=gamev2.getFrame();
//        jFrame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//        jFrame.pack();
//        jFrame.setVisible(true);
////        jFrame.listenSocket();
////        jFrame.receiveInitFromServer();
////        jFrame.startThread();
//    }
//
//    private void startThread() {
//        Thread gTh = new Thread(this);
//        gTh.start();
//    }
//
//    @Override
//    public void run() {
//        if (player==PLAYER1) {
//            f1();
//        }
//        else{
//            f2();
//        }
//        // Mozna zrobic w jednej metodzie. Zostawiam
//        // dla potrzeb prezentacji
//        // f(player);
//    }
//
//
//    // Jedna metoda dla kazdego Playera
//    void f(int iPlayer){
//        while(true) {
//            synchronized (this) {
//                if (actualPlayer== iPlayer) {
//                    try {
//                        wait(10);
//                    } catch (InterruptedException e) {
//                    }
//                }
//                if (showing ==ACTIVE){
//                    receive();
//                    showing =NONACTIVE;
//                }
//                notifyAll();
//            }
//        }
//    }
//
//    /// Metoda uruchamiana w run dla PLAYER1
//    void f1(){
//        while(true) {
//            synchronized (this) {
//                if (actualPlayer== PLAYER1) {
//                    try {
//                        wait(10);
//                    } catch (InterruptedException e) {
//                    }
//                }
//                if (showing ==ACTIVE){
//                    receive();
//                    showing =NONACTIVE;
//                }
//                notifyAll();
//            }
//        }
//    }
//
//    /// Metoda uruchamiana w run dla PLAYER2
//    void f2(){
//        while(true) {
//            synchronized (this) {
//                if (actualPlayer== PLAYER2) {
//                    try {
//                        wait(10);
//                    } catch (InterruptedException e) {
//                    }
//                }
//                if (showing ==ACTIVE){
//                    receive();
//                    showing =NONACTIVE;
//                }
//                notifyAll();
//            }
//        }
//    }
//}
