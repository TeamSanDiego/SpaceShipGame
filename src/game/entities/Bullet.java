package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Bullet implements EntityA{
    private SpriteSheet img;
    private int x, y;

    public Bullet(int x, int y, Game game, Controller c) {
        this.x = x;
        this.y = y;
        this.img = Assets.bullet;
    }

    public void tick() {
        y -= 15;
    }

    public void render(Graphics g) {
        g.drawImage(this.img.crop(200, 300, 100, 100)
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
        return new Rectangle(x + 25, y + 30, 25, 50);
    }
}
