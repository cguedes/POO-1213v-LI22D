package writers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

class Line {
  public Line(int lineNumber, String text) {
    this.lineNumber = lineNumber;
    this.text = text;
  }

  public final int lineNumber;
  public final String text;
}

// Criar um ficheiro que contém todas as linhas em que consta Alice
public class AliceWrittings {

  public static void main(String[] args) throws IOException
  {
    Line[] aliceLines = readLinesWith("Alice", "alice.txt", 1000);
    writeLinesTo(aliceLines, "alice-lines.txt");
    System.out.println("DONE");
  }

  private static void writeLinesTo(Line[] aliceLines, String fileName) throws IOException {

    PrintWriter printWriter = null;
    try {
      printWriter = new PrintWriter(new FileWriter(fileName));
      for (int i = 0; i < aliceLines.length; ++i) {
        Line line = aliceLines[i];
        if (line == null)
          break;
        printWriter.println(MessageFormat.format("{0}: {1}", line.lineNumber, line.text));
        printWriter.flush();
        System.out.println("line written...");
      }

    } finally {
      if (printWriter != null) {
        printWriter.close();
      }
    }

  }

  private static Line[] readLinesWith(String filterText, String fileName, int maxResults) {
    // TODO: Implement this at home!
    return new Line[] {
        new Line(9, "Title: Alice's Adventures in Wonderland"),
        new Line(45, "it, 'and what is the use of a book,' thought Alice 'without pictures or"),
        new Line(561, "'Only a thimble,' said Alice sadly.")
    };
  }
}