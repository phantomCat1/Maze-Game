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
    Action upAction;
	Action downAction;
	Action leftAction;
	Action rightAction;
    JLabel label;

    public Player(Maze maze) {
        posX =20;
        posY = 20;
        this.board = maze;
        label = new JLabel();
        label.setBackground(Color.white);
        label.setBounds(posX, posY, 48, 48);
        label.setOpaque(true);
        board.add(label);

    }
    public void update() {
        if (board.w) {
            posY -= speed;
            System.out.println("mmmm");
        } else if (board.s) {
            posY += speed; 
        } else if (board.a) {
            posX -= speed;
        } else if (board.s) {
            posX += speed;
        }

    }
    public void draw(Graphics2D g) {
        board.add(label);
        label.setLocation(posX, posY);
        
    }

}
