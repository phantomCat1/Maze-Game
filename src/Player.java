import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class is responsible for the character movement and 
 * movement animation.
 */
public class Player {
    int posX;
    int posY;
    final int speed = 5;
    GameWindow gameWindow;
    Maze board;
    KeyHandler key;
    BufferedImage normal;
    BufferedImage left;
    BufferedImage right;
    int pixelCount = 0;
    int animation = 1;
    boolean movement;
    public Rectangle solidArea;
    boolean collisionOn = false;
    boolean coinTouched = false;
    boolean reachedEnd = false;
     
    /**
     * This is the constructor of the class.
     * It initializez all the important varibales 
     * and creates instances of the classes necessary 
     * for player movement and control over the game.
     */
    public Player(Maze maze, KeyHandler keyH, GameWindow gw) {
        this.board = maze;
        this.key = keyH;
        this.gameWindow = gw;
        // solidArea represents the player's HitBox
        // It is smaller than the player in order to make movement
        // through the tight spaces in the maze easier, so that the player
        // does'nt have to waste time perfectly alligning the HitBox with the 
        // pathway in order to pass through.
        solidArea = new Rectangle(8, 16, 32, 32);
       
        
        posX = 48;
        posY = 38;
        try {
            normal = ImageIO.read(getClass().getResource("/Character/pixel_ch_normal.png"));
            left = ImageIO.read(getClass().getResource("/Character/pixel_ch_left.png"));
            right = ImageIO.read(getClass().getResource("/Character/pixel_ch_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * This method is responsible for updating the player coordinates when
     * one of the w, a, s, d keys is pressed.
     */
    public void update() {
        movement = false;
        
        // If the player presses a key, the movement variable is set to
        // true, which allows the movement animation to take place.
        if (key.wKey) {
            movement = true;
        } else if (key.sKey) {
            movement = true;
        } else if (key.aKey) {
            movement = true;
        } else if (key.dKey) {
            movement = true;
        }

        collisionOn = false;
        coinTouched = false;
        reachedEnd = false;

        board.checker.checkCollision(this);

        // Player can move only if he doesn't collide with a wall
        // The code for each key is the same
        if (!collisionOn) {
            if (key.wKey) {
                posY -= speed;
                // If player collides with a coin, he collects it
                if (coinTouched) {
                    board.coinsCollected++;
                }
                // If player has collected all coins and has reached the end of 
                // the maze, in the given time frame, the game stops and the win window
                // appears.
                if (reachedEnd && board.coinsCollected == board.coinsExpected) {
                    gameWindow.timer.stop();
                    board.gameThread = null;
                    gameWindow.winWindow();
                } 
            
            } else if (key.sKey) {
                posY += speed;
                if (coinTouched) {
                    board.coinsCollected++;
                }
                if (reachedEnd && board.coinsCollected == board.coinsExpected) {
                    gameWindow.timer.stop();
                    board.gameThread = null;
                    gameWindow.winWindow();
                } 
            
            } else if (key.aKey) {
                posX -= speed;
                if (coinTouched) {
                    board.coinsCollected++;
                }
                if (reachedEnd && board.coinsCollected == board.coinsExpected) {
                    gameWindow.timer.stop();
                    board.gameThread = null; 
                    gameWindow.winWindow();
                } 
            
            } else if (key.dKey) {
                posX += speed;
                if (coinTouched) {
                    board.coinsCollected++;
                }
                if (reachedEnd && board.coinsCollected == board.coinsExpected) {
                    gameWindow.timer.stop();
                    board.gameThread = null;
                    gameWindow.winWindow();
                } 
            
            }
        }
        
        // Every 10 pixels the character sprite will change
        // This makes the movement animation possible
        pixelCount++;
        if (pixelCount >= 10) {
            if (animation == 1) {
                animation = 2;
            } else if (animation == 2) {
                animation = 1;
            }
            pixelCount = 0;

        }

    }


    /**
     * This method draws the character sprite on the map/ maze.
     */
    public void draw(Graphics2D g) {
        // If player doesn't move, meaning no key is pressed
        // the normal, stationary sprite is displayed.
        // Otherwise, every 10 pixels, the character sprite changes,
        // which creates the movement animation.
        if (!movement) {
            g.drawImage(normal, posX, posY, board.tileSize, board.tileSize, null);
        } else {
            if (animation == 1) {
                g.drawImage(left, posX, posY, board.tileSize, board.tileSize, null);
            } else if (animation == 2) {
                g.drawImage(right, posX, posY, board.tileSize, board.tileSize, null);
            }
        }

    }

}
