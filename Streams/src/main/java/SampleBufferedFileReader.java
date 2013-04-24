import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SampleBufferedFileReader {

  public static void main(String[] args)
  {

    try
    {

      FileReader reader = new FileReader("alice.txt");
      BufferedReader buffered = new BufferedReader(reader);
      String line;
      while ((line = buffered.readLine()) != null)
      {
        System.out.println(line);
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