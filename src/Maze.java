import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * This class creates the maze.
 * 
 */

public class Maze extends JPanel implements ActionListener {
    int mazeLength;
    char[][] maze;
    final int scale = 3;
    final int originalTileSize = 16;
    public final int tileSize = 48;
    Timer timer;
    int fps = 30;
    KeyHandler key;
    Player player;


    public Maze(int level) {
        mazeLength = tileSize * level;
        maze = new char[level][level];
        key = new KeyHandler();
        player = new Player(this);
        this.setPreferredSize(new Dimension(mazeLength, mazeLength));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        timer = new Timer(33, this);
    
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //updates game info
        //player position
        player.update();
        //repaint the panel cuz he moved, got coins etc
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
    }
    

}