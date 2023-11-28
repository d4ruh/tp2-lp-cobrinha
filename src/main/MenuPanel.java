package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    static final int DEFAULT_TILE_SIZE = 16;
    static final int SCALE = 2;
    static final int TILE_SIZE = DEFAULT_TILE_SIZE * SCALE;
    static final int QTD_PER_ROW = 15;
    static final int SCREEN_WIDTH = TILE_SIZE * QTD_PER_ROW;
    static final int SCREEN_HEIGHT = TILE_SIZE * QTD_PER_ROW;
    static final int TOTAL_GAME_TILES = SCREEN_HEIGHT * SCREEN_WIDTH / QTD_PER_ROW;

    static final int DELAY = 150;

    GameFrame gf;
    int commandNumber = 0;

    Timer timer;

    public MenuPanel(GameFrame gf){
        this.gf = gf;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new LoginKeyAdapter(this));
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        this.removeAll();
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

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
        g.drawString(tittle, x, y);

        g.setFont(new Font("Ink Free",Font.BOLD,20));

        Dimension tamBttn = new Dimension(TILE_SIZE*6, TILE_SIZE);

        JButton bttn0 = new JButton("New Game");
        this.add(bttn0);
        bttn0.setVisible(true);
        bttn0.setSize(tamBttn);
        bttn0.setLocation(SCREEN_WIDTH/2 - TILE_SIZE*3, SCREEN_HEIGHT/2 - TILE_SIZE/2);
        bttn0.addActionListener(e -> {
            this.removeAll();
            gf.startGame(this);
        });
        if(0 == commandNumber)      g.drawString(">", TILE_SIZE*4, (int) bttn0.getLocation().getY() + TILE_SIZE/2);

        JButton bttn1 = new JButton("Log In");
        this.add(bttn1);
        bttn1.setVisible(true);
        bttn1.setSize(tamBttn);
        bttn1.setLocation(SCREEN_WIDTH/2 - TILE_SIZE*3, SCREEN_HEIGHT/2 + TILE_SIZE*2 - TILE_SIZE/2);
        bttn1.addActionListener(e -> {

        });
        if(1 == commandNumber)      g.drawString(">", TILE_SIZE*4, (int) bttn1.getLocation().getY() + TILE_SIZE/2);

        JButton bttn2 = new JButton("Quit");
        this.add(bttn2);
        bttn2.setVisible(true);
        bttn2.setSize(tamBttn);
        bttn2.setLocation(SCREEN_WIDTH/2 - TILE_SIZE*3, SCREEN_HEIGHT/2 + TILE_SIZE*4 - TILE_SIZE/2);
        bttn2.addActionListener(e -> System.exit(0));
        if(2 == commandNumber)      g.drawString(">", TILE_SIZE*4, (int) bttn2.getLocation().getY() + TILE_SIZE/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
