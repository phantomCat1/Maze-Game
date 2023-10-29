import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This method checks if movement keys are pressed.
 * If a key is pressed, the boolean variable of that key is set to true.
 * These boolean variables ar used in the Player class to indicate that the player is moving
 * and as such update his coordinates and initiate the movement animation.
 */
public class KeyHandler implements KeyListener {
    boolean wKey;
    boolean sKey;
    boolean aKey;
    boolean dKey;

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            wKey = true;
        } 
        if (code == KeyEvent.VK_S) {
            sKey = true;
        }
        if (code == KeyEvent.VK_A) {
            aKey = true;
        }
        if (code == KeyEvent.VK_D) {
            dKey = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            wKey = false;
        }
        if (code == KeyEvent.VK_S) {
            sKey = false;
        }
        if (code == KeyEvent.VK_A) {
            aKey = false;
        }
        if (code == KeyEvent.VK_D) {
            dKey = false;
        }
    }
    
}
