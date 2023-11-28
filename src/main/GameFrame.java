package main;

import javax.swing.*;

public class GameFrame extends JFrame {

    static final int DEFAULT_TILE_SIZE = 16;
    static final int SCALE = 2;
    static final int TILE_SIZE = DEFAULT_TILE_SIZE * SCALE;
    static final int QTD_PER_ROW = 15;
    static final int SCREEN_WIDTH = TILE_SIZE * QTD_PER_ROW;
    static final int SCREEN_HEIGHT = TILE_SIZE * QTD_PER_ROW;
    static final int TOTAL_GAME_TILES = SCREEN_HEIGHT * SCREEN_WIDTH / QTD_PER_ROW;
    final int playingState=0;
    final int menuState=1;
    final int loginState=2;

    public GameFrame(int state) {
        if (state==0)
            this.add(new GamePanel(this));
        else if(state==1)
            this.add(new MenuPanel(this));
        else if(state==2)
            this.add(new LoginPanel(this) );

        this.setTitle("Cobrão Gigante");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void startGame(MenuPanel mp) {
        new GameFrame(0);
        this.dispose();
    }
    public void changePage(int state){
        new GameFrame(state);
        this.dispose();
    }
    public void restart(GamePanel gp, int state) {
        new GameFrame(state);
        this.dispose();
    }
}
