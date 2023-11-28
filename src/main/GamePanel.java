package main;

import entity.Apple;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {

    static final int DEFAULT_TILE_SIZE = 16;
    static final int SCALE = 2;
    static final int TILE_SIZE = DEFAULT_TILE_SIZE * SCALE;
    static final int QTD_PER_ROW = 15;
    static final int SCREEN_WIDTH = TILE_SIZE * QTD_PER_ROW;
    static final int SCREEN_HEIGHT = TILE_SIZE * QTD_PER_ROW;
    static final int TOTAL_GAME_TILES = SCREEN_HEIGHT * SCREEN_WIDTH / QTD_PER_ROW;

    static final int DELAY = 150;


    boolean playing;
    Apple apple = new Apple();
    Player player = new Player();

    Timer timer;
    GameFrame gf;


    public GamePanel(GameFrame gf){
        this.gf = gf;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new GameKeyAdapter(player,this));
        startGame();
    }

    public void startGame() {
        apple.newApple();
        playing = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (playing) {
            for (int i = 0; i < QTD_PER_ROW; i++) {
                g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * TILE_SIZE, SCREEN_WIDTH, i * TILE_SIZE);
            }
            g.setColor(Color.green);
            g.fillOval(apple.x, apple.y, TILE_SIZE, TILE_SIZE);
            for (int i = 0; i < player.tamanho; i++) {
                if (i == 0) g.setColor(Color.red);
                else g.setColor(Color.blue);
                g.fillRect(player.posX[i], player.posY[i], TILE_SIZE, TILE_SIZE);
            }
        }
        else        gameOver(g);
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics01 = getFontMetrics(g.getFont());
        g.drawString("Game Over!", ((SCREEN_WIDTH - metrics01.stringWidth("Game Over!"))/2),
                SCREEN_HEIGHT/2 - SCREEN_HEIGHT/4);

        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics metrics02 = getFontMetrics(g.getFont());
        g.drawString("Pontos: " + player.pontos, (SCREEN_WIDTH - metrics02.stringWidth("Pontos: " + player.pontos))/2,
                (SCREEN_HEIGHT + 2 * metrics02.getHeight())/2 - SCREEN_HEIGHT/4);

        JButton bttn0 = new JButton("restart");
        this.add(bttn0);
        bttn0.setVisible(true);
        bttn0.setSize(new Dimension(TILE_SIZE*4, TILE_SIZE));
        bttn0.setLocation(SCREEN_WIDTH/2 - TILE_SIZE*2, SCREEN_HEIGHT/2);

        bttn0.addActionListener(e -> gf.restart(this, true));

        JButton bttn1 = new JButton("quit to title");
        this.add(bttn1);
        bttn1.setVisible(true);
        bttn1.setSize(new Dimension(TILE_SIZE*4, TILE_SIZE));
        bttn1.setLocation(SCREEN_WIDTH/2 - TILE_SIZE*2, SCREEN_HEIGHT/2 + TILE_SIZE*2);

        bttn1.addActionListener(e -> gf.restart(this, false));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playing) {
            player.move();
            player.checkApple(apple);
            playing = player.checkColision();
            if (!playing)   timer.stop();
        }
        repaint();
    }
}
