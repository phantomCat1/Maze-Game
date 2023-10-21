
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
/**
 * This is the main class of the Maze Game.
 * 
 * @author Marian Luca (1971972)
 * @author Alexandra Gutu (1974173)
 */
public class Main {
    public static void main(String[] args) {
        new StartMenu().startGame();
        JPanel panel = new JPanel();
        
        /*JFrame window = new JFrame("mze");
        Maze bruh = new Maze(10);
        panel.setPreferredSize(new Dimension(bruh.mazeLength, 100));
        panel.setBackground(Color.pink);
        bruh.startGameThread();
        window.add(panel, BorderLayout.SOUTH);
        window.add(bruh, BorderLayout.NORTH);
        window.setPreferredSize(new Dimension(bruh.mazeLength, bruh.mazeLength));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.pack();*/
      
    }
    
}
