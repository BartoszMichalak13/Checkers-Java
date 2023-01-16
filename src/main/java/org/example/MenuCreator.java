package org.example;

import org.example.builders.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//IF PIONKI WHERE CREATED, DONT DO IT AGAIN

/**
 * Class that creates menu for players
 */
public class MenuCreator {
    Boolean werecreated=false;
    Gamemain gamemain= new Gamemain().getGamemain();
    JMenu menu;
    static JMenuBar menuBar;
    JRadioButtonMenuItem rbMenuItem;

    /**
     * gets menu bar from gamemain
     * @return menubar
     */
    public JMenuBar getMenuBar(){
        return this.menuBar;
    }

    /**
     * creats menu for players
     */
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

            /**
             * handles action on menu
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                s = e.getActionCommand();
                System.out.println(s);
                gamemain.removebeforethegame();
                if (s.equals("warcaby angielskie"))
                    new EnglishBuilder().build(werecreated,s,gamemain.getPlayer());
                else if (s.equals("warcaby polskie"))
                    new PolishBuilder().build(werecreated,s,gamemain.getPlayer());
                else if (s.equals("warcaby brazylijskie"))
                    new BrazilianBuilder().build(werecreated,s,gamemain.getPlayer());
                menu.setEnabled(false);
                werecreated=true;
                new Gamev2().setWerecreated(werecreated);
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
        rbMenuItem = new JRadioButtonMenuItem("warcaby polskie");
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