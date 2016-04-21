package game;

import display.Display;
import game.entities.*;
import gfx.Assets;
import gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import static gfx.Assets.*;

/**
 * za da si naprawim nishka na koqto da ni wurvi igrata
 * pishem - implements Runnable
 */

public class Game implements Runnable {
    private String name;
    private int width;
    private int height;
    // BufferStrategy - nachina po koito nie kontrolilrame vizualiziraneto na obektite
    // na nashiq canvas
    // Graphics - towa koeto gi izrisuva na nashiq canvas
    private Thread thread;
    private boolean isRunning;
    public enum STATE {
        MENU,
        GAME,
        END
    };
    public static STATE State = STATE.MENU;
    public static STATE StateEnd = STATE.END;
    private Player player;
    public static int life = 2;
    public static int health = 100 * 3;
    public static int enemyCount = 10;
    public static int enemyKilled = 0;
    public static int level = 1;
    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private GameMenu menu;
    private InputHandler ih;
    private MouseInput mi;
    private int incrementBackground = 0;
    private Controller c;
    private Enemy enemy;
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;

    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public void init() {
        Assets.init();
        this.display = new Display(this.name, this.width, this.height);
        menu = new GameMenu();
        c = new Controller(this);
        c.createEnemys(enemyCount);
        player = new Player(700, 700, this, c);
        enemy = new Enemy(500, 500, this, c);
        this.ih = new InputHandler(this.display.getCanvas(), c, player);
        this.mi = new MouseInput(this.display.getCanvas());
        ea = c.getEntityA();
        eb = c.getEntityB();
    }

    /**
     * tick obnowqwa sustoqnieto na igrata na vseki tick
     * 1 sec = 1_000_000_000ns
     * render - printira sustoqnieto na igrata na wseki tick
     */
    public void tick() {
        if (State == STATE.GAME) {
            this.player.tick();
            this.c.tick();
            if (health <= 0) {
                State = STATE.END;
            }
        }

        if (enemyKilled >= enemyCount) {
            level++;
            enemyCount += 2;
            enemyKilled = 0;
            c.createEnemys(enemyCount);
        }
    }

    public void render() {
        /**
         * purvo suzdawame buferstrategiqta
         * i posle po neq grafik obekta
         */

        // canvasa ima nqkakwa strategiq za buferirane i q iniciali
        //ziram na bs
        this.bs = this.display.getCanvas().getBufferStrategy();

        // ako nqma bs q szdavam
        if (this.bs == null) {
            // (2) imame dva bufera s koito mojem da rabotim
            this.display.getCanvas().createBufferStrategy(2);
            // da vzemem strategiqta koqto toku shto suzdadohme
            this.bs = this.display.getCanvas().getBufferStrategy();
        }

        // grafikata se vzimame ot buferstrategiqta
        this.g = this.bs.getDrawGraphics();
        // izchistwame freima predi da rendnem
        this.g.clearRect(0, 0, this.width, this.height);

        // start drawing

        this.g.drawImage(background, 0, -900 + incrementBackground, 1600, 900, null);
        this.g.drawImage(background, 0, 0 + incrementBackground, 1600, 900, null);
        if (incrementBackground >= 900) {
            incrementBackground = 0;
        } else {
            incrementBackground++;
        }

        if (life > 0) {
            int value = 0;
            for (int i = 0; i < life; i++) {
                this.g.drawImage(lifeImage, 5 + value, 830, 47, 25, null);
                value += 50;
            }
        }

        if (State == STATE.GAME) {
            this.player.render(this.g);
            this.c.render(this.g);

            g.setColor(Color.WHITE);
            g.fillRect(4, 19, 302, 32);

            g.setColor(Color.RED);
            g.fillRect(5, 20, 300, 30);

            g.setColor(Color.GREEN);
            g.fillRect(5, 20, health, 30);

            g.setColor(Color.black);
            Font myFont = new Font("Arial", Font.BOLD, 30);
            g.setFont(myFont);
            g.drawString(Integer.toString(health / 3), health / 2 - 12, 47);

            g.setColor(Color.WHITE);
            Font myFont1 = new Font("Arial", Font.BOLD, 25);
            g.setFont(myFont1);
            g.drawString("LEVEL", 1400, 25);
            g.drawString(Integer.toString(level), 1500, 25);
            g.drawString("SCORE", 1400, 50);
            g.drawString(Integer.toString(Player.score), 1500, 50);

        } else if (State == STATE.MENU) {
            this.g.drawImage(menuPic, 0, 0, 1600, 900, null);
            this.menu.render(this.g);
        } else if (State == STATE.END) {
            this.g.drawImage(menuPic, 0, 0, 1600, 900, null);
            this.menu.render(this.g);

            Font fnt0 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt0);
            g.setColor(Color.white);
            g.drawString("AT LEVEL", 650, 350);
            g.drawString(Integer.toString(level), 950, 350);
            g.drawString("YOUR SCORE", 575, 450);
            g.drawString(Integer.toString(Player.score), 975, 450);
        }
        // kazvame na nashiq canvas da vizualizira informaciqta
        this.g.dispose();
        this.bs.show();
    }

    /**
     * za da moje klasa Game da nasledi interfeisa Runnable
     * zaduljitelno se pishe metoda run, zashtoto wsichki ot runable
     * imat metod run
     * Tozo metod se puska avtomatichno kogato pusna nova nishka
     * run metoda e main metoda na vsichko koeto e runnable
     */
    // kogato startna igrata ot start() shte se suzdade nova nishka
    // koqto avtomatichno shte pusne run metoda
    @Override
    public void run() {
        // iskame da inicializirame samo kogato trugne igrata
        this.init();

        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;

        double delta = 0;
        long now;
        long lastTimeTicked = System.nanoTime();

        // moje da e s true no taka nqmame kontrol nad cikula
        // po dobre e edna promenliva koqto da setvame na true
        // kogato startnem i na false kogato sprem igrata
        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTimeTicked) / timePerTick;
            lastTimeTicked = now;

            if (delta >= 1) {
                // na vsqka iteraciq nishkata spira swoqta rabota za
                // edi kolko si ms
                // slaga se 1 ms za optimizaciq na procesora pri
                // presmqtaneto na wremet oza zabavqne
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.tick();
                this.render();
                delta--;
            }
        }

        // v kraq na run metoda vikame stop() za da sprem nishkata
        this.stop();
    }

    /**
     * synchronized - sinhronizira dvete nishki pri puskane i spirane
     * toest ednata izchakwa drugata da se pusne i sled towa q izchakwa
     * da se joine
     * ako wsichki metodi w dvete nishki ni sa sinhronizirani nqma
     * smisul ot dve nishki, zashtoto te postoqnno shte se izchakwat
     */
    public synchronized void start() {
        this.isRunning = true;
        // iskame nova nishka koqto da raboti vurhu klasa Game
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        this.isRunning = false;
        // ne q prekusvam, a q slivam kum predishnata
        try {
            thread.join(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }
}
