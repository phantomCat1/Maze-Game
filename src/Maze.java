import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
/**
 * This class creates the maze.
 * 
 */

public class Maze extends JPanel implements Runnable, KeyListener {
    int mazeLength;
    int[][] maze;
    final int scale = 3;
    final int originalTileSize = 16;
    public final int tileSize = 48;
    Thread gameThread;
    int fps = 30;
    KeyHandler key;
    Player player;
    int size;
    boolean w,a,s,d;
    


    public Maze(int level) {
        this.size = level;
        mazeLength = tileSize * level;
        maze = new int[level][level];
        //key = new KeyHandler();
        this.addKeyListener(this);
        player = new Player(this);
        this.setPreferredSize(new Dimension(mazeLength, mazeLength));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        //this.setFocusTraversalKeysEnabled(true);
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
        player.draw(g2);
        g.dispose();


    }

    public void createMaze() {



    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            w = true;
            System.out.println("brrrr");
        } 
        if (code == KeyEvent.VK_S) {
            s = true;
        }
        if (code == KeyEvent.VK_A) {
            a = true;
        }
        if (code == KeyEvent.VK_D) {
            d = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            w = false;
        } 
        if (code == KeyEvent.VK_S) {
            s = false;
        }
        if (code == KeyEvent.VK_A) {
            a = false;
        }
        if (code == KeyEvent.VK_D) {
            d = false;
        }
    }

}