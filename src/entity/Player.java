package entity;

public class Player extends Entity{

    static final int DEFAULT_TILE_SIZE = 16;
    static final int SCALE = 2;
    static final int TILE_SIZE = DEFAULT_TILE_SIZE * SCALE;
    static final int QTD_PER_ROW = 15;
    static final int SCREEN_WIDTH = TILE_SIZE * QTD_PER_ROW;
    static final int SCREEN_HEIGHT = TILE_SIZE * QTD_PER_ROW;
    static final int TOTAL_GAME_TILES = SCREEN_HEIGHT * SCREEN_WIDTH / QTD_PER_ROW;

    public int tamanho = 5;
    public int pontos = 0;
    public int posX[] = new int[TOTAL_GAME_TILES];
    public int posY[] = new int[TOTAL_GAME_TILES];
    public int direcao = 2;

    public void checkApple(Apple apple) {
        if (posX[0] == apple.x && posY[0] == apple.y) {
            tamanho++;
            this.posX[tamanho-1] = this.posX[tamanho-2];
            this.posY[tamanho-1] = this.posY[tamanho-2];

            apple.newApple();
            this.pontos++;
        }
    }

    public void move() {
        for (int i = tamanho; i > 0; i--) {
            this.posX[i] = this.posX[i-1];
            this.posY[i] = this.posY[i-1];
        }

        switch (direcao) {
            case 1: //cima
                this.posY[0] -= TILE_SIZE;
                break;
            case 2: //baixo
                this.posY[0] += TILE_SIZE;
                break;
            case 3: //esquerda
                this.posX[0] -= TILE_SIZE;
                break;
            case 4: //direita
                this.posX[0] += TILE_SIZE;
                break;
        }
    }

    public boolean checkColision() {
        for (int i = this.tamanho; i > 0; i--) {
            if (this.posX[0] == this.posX[i] && this.posY[0] == this.posY[i]) {
                return false;
            }
        }

        if ( this.posX[0] < 0 || this.posX[0] > SCREEN_WIDTH ||
                this.posY[0] < 0 || this.posY[0] > SCREEN_HEIGHT) {
            return false;
        }

        return true;
    }
}