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

    public Player(Maze maze) {
        this.board = maze;

    }
    public void update() {

    }
    public void draw(Graphics2D g) {
        
    }

}
