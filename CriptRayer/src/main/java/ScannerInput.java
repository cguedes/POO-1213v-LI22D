public class ScannerInput implements Input {
    
    private static java.util.Scanner kbd = new java.util.Scanner(System.in); 

    private boolean[] keysDown = new boolean[255];

    public ScannerInput() {
      releaseAllKeys();
    }

    public void update() {
      releaseAllKeys();
      
      System.out.print("Choose direction (ASDW): ");
      char key = kbd.next().charAt(0);
      key = Character.toUpperCase(key);
      keysDown[key] = true;
    }

    public boolean isKeyDown(char key){
      return keysDown[key];
    }

    private void releaseAllKeys() {
      for (int i = 0; i < keysDown.length; i++) {
        keysDown[i] = false;
      }      
    }

}