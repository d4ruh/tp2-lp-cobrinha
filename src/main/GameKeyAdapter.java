package main;

import entity.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyAdapter extends KeyAdapter {
    Player player;
    GamePanel gp;

    public GameKeyAdapter(Player player, GamePanel gp) {
        this.player = player;
        this.gp=gp;
    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (2 == this.player.direcao)
                    break;
                this.player.direcao = 1;
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (1 == this.player.direcao)
                    break;
                this.player.direcao = 2;
                break;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (4 == this.player.direcao)
                    break;
                this.player.direcao = 3;
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (3 == this.player.direcao)
                    break;
                this.player.direcao = 4;
                break;
        }
    }
}
