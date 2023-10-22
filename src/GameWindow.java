import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.swing.*;

/**
 * This class creates the maze game itself.
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
   
    

    public GameWindow() {
        window = new JFrame("Maze Game");
        
        panel2 = new JPanel();
        restartButton = new JButton("Restart Level");
        timeLabel = new JLabel("Start");
    }

    public void mazeGame(int level) {

        maze = new Maze(level * 5, this);
        maze.startGameThread();
        currentLevel = level;
        restartButton.setFocusable(false);
        panel2.setPreferredSize(new Dimension(maze.mazeLength, 50));
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(restartButton);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(timeLabel);
        window.add(maze, BorderLayout.NORTH);
        window.add(panel2, BorderLayout.SOUTH);


       // window.setPreferredSize(new Dimension(maze.mazeLength, maze.mazeLength + 100));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setFocusable(false);
        
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.pack();

        
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

    

    public void restartLevel() {
        //if restart button clicked restart level
        window.dispose();
        new GameWindow().mazeGame(currentLevel);

    }

    public void countdownTimer() {
        //displays timeLabel
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
        JButton nextLevel = new JButton("Next Level");
        JLabel label1 = new JLabel("YOU WON");
        label1.setHorizontalAlignment(JLabel.CENTER);
        JLabel label2 = new JLabel("You Completed the Game");
        label2.setHorizontalAlignment(JLabel.CENTER);

        if (currentLevel == 3) {
            winFrame.add(label2);
            winFrame.add(menu, BorderLayout.SOUTH);
        } else {
            panel.add(menu);
            panel.add(nextLevel);
            winFrame.add(label1);
            winFrame.add(panel, BorderLayout.SOUTH);
        }

        winFrame.setPreferredSize(new Dimension(300, 400));
        winFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        winFrame.setVisible(true);
        winFrame.setLocationRelativeTo(null);
        winFrame.setResizable(false);
        winFrame.pack();

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
        JLabel loseMessage = new JLabel("YOU LOST");
        JButton retry = new JButton("Retry");
        JButton menu = new JButton("Menu");
        loseMessage.setHorizontalAlignment(JLabel.CENTER);
        pan.setLayout(new GridLayout(1, 2));
        pan.add(retry);
        pan.add(menu);
        loseFrame.add(loseMessage);
        loseFrame.add(pan, BorderLayout.SOUTH);

        loseFrame.setPreferredSize(new Dimension(300, 400));
        loseFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        loseFrame.setVisible(true);
        loseFrame.setLocationRelativeTo(null);
        loseFrame.setResizable(false);
        loseFrame.pack();

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

