import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class initiates the game and creates the Start menu.
 * The Start menu consists of a JLabel that makes up the title 
 * of the game and a panel with 2 buttons.
 * The "Play Game" button loads up the levels' menu
 * The "Instructions" button creates a new JFrame which contains a JLabel
 * with the rules of the game.
 */

public class StartMenu  {
    JFrame startMenu;
    JPanel panel;
    JLabel title;
    JButton playButton;
    JButton instructionButton;
    JLabel instructions;
    ImageIcon titleName;
    
    /**
     * The constructor of the class.
     * It initializez all of the instance variables.
     */
    public StartMenu() {
        startMenu = new JFrame("Maze Game");
        panel = new JPanel();
        titleName = new ImageIcon(getClass().getResource("/Character/title.png"));
        title = new JLabel();
        playButton = new JButton("Play Game");
        instructionButton = new JButton("Instructions");
    
    }

    /**
     * This method is used to initialize the class and creates the Start menu.
     * It creates a JFrame with GridLayout as it's layout manager.
     * It contains a JLabel with the title image and a JPanel with two JButtons.
     * playButton loads up the levels' menu by making an instance of the Levels class.
     * instructionButton creates the instructions window by calling the instructMenu method.
    */

    public void startGame() {
        
        panel.setLayout(new GridLayout(2, 1));
        panel.setPreferredSize(new Dimension(500, 100));
        panel.setBackground(new Color(0x449c5c));
        title.setIcon(titleName);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        playButton.setFocusable(false);
        playButton.setPreferredSize(new Dimension(500, 50));
        playButton.setBackground(new Color(0x449c5c));
        playButton.setForeground(Color.white);
        playButton.setFont(new Font("Monospace", Font.BOLD, 25));
        instructionButton.setFocusable(false);
        instructionButton.setBackground(new Color(0x449c5c));
        instructionButton.setForeground(Color.white);
        instructionButton.setFont(new Font("Monospace", Font.BOLD, 25));
        instructionButton.setPreferredSize(new Dimension(500, 50));
        
        panel.add(playButton);
        panel.add(instructionButton, BorderLayout.SOUTH);
        startMenu.setLayout(new GridLayout(2, 1));
        startMenu.setPreferredSize(new Dimension(500, 500));
        startMenu.getContentPane().setBackground(new Color(0x449c5c));
        startMenu.add(title);
        startMenu.add(panel);

        instructions = new JLabel("" + "<html>"
        + "1. Collect all the coins and solve the maze before the timer ends<br>"
        + "2. You must reach the trophy in order to solve the maze<br>"
        + "3. You cannot finish the level before collecting all coins<br>"
        + "4. Use the W, A, S, D keys to move your character<br>"
        + "Have Fun!!!</html>");

        
        startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startMenu.pack();
        startMenu.setLocationRelativeTo(null);
        startMenu.setVisible(true);
        startMenu.setResizable(false);
        

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
     * This method creates the instructions window.
     * It creates a new JFrame that contains the rules of the game.
     * It's default close operation is purposefully set to HIDE_ON_CLOSE
     * so that the the game doesn't terminate when the user closes it.
     */
    void instructMenu() {
        JFrame frame = new JFrame("Instructions");
        instructions.setHorizontalAlignment(JLabel.CENTER);
        
        instructions.setFont(new Font("Monospace", Font.BOLD, 20));
        frame.getContentPane().add(instructions, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

    }
    

}
