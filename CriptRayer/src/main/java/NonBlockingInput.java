import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class NonBlockingInput implements Input, KeyListener {
    
    private boolean[] keysDown = new boolean[255];

    public NonBlockingInput() {
      releaseAllKeys();
      JFrame frame = new JFrame("NonBlockingInput");
      frame.add(new JLabel("Choose direction (ASDW)"));
      frame.pack();
      frame.addKeyListener(this);
      frame.setVisible(true);
    }

    public void update() {
    }

    public boolean isKeyDown(char key){
      return keysDown[key];
    }

    private void releaseAllKeys() {
      for (int i = 0; i < keysDown.length; i++) {
        keysDown[i] = false;
      }      
    }


   public void keyPressed(KeyEvent e)  {
      char key = Character.toUpperCase(e.getKeyChar());
      keysDown[key] = true;
   }

   public void keyReleased(KeyEvent e)  {
      char key = Character.toUpperCase(e.getKeyChar());
      keysDown[key] = false;    
   }
   public void keyTyped(KeyEvent e) {}


}