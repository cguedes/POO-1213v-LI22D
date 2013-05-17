package crypt.scores;

import java.util.Comparator;

public class ScoreComparatorByLevel implements Comparator<Score> {

  @Override
  public int compare(Score a, Score b) {
    return b.getLevel() - a.getLevel();
  }

}
