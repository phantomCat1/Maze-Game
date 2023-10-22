import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

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
     

    public Player(Maze maze, KeyHandler keyH, GameWindow gw) {
        this.board = maze;
        this.key = keyH;
        this.gameWindow = gw;
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



    public void update() {
        movement = false;
        
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

        if (!collisionOn) {
            if (key.wKey) {
                posY -= speed;
                if (coinTouched) {
                    board.coinsCollected++;
                }
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
                System.out.println(board.coinsCollected);
            
            } else if (key.aKey) {
                posX -= speed;
                if (coinTouched) {
                    board.coinsCollected++;
                }
                if (reachedEnd && board.coinsCollected == board.coinsExpected) {
                    gameWindow.timer.stop();
                    board.gameThread = null; // discuss the final details!!!!!!!!!!!!!!!!!!
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
        
        // Every 10 pixels the animation will change
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



    public void draw(Graphics2D g) {
        //g.setColor(Color.white);
        //g.fillRect(posX, posY, board.tileSize, board.tileSize);
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
