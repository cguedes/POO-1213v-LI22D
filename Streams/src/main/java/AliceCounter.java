import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;

public class AliceCounter {

  public static void main(String[] args)
  {
    int totalLines = getNumberOfNonEmptyLines("alice.txt");
    System.out.println("Number of (non empty) lines: " + totalLines);

    String text = "Alice";
    int numAlices = getTotalOccurencesOf(text, "alice.txt");
    System.out.println(MessageFormat.format("Number of {0} in file: {1}", text, numAlices));
  }

  private static int getNumberOfNonEmptyLines(String fileName) {
    Reader reader = null;
    int numEmptyLines = 0;
    try {
      // reader = new StringReader("Olá\n\nMundo.");
      reader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        line = line.trim();
        if (!line.isEmpty())
          ++numEmptyLines;
      }
      return numEmptyLines;
    } catch (IOException ex) {
      System.err.println("Error: " + ex.getMessage());
      return -1;
    } finally {
      closeStream(reader);
    }
  }

  private static int getTotalOccurencesOf(String textToFind, String fileName) {
    Reader reader = null;
    int numOccurs = 0;
    try {
      reader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        numOccurs += getTotalOccurencesInLine(textToFind, line);
      }
      return numOccurs;
    } catch (IOException ex) {
      System.err.println("Error: " + ex.getMessage());
      return -1;
    } finally {
      closeStream(reader);
    }
  }

  private static int getTotalOccurencesInLine(String textToFind, String line) {
    int idx = -1, count = 0;
    while ((idx = line.indexOf(textToFind, idx + 1)) != -1) {
      ++count;
    }
    return count;
  }

  private static void closeStream(Reader reader) {
    try {
      if (reader != null)
        reader.close();
    } catch (IOException ex) {
      System.err.println("Cannot close the stream! " + ex.getMessage());
    }
  }
}