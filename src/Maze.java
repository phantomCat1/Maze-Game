import java.awt.*;
import javax.swing.*;
/**
 * This class creates the JPanel where the game is displayed.
 * It makes the connection between the GameWindow class and the 
 * Player, CollisionCheckerz, Keyhandler and MazeCreator class.
 * It contains the game loop responsible for updating and repainting the 
 * game panel.
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
    
    

    /**
     * This is the constructor of the class.
     * It initiates all the different variables 
     * and creates instances of the classes mentioned above.
     * 
     */
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

    /**
     * This method starts the game thread.
     * The thread is resonsible for running the game loop
     * every 0,033 seconds, meaning the game runs at 30 frames per second.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        // We have used the delta / accelerator method for creating the game loop
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

    /**
     * This method updates the game.
     * The update consists of the player movements and keeps track of 
     * his position in the maze and collisions.
     */
    public void update() {
        player.update();


    }

    /**
     * This method is responsible for painting and repainting the game panel.
     * It calls upon the draw() method of the MazeCreator class to draw the maze/ map.
     * It does so repeatedly because certain tiles on the map change when the player collects
     * coins.
     * Then it calls the draw() method of the Player class to draw the character sprite 
     * on top of the maze/ map at his new coordinates.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        mazeCreate.draw(g2);
        player.draw(g2);
        g.dispose();


    }

    
}

