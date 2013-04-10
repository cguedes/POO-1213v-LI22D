package crypt.input;
public class ScannerInput extends AbstractInput {
    
    private static java.util.Scanner kbd = new java.util.Scanner(System.in); 

    @Override
    public void update() {
      releaseAllKeys();
      
      System.out.print("Choose direction (ASDW): ");
      char key = kbd.next().charAt(0);
      key = Character.toUpperCase(key);
      keysDown[key] = true;
    }

}