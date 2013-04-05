import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class NonBlockingInput extends AbstractInput 
                              implements KeyListener {
    
    public NonBlockingInput() {
      releaseAllKeys();
      JFrame frame = new JFrame("NonBlockingInput");
      frame.add(new JLabel("Choose direction (ASDW)"));
      frame.addKeyListener(this);
      frame.pack();
      frame.setVisible(true);
    }

   public void keyPressed(KeyEvent e)  {
      char key = Character.toUpperCase(e.getKeyChar());
      if(Character.isLetter(key)) {
        keysDown[key] = true;
      }
   }

   public void keyReleased(KeyEvent e)  {
      char key = Character.toUpperCase(e.getKeyChar());
      if(Character.isLetter(key)) {
        keysDown[key] = false;    
      }
   }
   public void keyTyped(KeyEvent e) {}


}