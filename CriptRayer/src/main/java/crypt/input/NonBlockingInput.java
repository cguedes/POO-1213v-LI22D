package crypt.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class NonBlockingInput extends AbstractInput
    implements KeyListener {

  public NonBlockingInput() {
    releaseAllKeys();
    JFrame frame = new JFrame("NonBlockingInput");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JLabel("Choose direction (ASDW)"));
    frame.addKeyListener(this);
    frame.pack();
    frame.setVisible(true);
  }

  public void keyPressed(KeyEvent e) {
    char key = Character.toUpperCase(e.getKeyChar());
    if (Character.isLetter(key)) {
      keysDown[key] = true;
    }
  }

  public void keyReleased(KeyEvent e) {
    char key = Character.toUpperCase(e.getKeyChar());
    if (Character.isLetter(key)) {
      keysDown[key] = false;
    }
  }

  public void keyTyped(KeyEvent e) {
  }

}