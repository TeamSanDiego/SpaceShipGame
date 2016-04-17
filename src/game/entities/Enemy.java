package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject implements EntityB{
    private SpriteSheet img;
    private Game game;
    private Controller c;

    private Rectangle boundingBox;

    Random rnd = new Random();
    private int speed = rnd.nextInt(3) + 1;

    public Enemy(int x, int y, Game game, Controller c) {
        super(x, y);
        this.game = game;
        this.c = c;
        this.img = Assets.enemy;
    }

    public void tick() {
        this.y += speed;
        if (Physics.Collision(this, game.ea)) {
            c.removeEntity(this);
            game.setEnemyKilled(game.getEnemyKilled() + 1);
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
        return new Rectangle(x, y, 100, 100);
    }
}
