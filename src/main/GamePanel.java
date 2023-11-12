package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int DEFAULT_TILE_SIZE = 16;
    static final int SCALE = 2;
    static final int TILE_SIZE = DEFAULT_TILE_SIZE * SCALE;
    static final int QTD_PER_ROW = 15;
    static final int SCREEN_WIDTH = TILE_SIZE * QTD_PER_ROW;
    static final int SCREEN_HEIGHT = TILE_SIZE * QTD_PER_ROW;
    static final int TOTAL_GAME_TILES = SCREEN_HEIGHT * SCREEN_WIDTH / QTD_PER_ROW;

    static final int DELAY = 75;


    int tamanhoCobra = 5;
    final int posX[] = new int[TOTAL_GAME_TILES];
    final int posY[] = new int[TOTAL_GAME_TILES];
    int direcao = 2;
    boolean running;
    int pontos = 0;
    int appleX;
    int appleY;

    Timer timer;
    Random rand;


    public GamePanel(){
        rand = new Random();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < QTD_PER_ROW; i++) {
            g.drawLine(i*TILE_SIZE, 0, i*TILE_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*TILE_SIZE, SCREEN_WIDTH, i*TILE_SIZE);
        }

        g.setColor(Color.green);
        g.fillOval(appleX, appleY, TILE_SIZE, TILE_SIZE);

        for (int i = 0; i < tamanhoCobra; i++) {
            if (i == 0) g.setColor(Color.red);
            else        g.setColor(Color.blue);

            g.fillRect(posX[i], posY[i], TILE_SIZE, TILE_SIZE);
        }
    }

    public void newApple() {
        appleX = rand.nextInt( QTD_PER_ROW ) * TILE_SIZE;
        appleY = rand.nextInt( QTD_PER_ROW ) * TILE_SIZE;
    }

    public void move() {
        for (int i = tamanhoCobra; i > 0; i--) {
            posX[i] = posX[i-1];
            posY[i] = posY[i-1];
        }

        switch (direcao) {
            case 1: //cima
                posY[0] -= TILE_SIZE;
                break;
            case 2: //baixo
                posY[0] += TILE_SIZE;
                break;
            case 3: //esquerda
                posX[0] -= TILE_SIZE;
                break;
            case 4: //direita
                posX[0] += TILE_SIZE;
                break;
        }
    }

    public void checkApple() {
        if (posX[0] == appleX && posY[0] == appleY) {
            tamanhoCobra++;
            posX[tamanhoCobra-1] = posX[tamanhoCobra-2];
            posY[tamanhoCobra-1] = posY[tamanhoCobra-2];

            newApple();
            pontos++;
        }
    }

    public void checkColision() {
        for (int i = tamanhoCobra; i > 0; i--) {
            if (posX[0] == posX[i] && posY[0] == posY[i]) {
                running = false;
            }
        }

        if ( posX[0] < 0 || posX[0] > SCREEN_WIDTH ||
             posY[0] < 0 || posY[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) timer.stop();
    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkColision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    if (2 == direcao)
                        break;

                    direcao = 1;
                    break;

                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    if (1 == direcao)
                        break;

                    direcao = 2;
                    break;

                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    if (4 == direcao)
                        break;

                    direcao = 3;
                    break;

                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    if (3 == direcao)
                        break;

                    direcao = 4;
                    break;
            }
        }
    }
}
