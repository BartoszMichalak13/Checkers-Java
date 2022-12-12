package pl.krzysztof;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(10, 10, 512, 540);
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
            }
        };
        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }
}
