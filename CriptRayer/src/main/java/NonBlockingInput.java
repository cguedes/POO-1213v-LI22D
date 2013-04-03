import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class NonBlockingInput implements Input, KeyListener {
    
    private boolean[] keysDown = new boolean[255];

    public NonBlockingInput() {
      releaseAllKeys();
      JFrame frame = new JFrame();
      frame.getContentPane().add(new JLabel("bla bla"));
      frame.pack();
      frame.addKeyListener(this);
      frame.setVisible(true);
    }

    public void update() {
      releaseAllKeys();
      
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
    keysDown[e.getKeyChar()] = true;
   }

   public void keyReleased(KeyEvent e)  {}
   public void keyTyped(KeyEvent e) {}


}