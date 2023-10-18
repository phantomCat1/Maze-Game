import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;


/**
 * fe.
 */

public class Levels {
    JFrame levelMenu;
    JButton level1;
    JButton level2;
    JButton level3;
    JButton resetGame;
    

    public Levels() {
        levelMenu = new JFrame("Maze Game");
        level1 = new JButton("Level 1: " + isTheLevelPlayable(1));
        level2 = new JButton("Level 2: " + isTheLevelPlayable(2));
        level3 = new JButton("Level 3: " + isTheLevelPlayable(3));
        resetGame = new JButton("Reset Game");
        
    }
    public void loadLevels() {
        levelMenu.setLayout(new GridLayout(4, 1));
        levelMenu.add(level1);
        levelMenu.add(level2);
        levelMenu.add(level3);
        levelMenu.add(resetGame);
        levelMenu.setPreferredSize(new Dimension(500, 500));
        levelMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelMenu.setVisible(true);
        levelMenu.setLocationRelativeTo(null);
        levelMenu.setResizable(false);
        levelMenu.pack();
        level1.setFocusable(false);
        level2.setFocusable(false);
        level3.setFocusable(false);
        resetGame.setFocusable(false);

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

    public void loadGame(int level) {
        levelMenu.dispose();
        GameWindow game = new GameWindow();
        game.mazeGame(level);
    }

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
