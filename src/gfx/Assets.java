package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static SpriteSheet player;
    public static SpriteSheet bullet;
    public static SpriteSheet enemy;


    public static void init() {
        background = ImageLoader.loadImage("/galaxies.jpg");
        player = new SpriteSheet(ImageLoader.loadImage("/images.png"));
        bullet = new SpriteSheet(ImageLoader.loadImage("/bullet.png"));
        enemy = new SpriteSheet(ImageLoader.loadImage("/enemy.png"));
    }
}
