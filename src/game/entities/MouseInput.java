package game.entities;

import game.Game;
import game.GameMenu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.Pipe;

public class MouseInput implements MouseListener {

    public MouseInput(Canvas canvas) {
        canvas.addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // Play button
        if (mx >= 700 && mx <= (700 + 200)) {
            if (my >= 350 && my <= (350 + 100)) {
                Game.State = Game.STATE.GAME;
            }
        }
        // Qyit button
        if (mx >= 700 && mx <= (700 + 200)) {
            if (my >= 500 && my <= (500 + 100)) {
                System.exit(1);
            }
            //public Rectangle tryAgainButton = new Rectangle(650, 350, 350, 100);
        }
        //Try again button
        if (mx >= 625 && mx <= (625 + 350)){
            if (my >= 650 && my <= (650 + 100)) {
                Game.State = Game.STATE.GAME;
                Game.life = 2;
                Game.health = 300;
                Player.score = 0;
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
