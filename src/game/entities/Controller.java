package game.entities;

import game.Game;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
    private LinkedList<EntityA> ea = new LinkedList<EntityA>();
    private LinkedList<EntityB> eb = new LinkedList<EntityB>();

    Random rnd = new Random();

    private EntityA enta;
    private EntityB entb;

    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public void createEnemys(int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            addEntity(new Enemy(rnd.nextInt(1400), -200, game, this));
        }
    }

    public void tick() {
        // Class A
        for (int i = 0; i < ea.size(); i++) {
            enta = ea.get(i);

            if (enta.getY() < -10) {
                removeEntity(enta);
            }

            enta.tick();
        }
        // Class B
        for (int i = 0; i < eb.size(); i++) {
            entb = eb.get(i);

            if (entb.getY() > 900) {
                entb.setY(-200);
                entb.setX(rnd.nextInt(1400));
                entb.setSpeed(rnd.nextInt(3) + 1);
            }

            entb.tick();
        }
    }

    public void render(Graphics g) {
        // Class A
        for (int i = 0; i < ea.size(); i++) {
            enta = ea.get(i);
            enta.render(g);
        }
        // Class B
        for (int i = 0; i < eb.size(); i++) {
            entb = eb.get(i);
            entb.render(g);
        }
    }

    public void addEntity(EntityA block) {
        ea.add(block);
    }
    public void removeEntity(EntityA block) {
        ea.remove(block);
    }
    public void addEntity(EntityB block) {
        eb.add(block);
    }
    public void removeEntity(EntityB block) {
        eb.remove(block);
    }
    public Game getGame() {
        return game;
    }
    public LinkedList<EntityA> getEntityA() {
        return ea;
    }
    public LinkedList<EntityB> getEntityB() {
        return eb;
    }
}
