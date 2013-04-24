import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SampleFileReader {

  public static void main(String[] args)
  {

    try
    {

      FileReader reader = new FileReader("grades.txt");
      int fileChar;
      while ((fileChar = reader.read()) != -1)
      {
        System.out.print((char) fileChar);
      }
      System.out.println();
      System.out.println("FIM");

    } catch (FileNotFoundException e) {
      System.out.println("Erro: O ficheiro n�o existe: " + e.getMessage());
      System.exit(-1);
    } catch (IOException e) {
      System.out.println("Erro: N�o � poss�vel ler o ficheiro: " + e.getMessage());
      System.exit(-1);
    }

  }
}