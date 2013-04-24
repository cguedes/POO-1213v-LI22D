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
    // TODO
    return 0;
  }

  private static int getTotalOccurencesOf(String textToFind, String fileName) {
    // TODO
    return 0;
  }
}