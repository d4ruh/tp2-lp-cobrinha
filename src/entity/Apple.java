package entity;

import java.util.Random;

public class Apple extends Entity {

    static final int DEFAULT_TILE_SIZE = 16;
    static final int SCALE = 2;
    static final int TILE_SIZE = DEFAULT_TILE_SIZE * SCALE;
    static final int QTD_PER_ROW = 15;
    static final int SCREEN_WIDTH = TILE_SIZE * QTD_PER_ROW;
    static final int SCREEN_HEIGHT = TILE_SIZE * QTD_PER_ROW;
    static final int TOTAL_GAME_TILES = SCREEN_HEIGHT * SCREEN_WIDTH / QTD_PER_ROW;

    Random rand = new Random();

    public void newApple() {
        this.x = rand.nextInt( QTD_PER_ROW ) * TILE_SIZE;
        this.y = rand.nextInt( QTD_PER_ROW ) * TILE_SIZE;
    }
}
