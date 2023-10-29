import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;


/**
 * This class creates the levels' menu.
 * It creates a JFrame with 4 JButtons.
 * 3 of them represent the levels
 * The last button resets the game progress when pressed.
 */

public class Levels {
    JFrame levelMenu;
    JButton level1;
    JButton level2;
    JButton level3;
    JButton resetGame;
    
    /**
     * This is the constructor of the class.
     * It initiates all the variables.
     */
    public Levels() {
        levelMenu = new JFrame("Maze Game");
        level1 = new JButton("Level 1: " + isTheLevelPlayable(1));
        level2 = new JButton("Level 2: " + isTheLevelPlayable(2));
        level3 = new JButton("Level 3: " + isTheLevelPlayable(3));
        resetGame = new JButton("Reset Game");
        
    }

    /**
     * This method creates the level's menu.
     * It creates a JFrame with 4 buttons.
     * 3 Buttons for the levels and one button for resetting the game.
     */
    public void loadLevels() {
        levelMenu.setLayout(new GridLayout(4, 1));
        levelMenu.add(level1);
        levelMenu.add(level2);
        levelMenu.add(level3);
        levelMenu.add(resetGame);
        levelMenu.setPreferredSize(new Dimension(500, 500));
        levelMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelMenu.pack();
        levelMenu.setLocationRelativeTo(null);
        levelMenu.setVisible(true);
        levelMenu.setResizable(false);
        
        level1.setFocusable(false);
        level1.setBackground(new Color(0x449c5c));
        level1.setForeground(Color.white);
        level1.setFont(new Font("Monospace", Font.BOLD, 20));

        level2.setFocusable(false);
        level2.setBackground(new Color(0x449c5c));
        level2.setForeground(Color.white);
        level2.setFont(new Font("Monospace", Font.BOLD, 20));

        level3.setFocusable(false);
        level3.setBackground(new Color(0x449c5c));
        level3.setForeground(Color.white);
        level3.setFont(new Font("Monospace", Font.BOLD, 20));

        resetGame.setFocusable(false);
        resetGame.setBackground(new Color(0x449c5c));
        resetGame.setForeground(Color.white);
        resetGame.setFont(new Font("Monospace", Font.BOLD, 20));

        level1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGame(1);
            }
        });

        level2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isTheLevelPlayable(2).equals("Blocked")) {
                    loadGame(2);
                }
            }
        });

        level3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isTheLevelPlayable(3).equals("Blocked")) {
                    loadGame(3);
                }
            }
        });

        resetGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        
        
    }

    
    /**
     * This method checks the maximum level achieved by the player
     * by reading from the PlayableLevels.txt file. 
     * It then returns the result in the form of a string.
     * This string indicates the status of the levels of the game,
     * whether a level has been completed, is playable, or is still blocked
     * bcause the player has not passed the previous levels.
     */
    public String isTheLevelPlayable(int level) {
        int maxLevelAchieved = 1;
        try {
            Scanner scanner = new Scanner(new File("PlayableLevels.txt"));
            maxLevelAchieved = scanner.nextInt();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        

        if (level < maxLevelAchieved) {
            return "Completed";
        }
        if (level == maxLevelAchieved) {
            return "Play";
        }
         
        return "Blocked";

    }

    /**
     * This method makes an instance of the GameWindow class.
     * When a JButton of a level is pressed, the current level is created 
     * by passing that level as an argument to the GameWindow class.
     */
    public void loadGame(int level) {
        levelMenu.dispose();
        new GameWindow().mazeGame(level);
    }

    /**
     * This method resets the progress of the game.
     * If at any point, the player wishes to reset all his progress
     * and start the game from scratch, from level 1 again, he can do that.
     * When the reset JButton is pressed, this method is called.
     * It overwrites the maximum level achieved by the player stored in the
     * PlayableLevels.txt file to "1", the first level.
     * Then it disposes of the current instance of the Levels class and creates a new one.
     */
    public void resetGame() {
        try {
            PrintWriter reset = new PrintWriter(new FileOutputStream("PlayableLevels.txt"));
            reset.print("1");
            reset.flush();
            reset.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        levelMenu.dispose();
        new Levels().loadLevels();
    }


}
