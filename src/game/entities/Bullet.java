package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Bullet extends GameObject implements EntityA{
    private SpriteSheet img;
    private Game game;
    private Controller c;

    public Bullet(int x, int y, Game game, Controller c) {
        super(x, y);
        this.img = Assets.bullet;
        this.game = game;
        this.c = c;
    }

    public void tick() {
        y -= 15;

//        if (Physics.Collision(this, game.eb)) {
//            c.removeEntity(this);
//        }
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
        return new Rectangle(x, y, 100, 100);
    }
}
