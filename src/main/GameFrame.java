package main;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        this.add(new GamePanel(this));
        this.setTitle("Cobrão Gigante");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void restart() {
        new GameFrame();
        this.dispose();
    }
}
