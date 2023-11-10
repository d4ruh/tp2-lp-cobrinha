package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up_press = false, down_press = false, right_press = false, left_press = false;
    public boolean jump_press = false;

    @Override
    public void keyTyped(KeyEvent e) {
        //não é usado
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            up_press = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            down_press = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            left_press = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            right_press = true;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            jump_press = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            up_press = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            down_press = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            left_press = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            right_press = false;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            jump_press = false;
        }
    }
}
