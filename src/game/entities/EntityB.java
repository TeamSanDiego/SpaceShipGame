package game.entities;

import java.awt.*;

public interface EntityB {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public int getX();
    public void setX(int x);
    public int getY();
    public void setY(int y);
    public void setSpeed(int speed);
}
