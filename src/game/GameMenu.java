package game;

import java.awt.*;

public class GameMenu {
    public Rectangle playButton = new Rectangle(700, 350, 200 ,100);
    public Rectangle quitButton = new Rectangle(700, 500, 200 ,100);
    public Rectangle tryAgainButton = new Rectangle(625, 650, 350, 100);



    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (Game.State == Game.STATE.MENU){

        Font fnt0 = new Font("arial", Font.BOLD, 100);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SPACE SURVIVAL", 350, 250);

        Font fnt1 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt1);
        g.drawString("PLAY", playButton.x + 35, playButton.y + 70);
        g2d.draw(playButton);
        g.drawString("QUIT", quitButton.x + 40, quitButton.y + 70);
        g2d.draw(quitButton);

        } else if (Game.State == Game.STATE.END){

            Font fnt0 = new Font("arial", Font.BOLD, 100);
            g.setFont(fnt0);
            g.setColor(Color.white);
            g.drawString("GAME OVER", 500, 250);

            Font fnt1 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt1);
            g.drawString("TRY AGAIN", tryAgainButton.x + 35, tryAgainButton.y + 70);
            g2d.draw(tryAgainButton);
            g.drawString("QUIT", quitButton.x + 40, quitButton.y + 70);
            g2d.draw(quitButton);
        }
    }
}
