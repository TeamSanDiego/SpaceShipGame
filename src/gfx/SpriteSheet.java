package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage crop(double x, double y, double width, double height) {
        return this.image.getSubimage((int)x, (int)y, (int)width, (int)height);
    }

}
