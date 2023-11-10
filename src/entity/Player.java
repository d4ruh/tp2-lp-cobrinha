package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.Color;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    long jumpStartTime = -1;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
        speed = 5;
    }

    public void update() {

        if (keyH.up_press)         y -= speed * gp.deltaTime;
        if (keyH.down_press)       y += speed * gp.deltaTime;
        if (keyH.left_press)       x -= speed * gp.deltaTime;
        if (keyH.right_press)      x += speed * gp.deltaTime;

        if (y > 384)        y = 384;
        if (y < 0)          y = 0;
        if (x < 0)          x = 0;
        if (x > 720)        x = 720;

        try{Thread.sleep(0);}
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
