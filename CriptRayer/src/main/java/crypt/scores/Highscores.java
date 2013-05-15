package crypt.scores;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Scanner;

public class Highscores {

  private final String HIGH_SCORES_FILE_PATH = "scores/scores.txt";
  private static final int MAX_SCORES = 10;
  private int nextScoreIdx = 0;
  private Score[] scores = new Score[MAX_SCORES];

  public Highscores() {
    loadScores();
  }

  public void tryAddEntry(String username, int points, int level) {

    Score score = new Score(username, points, level);
    scores[nextScoreIdx++] = score;
    saveScores();
  }

  private void loadScores() {

    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(HIGH_SCORES_FILE_PATH));

      String line = null;
      while ((line = reader.readLine()) != null) {
        readScoreLine(line);
      }

    } catch (FileNotFoundException _) {
      // This is not a problem!
      // Let scores array be empty
    } catch (IOException exp) {
      // This is a little problem!
      // The file content is not in the format we expected!
      // Let scores array be empty and warn
      System.err.println("WARN: The highscores file format is invalid. The file will be deleted!");
      System.err.println("Exception message: " + exp.getMessage());
      File file = new File(HIGH_SCORES_FILE_PATH);
      if (file.exists()) {
        file.delete();
      }
    } finally {
      closeCloseable(reader);
    }

  }

  private void closeCloseable(Closeable closeable) {
    try {
      if (closeable != null)
      {
        closeable.close();
      }
    } catch (IOException e) {
      System.err.println("WARN: Unexpected exception on close! ");
      System.err.println(e.getMessage());
    }
  }

  private void readScoreLine(String line) {
    Scanner lineScanner = new Scanner(line);
    String username = lineScanner.next();
    int points = lineScanner.nextInt();
    int level = lineScanner.nextInt();
    Score score = new Score(username, points, level);
    scores[nextScoreIdx++] = score;
    lineScanner.close();
  }

  private void saveScores() {

    PrintWriter writer = null;

    try {
      writer = new PrintWriter(HIGH_SCORES_FILE_PATH);
      for (Score score : scores) {
        if (score != null) {
          writer.println(
              MessageFormat.format(
                  "{0} {1} {2}",
                  score.getUsername(), score.getPoints(), score.getLevel()
                  )
              );
        }
      }
    } catch (FileNotFoundException e) {
      // TODO ..... FAZER EM CASA!
    } finally {
      closeCloseable(writer);
    }

    // TEMP CODE
    System.out.println("SCORES");
    System.out.println("===============");
    for (Score score : scores) {
      if (score != null) {
        System.out.println(score);
      }
    }
    System.out.println();
  }

}
