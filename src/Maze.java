import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
/**
 * This class creates the maze.
 * 
 */

public class Maze extends JPanel implements Runnable {
    int mazeLength;
    final int scale = 3;
    final int originalTileSize = 16;
    public final int tileSize = 48;
    public Thread gameThread;
    int fps = 30;
    KeyHandler key;
    MazeCreator mazeCreate;
    Player player;
    int lev;
    CollisionChecker checker;
    int coinsCollected = 0;
    int coinsExpected;
    GameWindow gw;
    
    


    public Maze(int level, GameWindow gww) {
        this.lev = level;
        this.gw = gww;
        mazeLength = tileSize * level;
        coinsExpected = (level / 5) * 2;
        key = new KeyHandler();
        this.addKeyListener(key);
        mazeCreate = new MazeCreator(this, level);
        checker = new CollisionChecker(this);
        player = new Player(this, key, gw);
        this.setPreferredSize(new Dimension(mazeLength, mazeLength));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocus(true);
        
        

    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                //1. Update the screen when player moves
                update();
                //2. Repaint the screen with the new updates
                repaint();
                delta--;
            }
           

        }

    }

    public void update() {
        player.update();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        mazeCreate.draw(g2);
        player.draw(g2);
        g.dispose();


    }

    
}

