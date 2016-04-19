package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {
    public static BufferedImage background;
    public static BufferedImage menuPic;
    public static SpriteSheet player;
    public static SpriteSheet bullet;
    public static SpriteSheet enemy;


    public static void init() {
        try {
            background = ImageLoader.loadImage("/galaxies.jpg");
            menuPic = ImageLoader.loadImage("/menu.png");
            player = new SpriteSheet(ImageLoader.loadImage("/images.png"));
            bullet = new SpriteSheet(ImageLoader.loadImage("/bullet.png"));
            enemy = new SpriteSheet(ImageLoader.loadImage("/enemy.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
