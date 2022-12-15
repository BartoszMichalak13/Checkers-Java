package pl.krzysztof;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(10, 10, 512, 540);

        //Ustawiam pionki
        for(int i = 0; i<8;i++){
            for(int j=0; j<8;j++){
                if(((i==0||i==2)&&j%2==1)||(i==1&&j%2==0)){
                    new Pionek(true, j, i);
                }else if(((i==7||i==5)&&j%2==0)||(i==6&&j%2==1)){
                    new Pionek(false, j, i);
                }
            }
        }
        //tworzę szachownice, myśle że to wszystko trzeba będzie dać do osobnych klas, ale narazie
        //jestem w programistycznym cugu i robie cokolwiek byle działało, potem to sie ogarnie.
        JPanel jPanel = new JPanel(){
            public void paint(Graphics g){
                for(int y=0; y<8;y++){
                    for(int x=0; x<8; x++){
                        if((x%2==0&&y%2==1)||(y%2==0&&x%2==1)){
                            g.setColor(Color.DARK_GRAY);
                        }
                        else{
                            g.setColor(Color.lightGray);
                        }
                        g.fillRect(x*64, y*64, 64, 64);
                    }
                }
                for(Pionek pion : Pionek.getPionki() ){
                    if(pion.isActive()){
                        g.setColor(Color.YELLOW);
                        g.fillRect(pion.getX()*64, pion.getY()*64, 64, 64);
                    }
                    if(pion.isWhite()){
                        g.setColor(Color.WHITE);
                    }else{
                        g.setColor(Color.BLACK);
                    }
                    g.fillOval(pion.getX()*64, pion.getY()*64, 64, 64);
                }
            }
        };
        jPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(Pionek pion:Pionek.getPionki()){
                    if(pion.isActive()){
                        pion.przesun(e.getX()/64,e.getY()/64);
                        pion.setActive(false);
                        jFrame.repaint();
                        return;
                    }
                }
                if(Pionek.getPionekByCords(e.getX()/64,e.getY()/64)!=null){

                    for(Pionek pion:Pionek.getPionki()){
                        pion.setActive(false);
                    }

                    Pionek.getPionekByCords(e.getX()/64,e.getY()/64).setActive(true);

                    System.out.println(Pionek.bicia(Pionek.getPionekByCords(e.getX()/64,e.getY()/64).getX(),
                            Pionek.getPionekByCords(e.getX()/64,e.getY()/64).getY(),
                            Pionek.getPionekByCords(e.getX()/64,e.getY()/64).isWhite(),
                            Pionek.getPionekByCords(e.getX()/64,e.getY()/64).isQueen()));
                    jFrame.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        jFrame.add(jPanel);

        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }

}
