import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.swing.*;

/**
 * This class creates the maze game itself.
 * It creates a JFrame with two JPanels.
 * The first JPanel displays the map and the character sprite.
 * The second JPanel contains a JButton for restarting the level
 * and a timer for the player to know how much time he has.
 */

public class GameWindow {
    JFrame window;
    Maze maze;
    JPanel panel2;
    JButton restartButton;
    JLabel timeLabel;
    Timer timer;
    int second;
    int minute;
    String ddSecond;
    String ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");
    int currentLevel;
   
    
    /**
     * The constructor of the class.
     * It initiates all the instance variables of the Swing class. 
     */
    public GameWindow() {
        window = new JFrame("Maze Game");
        panel2 = new JPanel();
        restartButton = new JButton("Restart");
        timeLabel = new JLabel("Start");
    }

    /**
     * This method is responsible for initiating the actual game window.
     * It creates an instance of the Maze class which will display the 
     * maze and the character sprite in the first JPanel (the Maze class extends the JPanel class)
     * and displays a JPanel with a JButton and a JLabel.
     * "restartButton" can be used to restart the current level of the game
     * by calling the restartLevel() method.
     * timeLabel displays the timer of the current level to let the player know how much time he has
     * left by calling the countdownTimer() method.
     */
    public void mazeGame(int level) {

        maze = new Maze(level * 5, this);
        maze.startGameThread();
        currentLevel = level;
        restartButton.setFocusable(false);
        restartButton.setFont(new Font("Monospace", Font.BOLD, 20));
        restartButton.setBackground(new Color(0x449c5c));
        restartButton.setForeground(Color.white);
        panel2.setPreferredSize(new Dimension(maze.mazeLength, 50));
        panel2.setBackground(new Color(0x449c5c));
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(restartButton);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setFont(new Font("Monospace", Font.BOLD, 20));
        timeLabel.setForeground(Color.white);
        panel2.add(timeLabel);
        window.add(maze, BorderLayout.NORTH);
        window.add(panel2, BorderLayout.SOUTH);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setFocusable(false);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        

        
        minute = 2 * level;
        second = 0;
        countdownTimer();
        timer.start();

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartLevel();
            }
        });



    }

    
    /**
     * This method restarts the current level of the game when
     * "restartButton" is pressed.
     * It does that by creating a new instance of the GameWindow class and disposing 
     * of the old one.
     */
    public void restartLevel() {
        //if restart button clicked restart level
        window.dispose();
        new GameWindow().mazeGame(currentLevel);

    }

    /**
     * This method creates the countdown timer for the current level.
     * It does that by using a timer and every second it decrements the
     * second variable and it displays the new text in a 00:00 format.
     * When "second" is smaller than zero, meaning a minute has passed, the 
     * "minute" variable decrements and "second" is set to 59, in order to properly
     * simulate the working of a timer.
     * Finally, if the timer hits 00:00, meaning both "minute" and "second" are zero,
     * the game ends, the gameThiread from the Maze class is set to null, thus game
     * panel (the first panel) stops being repainted and the player cannot move anymore.
     * A losw window (JFrame) is displayed by calling the loseWindow() method
     */
    public void countdownTimer() {
        
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                timeLabel.setText(ddMinute + ":" + ddSecond);
                
                if (second == -1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    timeLabel.setText(ddMinute + ":" + ddSecond);
                }
                if (minute == 0 && second == 0) {
                    timer.stop();
                    maze.gameThread = null;
                    loseWindow();
                }
            }
        });
    }

    
    public void winWindow() {
        //creates win frame with 2 buttons
        //press menu to go to levels menu
        //press next level to move to next level
        //change txt file to have the new level
        //maybe implement this as a new class entirely
        //window.dispose();
        try {
            PrintWriter print = new PrintWriter(new FileOutputStream("PlayableLevels.txt"));
            String newLevel = String.valueOf(currentLevel + 1);
            print.print(newLevel);
            print.flush();
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JFrame winFrame = new JFrame("Maze Game");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JButton menu = new JButton("Menu");
        menu.setBackground(new Color(0x449c5c));
        menu.setForeground(Color.white);
        menu.setFont(new Font("Monospace", Font.BOLD, 20));
        menu.setFocusable(false);
        JButton nextLevel = new JButton("Next Level");
        nextLevel.setBackground(new Color(0x449c5c));
        nextLevel.setForeground(Color.white);
        nextLevel.setFont(new Font("Monospace", Font.BOLD, 20));
        nextLevel.setFocusable(false);
        JLabel winMessage = new JLabel("YOU WON!");
        winMessage.setHorizontalAlignment(JLabel.CENTER);
        winMessage.setForeground(new Color(0xffc20e));
        winMessage.setFont(new Font("Monospace", Font.BOLD, 40));
        JLabel label2 = new JLabel("You Completed the Game!");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setForeground(new Color(0xffc20e));
        label2.setFont(new Font("Monospace", Font.BOLD, 30));

        if (currentLevel == 3) {
            winFrame.add(label2);
            menu.setPreferredSize(new Dimension(300, 50));
            winFrame.add(menu, BorderLayout.SOUTH);
        } else {
            panel.setPreferredSize(new Dimension(300, 50));
            panel.add(menu);
            panel.add(nextLevel);
            winFrame.add(winMessage);
            winFrame.add(panel, BorderLayout.SOUTH);
        }

        winFrame.getContentPane().setBackground(new Color(0x449c5c));
        winFrame.setPreferredSize(new Dimension(300, 400));
        winFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        winFrame.pack();
        winFrame.setLocationRelativeTo(null);
        winFrame.setVisible(true);
        winFrame.setResizable(false);
        

        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                winFrame.dispose();
                window.dispose();
                
                new Levels().loadLevels();
            }
        });

        nextLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                winFrame.dispose();
                window.dispose();
                new GameWindow().mazeGame(currentLevel + 1);

            }
        });

    }

    public void loseWindow() {
        //2 buttons
        //retry and back to level menu
        JFrame loseFrame = new JFrame("Maze Game");
        JPanel pan = new JPanel();
        JLabel loseMessage = new JLabel("YOU LOST!");
        loseMessage.setForeground(new Color(0xffc20e));
        loseMessage.setFont(new Font("Monospace", Font.BOLD, 40));
        loseMessage.setHorizontalAlignment(JLabel.CENTER);
        JButton retry = new JButton("Retry");
        retry.setBackground(new Color(0xd1242c));
        retry.setForeground(Color.white);
        retry.setFont(new Font("Monospace", Font.BOLD, 20));
        retry.setFocusable(false);
        JButton menu = new JButton("Menu");
        menu.setBackground(new Color(0xd1242c));
        menu.setForeground(Color.white);
        menu.setFont(new Font("Monospace", Font.BOLD, 20));
        menu.setFocusable(false);
        pan.setPreferredSize(new Dimension(300, 50));
        pan.setLayout(new GridLayout(1, 2));
        pan.add(retry);
        pan.add(menu);
        loseFrame.add(loseMessage);
        loseFrame.add(pan, BorderLayout.SOUTH);


        loseFrame.getContentPane().setBackground(new Color(0xd1242c));
        loseFrame.setPreferredSize(new Dimension(300, 400));
        loseFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        loseFrame.pack();
        loseFrame.setLocationRelativeTo(null);
        loseFrame.setVisible(true);
        loseFrame.setResizable(false);
        

        retry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loseFrame.dispose();
                restartLevel();

            }
        });

        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loseFrame.dispose();
                window.dispose();
                maze.gameThread = null;
                new Levels().loadLevels();
            }
        });
        



    }


}

