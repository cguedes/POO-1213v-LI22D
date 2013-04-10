package crypt.input;

public abstract class AbstractInput implements Input {
    
    protected boolean[] keysDown = new boolean[255];
    
    protected AbstractInput() {
      releaseAllKeys();
    }

    public void update() { }

    public boolean isKeyDown(char key){
      return keysDown[key];
    }

    protected void releaseAllKeys() {
      for (int i = 0; i < keysDown.length; i++) {
        keysDown[i] = false;
      }      
    }

}