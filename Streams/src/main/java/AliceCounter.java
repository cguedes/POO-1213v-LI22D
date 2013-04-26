import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;

interface LineCounter {
  int count(String line);
}

class NonEmptyLineCounter implements LineCounter {
  @Override
  public int count(String line) {
    return (line == null || line.trim().isEmpty()) ? 0 : 1;
  }
}

class TextLineCounter implements LineCounter {
  private final String text;

  public TextLineCounter(String text) {
    this.text = text;
  }

  @Override
  public int count(String line) {
    int idx = -1, count = 0;
    while ((idx = line.indexOf(text, idx + 1)) != -1) {
      ++count;
    }
    return count;
  }
}

class NumberOfLettersInLineCounter implements LineCounter {
  @Override
  public int count(String line) {
    int count = 0;
    for (int i = 0; i < line.length(); ++i) {
      if (Character.isLetter(line.charAt(i)))
        count += 1;
    }
    return count;
  }
}

public class AliceCounter {

  public static void main(String[] args)
  {
    int totalLines = getNumberOfNonEmptyLines("alice.txt");
    System.out.println("Number of (non empty) lines: " + totalLines);

    String text = "Alice";
    int numAlices = getTotalOccurencesOf(text, "alice.txt");
    System.out.println(MessageFormat.format("Number of {0} in file: {1}", text, numAlices));

    int numOfLetters = getNumberOfLettersInFile("alice.txt");
    System.out.println(MessageFormat.format("Number of letters in file: {0}", numOfLetters));
  }

  private static int countSomethingInLines(String fileName, LineCounter lineCounter) {
    Reader reader = null;
    int counter = 0;
    try {
      reader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        counter += lineCounter.count(line);
      }
      return counter;
    } catch (IOException ex) {
      System.err.println("Error: " + ex.getMessage());
      return -1;
    } finally {
      closeStream(reader);
    }
  }

  private static int getNumberOfNonEmptyLines(String fileName) {
    return countSomethingInLines(fileName, new NonEmptyLineCounter());
  }

  private static int getTotalOccurencesOf(String textToFind, String fileName) {
    return countSomethingInLines(fileName, new TextLineCounter(textToFind));
  }

  private static int getNumberOfLettersInFile(String fileName) {
    LineCounter lineCounter = new NumberOfLettersInLineCounter();
    return countSomethingInLines(fileName, lineCounter);
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