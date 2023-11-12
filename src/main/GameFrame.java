package main;

import javax.swing.JFrame;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        this.add(new GamePanel());
        this.setTitle("joguin");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
