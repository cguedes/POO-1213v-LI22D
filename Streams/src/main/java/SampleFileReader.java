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
      System.out.println("Erro: O ficheiro não existe: " + e.getMessage());
      System.exit(-1);
    } catch (IOException e) {
      System.out.println("Erro: Não é possível ler o ficheiro: " + e.getMessage());
      System.exit(-1);
    }

  }
}