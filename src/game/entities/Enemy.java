package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.Random;

public class Enemy implements EntityB{
    private SpriteSheet img;
    private int x, y;
    private Game game;
    private Controller c;
    Random rnd = new Random();

    private int speed = rnd.nextInt(4) + 2;

    public Enemy(int x, int y, Game game, Controller c) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.c = c;
        this.img = Assets.enemy;
    }

    public void tick() {
        this.y += speed;

        for (int i = 0; i < game.ea.size(); i++) {
            EntityA tempEnt = game.ea.get(i);

            if (Physics.Collision(this, tempEnt)) {
                c.removeEntity(tempEnt);
                c.removeEntity(this);
                game.setEnemyKilled(game.getEnemyKilled() + 1);
                Player.score += 10;
            }
        }


    }

    public void render(Graphics g) {
        g.drawImage(this.img.crop(0, 200, 100, 100)
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
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 100, 75);
    }
}
