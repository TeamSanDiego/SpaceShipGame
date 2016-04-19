package game;

import game.entities.Bullet;
import game.entities.Controller;
import game.entities.Player;

import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    private Controller c;
    private Player p;

    public InputHandler (Canvas canvas, Controller c, Player p) {
        canvas.addKeyListener(this);
        this.c = c;
        this.p = p;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (Game.State == Game.STATE.GAME) {
            if (code == KeyEvent.VK_RIGHT) {
                Player.isMovingRight = true;
                Player.isMovingLeft = false;
            } else if (code == KeyEvent.VK_LEFT) {
                Player.isMovingRight = false;
                Player.isMovingLeft = true;
            } else if (code == KeyEvent.VK_UP) {
                Player.isMovingUp = true;
                Player.isMovingDown = false;
            } else if (code == KeyEvent.VK_DOWN) {
                Player.isMovingUp = false;
                Player.isMovingDown = true;
            } else if (code == KeyEvent.VK_SPACE) {
                c.addEntity(new Bullet(p.getX() + 65, p.getY() - 35, c.getGame(), c));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT) {
            Player.isMovingRight = false;
            Player.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT) {
            Player.isMovingRight = false;
            Player.isMovingLeft = false;
        } else if (code == KeyEvent.VK_UP) {
            Player.isMovingUp = false;
            Player.isMovingDown = false;
        } else if (code == KeyEvent.VK_DOWN) {
            Player.isMovingUp = false;
            Player.isMovingDown = false;
        }
    }
}
