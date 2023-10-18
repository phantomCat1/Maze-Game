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
       //this.key = keyH;
        panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setBounds(20, 20, 48, 48);
        /*upAction = new UpAction();
		downAction = new DownAction();
		leftAction = new LeftAction();
		rightAction = new RightAction();
        panel.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
		panel.getActionMap().put("upAction", upAction);
		panel.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
		panel.getActionMap().put("downAction", downAction);
		panel.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
		panel.getActionMap().put("leftAction", leftAction);
		panel.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
		panel.getActionMap().put("rightAction", rightAction);
        board.add(panel);*/
        
        //this.key
        posX = 20;
        posY = 20;
        try {
            normal = ImageIO.read(new File("C:\\Users\\20232049\\OneDrive - TU Eindhoven\\Documents\\MazeGame\\Character\\pixel_ch_normal.png"));
            left = ImageIO.read(new File("C:\\Users\\20232049\\OneDrive - TU Eindhoven\\Documents\\MazeGame\\Character\\pixel_ch_left.png"));
            right = ImageIO.read(new File("C:\\Users\\20232049\\OneDrive - TU Eindhoven\\Documents\\MazeGame\\Character\\pixel_ch_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

   /*  public class UpAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			posY -= speed;
            panel.setLocation(posX, posY);
		}		
	}
	public class DownAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			posY += speed;	
            panel.setLocation(posX, posY);
		}		
	}
	public class LeftAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			posX -= speed;	
            panel.setLocation(posX, posY);
		}		
	}
	public class RightAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			posX += speed;
            panel.setLocation(posX, posY);
		}		
	}*/

    public void update() {
        movement = false;
        
        if (board.w) {
            posY -= speed;
            movement = true;
        } else if (board.s) {
            posY += speed;
            movement = true;
        } else if (board.a) {
            posX -= speed;
            movement = true;
        } else if (board.d) {
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

        //panel.setLocation(posX, posY);
        
        
        

    }

}
