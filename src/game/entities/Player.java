package game.entities;

import game.Game;
import game.Launcher;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.io.*;

public class Player implements EntityA{
    private int x, y, width, height, velocity;
    public static int score = 0;
    private SpriteSheet img;
    private Game game;
    Controller controller;
    private int column = 2;
    private int row = 2;
    public static boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;

    public Player(int x, int y, Game game, Controller controller) {
        this.x = x;
        this.y = y;
        this.velocity = 10;
        this.controller = controller;

        this.game = game;

        this.width = 200;
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

        for (int i = 0; i < game.eb.size(); i++) {
            EntityB tempEnt = game.eb.get(i);

            if (Physics.Collision(this, tempEnt)){
                controller.removeEntity(tempEnt);
                game.setEnemyKilled(game.getEnemyKilled() + 1);
                game.health -= 10 * 3;
                Player.score += 5;
                if (game.health <= 0 && game.life >= 1){
                    game.life--;
                    game.health = 300;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(this.img.crop(this.column * this.width, this.row * this.height, this.width, this.height)
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
        return new Rectangle(x + 10 , y + 30, 170, 100);
    }
}
