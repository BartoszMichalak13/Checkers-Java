package org.example;

import org.example.builders.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuCreator {
    Gamemain gamemain= new Gamemain().getGamemain();
    JMenu menu;
    static JMenuBar menuBar;

    JRadioButtonMenuItem rbMenuItem;

    public JMenuBar getMenuBar(){
        return this.menuBar;
    }
    public void createmenu() {
//Create the menu bar.
        menuBar = new JMenuBar();

//Build the first menu.
        menu = new JMenu("Game Modes");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);


//a group of radio button menu items

        class ActionHandler implements ActionListener {
            String s;
            @Override
            public void actionPerformed(ActionEvent e) {
                s = e.getActionCommand();
                System.out.println(s);
                gamemain.removebeforethegame();
                if (s.equals("warcaby angielskie"))
                    new EnglishBuilder().build();
                else if (s.equals("warcaby włoskie"))
                    new ItalianBuilder().build();
                else if (s.equals("warcaby hiszpańskie"))
                    new SpanishBuilder().build();
                else if (s.equals("warcaby niemieckie"))
                    new GermanBuilder().build();
                else if (s.equals("warcaby rosyjskie"))
                    new RussianBuilder().build();
                else if (s.equals("warcaby polskie"))
                    new PolishBuilder().build();
                else if (s.equals("warcaby kanadyjskie"))
                    new CanadianBuilder().build();
                else if (s.equals("warcaby tureckie"))
                    new TurkishBuilder().build();
                else if (s.equals("warcaby brazylijskie"))
                    new BrazilianBuilder().build();
                menu.setEnabled(false);

                gamemain.send(s);
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
    }
}