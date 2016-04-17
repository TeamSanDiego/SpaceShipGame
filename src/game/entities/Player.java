package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Player extends GameObject implements EntityA{
    private int widht, height, velocity, hitPoints;
    private SpriteSheet img;

    private Game game;

    private int column = 2;
    private int row = 2;

    public static boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;


    public Player(int x, int y, int hitPoints, Game game) {
        super(x, y);
        this.hitPoints = hitPoints;
        this.velocity = 10;

        this.game = game;

        this.widht = 200;
        this.height = 200;
        this.img = Assets.player;
    }

    public void tick() {
        if (isMovingRight) {
            this.x += this.velocity;

            this.column++;
            if (column > 4) {
                column = 4;
            }
        } else if (isMovingLeft) {
            this.x -= this.velocity;

            this.column--;
            if (column < 0) {
                column = 0;
            }
        } else if (isMovingUp) {
            this.y -= this.velocity;

            row--;
            if (row < 0) {
                row = 0;
            }
        } else if (isMovingDown) {
            this.y += this.velocity;

            row++;
            if (row > 4) {
                row = 4;
            }
        } else {
            if (column > 2) {
                column--;
            } else if (column < 2) {
                column++;
            }

            if (row > 2) {
                row--;
            } else if (row < 2) {
                row++;
            }
        }

        if (this.x <= 0) {
            this.x = 0;
        } else if (this.x > 1400) {
            this.x = 1400;
        }

        if (this.y <= 0) {
            this.y = 0;
        } else if (this.y > 700) {
            this.y = 700;
        }

//        if (Physics.Collision(this, game.eb)) {
//            game.stop();
//        }
    }

    public void render(Graphics g) {
        g.drawImage(this.img.crop(this.column * this.widht, this.row * this.height, this.widht, this.height)
                , this.x
                , this.y
                , null);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 200, 200);
    }
}
