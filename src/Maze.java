import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
/**
 * This class creates the maze.
 * 
 */

public class Maze extends JPanel implements ActionListener, KeyListener {
    int mazeLength;
    char[][] maze;
    final int scale = 3;
    final int originalTileSize = 16;
    public final int tileSize = 48;
    Timer timer;
    int fps = 30;
    KeyHandler key;
    Player player;
    boolean w, a, s, d;


    public Maze(int level) {
        mazeLength = tileSize * level;
        maze = new char[level][level];
        key = new KeyHandler();
        player = new Player(this);
        this.setPreferredSize(new Dimension(mazeLength, mazeLength));
        this.setBackground(Color.green);
        this.addKeyListener(this);
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
        g.dispose();
    }


    @Override
    public void keyTyped(KeyEvent e) {
       
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            w = true;
            System.out.println("brrr");
        }
        if (code == KeyEvent.VK_A) {
            a = true;
        }
        if (code == KeyEvent.VK_S) {
            s = true;
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
        if (code == KeyEvent.VK_A) {
            a = false;
        }
        if (code == KeyEvent.VK_S) {
            s = false;
        }
        if (code == KeyEvent.VK_D) {
            d = false;
        }
    }
    

}