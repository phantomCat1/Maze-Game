import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class initiates the game and creates the Start menu.
 */

public class StartMenu  {
    JFrame startMenu;
    JButton playButton;
    JButton instructionButton;
    JLabel instructions;
    
    /**
     * The constructor of the class.
     */
    public StartMenu() {
        startMenu = new JFrame("Maze Game");
        playButton = new JButton("Play Game");
        instructionButton = new JButton("Instructions");
    
    }

    /**
    * The method used to initiate the class and start the game.
    */

    public void startGame() {
        startMenu.setPreferredSize(new Dimension(500, 500));
        startMenu.setLayout(new GridLayout(2, 1));
        playButton.setFocusable(false);
        instructionButton.setFocusable(false);
        startMenu.add(playButton);
        startMenu.add(instructionButton);

        instructions = new JLabel("" + "<html>"
        + "1. Collect all the coins and solve the maze before the timer ends<br>"
        + "2. You must reach the red square in order to solve the maze<br>"
        + "3. You cannot finish the level before collecting all coins<br>"
        + "4. Use the W, A, S, D keys to move your character<br>"
        + "Have Fun!!!</html>");

        
        startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startMenu.setVisible(true);
        startMenu.setLocationRelativeTo(null);
        startMenu.setResizable(false);
        startMenu.pack();

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startMenu.dispose();
                new Levels().loadLevels();

            }
        });

        instructionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instructMenu();
            }
        });
    }
    
    /**
     * This method creates the instruction window.
     */
    void instructMenu() {
        JFrame frame = new JFrame("Instructions");
        frame.getContentPane().add(instructions, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

    }
    

}
