package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
//KOMUNIKACJA MIEDZY GRACZAMI: BAZOWO TU MA BYC MAIN A NIE U GRACZA,


public class Gamev2 implements Runnable{

    private Socket firstPlayer;
    private Socket secondPlayer;


    private final static int FIRST=1;
    private final static int SECOND=2;
    private static int turn=FIRST;
    public Gamev2(){

    }

    public Gamev2(Socket firstPlayer, Socket secondPlayer){
        this.firstPlayer = firstPlayer;
        this.secondPlayer= secondPlayer;


    }
    @Override
    public void run() {

        try{
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
            setjFrame();
            String line;
            do {
                //no dobra trzeba tu wrzucic wysylanie listy pionkow a nie wiadomosci

                if (turn==SECOND) {
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
                    turn=FIRST;
                }

                if (turn==FIRST) {
                    // IDEA: Client samsobie wyświetla
                    // Odbieranie od socketa
                    line = inF.readLine();
                    // Wypisywanie na serwerze
                    System.out.println(line);
                    // Wysylanie do socketa
                    outS.println("-> (" + line + ")");
                    //Gamemain.jFrame.repaint();

                    turn=SECOND;
                }
            } while (true);

        } catch (IOException ex) {
            System.err.println("ex");
        }
    }

    private void sendMove(DataOutputStream out, String text) throws IOException {
        out.writeChars(text);

    }
    static JFrame jFrame;
    static JLabel beforethegame;
    public JFrame getFrame() {return this.jFrame;}
    class CheckerBoardHandler implements MouseListener {
        Plansza pl= new Plansza();
        @Override
        public void mouseClicked(MouseEvent e) {
            //Z tego co rozumiem, wykonalismy ruch == zmiana tury
            for (Pionek pion : Pionek.getPionki()) {
                if (pion.isActive()) {
                    pion.przesun(e.getX() / pl.getwindowW(), e.getY() / pl.getwindowH());
                    pion.setActive(false);
                    jFrame.repaint();
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
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }
    public JFrame sendFrame(){
        return this.jFrame;
    }

    //public static void main(String[] args) {
    public void setjFrame(){
        jFrame = new JFrame();
        jFrame.setBounds(10, 10, 800, 800);
        beforethegame = new JLabel(
                "WYBIERZ TRYB GRY",
                SwingConstants.CENTER
        );
        beforethegame.setFont(beforethegame.getFont().deriveFont(50.0F));

        JMenuBar menuBar;
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
        //jFrame.setVisible(true);
    }

}


