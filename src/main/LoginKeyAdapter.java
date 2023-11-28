package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginKeyAdapter extends KeyAdapter {
    MenuPanel mp;

    public LoginKeyAdapter(MenuPanel mp) {
        this.mp = mp;
    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                mp.commandNumber = (mp.commandNumber - 1)%3;
                if (0 > mp.commandNumber) mp.commandNumber = 2;
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                mp.commandNumber = (mp.commandNumber + 1)%3;
                break;

            case KeyEvent.VK_ENTER:
                switch (mp.commandNumber) {
                    case 0:
                        mp.gf.startGame(mp);
                        break;
                    case 1:
                        mp.gf.changePage(2);
                        break;
                    case 2:
                        System.exit(0);
                }
        }
    }
}
