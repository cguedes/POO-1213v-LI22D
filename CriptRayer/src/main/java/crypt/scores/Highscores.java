package crypt.scores;

public class Highscores {

  private final String HIGH_SCORES_FILE_NAME = "scores.txt";
  
  private Score[] scores; ....
  
  public Highscores() {
    // TODO....
    
    
    loadScores();
  }

  public void tryAddEntry(String username, int points, int level) {
    // TODO ...
    // if...
    
    Score score = new Score(username, points, level);
    scores[lastScore++] = score;
    
    
    saveScores();
  }

}
