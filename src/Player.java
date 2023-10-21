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
    Maze board;
    KeyHandler key;
    BufferedImage normal;
    BufferedImage left;
    BufferedImage right;
    int pixelCount = 0;
    int animation = 1;
    boolean movement;
    JPanel panel;
    

    public Player(Maze maze, KeyHandler keyH) {
        this.board = maze;
        this.key = keyH;
        panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setBounds(20, 20, 48, 48);
       
        
        posX = 48;
        posY = 48;
        try {
            normal = ImageIO.read(getClass().getResource("pixel_ch_normal.png"));
            left = ImageIO.read(getClass().getResource("pixel_ch_left.png"));
            right = ImageIO.read(getClass().getResource("pixel_ch_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void update() {
        movement = false;
        
        if (key.wKey) {
            posY -= speed;
            movement = true;
        } else if (key.sKey) {
            posY += speed;
            movement = true;
        } else if (key.aKey) {
            posX -= speed;
            movement = true;
        } else if (key.dKey) {
            posX += speed;
            movement = true;
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
