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
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        /**
         * public Rectangle playButton = new Rectangle(700, 350, 200 ,100);
           public Rectangle helpButton = new Rectangle(700, 500, 200 ,100);
           public Rectangle quitButton = new Rectangle(700, 650, 200 ,100);
         */
        // Play button
        if (mx >= 700 && mx <= (700 + 200)) {
            if (my >= 350 && my <= (350 + 100)) {
                Game.State = Game.STATE.GAME;
            }
        }
        // Help button
        if (mx >= 700 && mx <= (700 + 200)) {
            if (my >= 500 && my <= (500 + 100)) {
                System.out.println("I CAN'T HELP YOU!!!");
            }
        }
        // Qyit button
        if (mx >= 700 && mx <= (700 + 200)) {
            if (my >= 650 && my <= (650 + 100)) {
                System.exit(1);
            }
        }
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
