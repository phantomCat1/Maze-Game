import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This method checks if movement keys are pressed.
 */
public class KeyHandler implements KeyListener {
    boolean wKey;
    boolean sKey;
    boolean aKey;
    boolean dKey = true;

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
