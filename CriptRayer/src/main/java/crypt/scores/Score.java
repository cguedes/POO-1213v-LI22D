package crypt.scores;

import java.text.MessageFormat;

public class Score {

  private final String username;
  private final int points;
  private final int level;

  public Score(String username, int points, int level) {
    this.username = username;
    this.points = points;
    this.level = level;
  }

  @Override
  public String toString() {
    return MessageFormat.format(
        "{0}\t{1}\t{2}",
        getUsername(), getPoints(), getLevel()
        );
  }

  public String getUsername() {
    return username;
  }

  public int getPoints() {
    return points;
  }

  public int getLevel() {
    return level;
  }

}
