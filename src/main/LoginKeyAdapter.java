package main;

import entity.Player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginKeyAdapter extends KeyAdapter {
    LoginPanel lp;

    public LoginKeyAdapter(LoginPanel lp) {
        this.lp=lp;
    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                lp.commandNumber = (lp.commandNumber - 1)%3;
                if (0 > lp.commandNumber) lp.commandNumber = 2;
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                lp.commandNumber = (lp.commandNumber + 1)%3;
                break;

            case KeyEvent.VK_ENTER:
                switch (lp.commandNumber) {
                    case 0:
                        lp.gf.startGame(lp);
                        break;
                    case 1:
                        //login
                        break;
                    case 2:
                        System.exit(0);
                }
        }
    }
}
