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


    int tamanhoCobra = 5;
    final int posX[] = new int[TOTAL_GAME_TILES];
    final int posY[] = new int[TOTAL_GAME_TILES];
    int direcao = 2;
    boolean playing;
    int pontos = 0;
    Apple apple = new Apple();
    Player player = new Player();

    Timer timer;
    GameFrame gf;
    int gameState;
    public final int startState=0;
    public  final int menuState=1;
    public final int gameOverState=2;
    public int commandNumber=0;


    public GamePanel(GameFrame gf){
        this.gf = gf;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(player,this));
        startGame();
    }

    public void startGame() {
        apple.newApple();
        playing = true;
        gameState=menuState;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if(gameState==menuState){
            drawMenu(g);
        }
     else if (gameState==startState) {
         if (playing )
            drawGame(g);
         else
             gameOver(g);
      }

    }
    private void drawGame(Graphics g){
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
    private void drawMenu(Graphics g){
        g.setFont(new Font("Ink Free",Font.BOLD,60));
        String tittle="Cobrão gigante";
        FontMetrics metrics = getFontMetrics(g.getFont());
        int x=((SCREEN_WIDTH - metrics.stringWidth(tittle))/2);
        int y=SCREEN_HEIGHT/2 - SCREEN_HEIGHT/4;
        //sombra
        g.setColor(Color.gray);
        g.drawString(tittle, x+5, y+5);
        //titulo
        g.setColor(Color.white);
        g.drawString(tittle, x,y);

        g.setFont(new Font("Ink Free",Font.BOLD,20));
        String newGame="New game";
        x=((SCREEN_WIDTH - metrics.stringWidth(newGame))/2);
        y+=TILE_SIZE*4;
        g.drawString(newGame, x, y);
        if(commandNumber==0){
            g.drawString(">", x-TILE_SIZE,y );
        }

        String login="login";
        x=((SCREEN_WIDTH - metrics.stringWidth(login))/2);
        y+=TILE_SIZE*2;
        g.drawString(login, x, y);
        if(commandNumber==1){
            g.drawString(">", x-TILE_SIZE,y );
        }

        String quit="quit";
        x=((SCREEN_WIDTH - metrics.stringWidth(quit))/2);
        y+=TILE_SIZE*2;
        g.drawString(quit, x, y);
        if(commandNumber==2){
            g.drawString(">", x-TILE_SIZE,y );
        }



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

        JButton bttn = new JButton("restart");
        this.add(bttn);
        bttn.setVisible(true);
        bttn.setSize(new Dimension(TILE_SIZE*4, TILE_SIZE));
        bttn.setLocation(SCREEN_WIDTH/2 - TILE_SIZE*2, SCREEN_HEIGHT/2);

        bttn.addActionListener(e -> gf.restart());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (playing && gameState==startState) {
            player.move();
            player.checkApple(apple);
            playing = player.checkColision();
            if (!playing)   timer.stop();
        }
        repaint();
    }
}
