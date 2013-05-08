package crypt;

import java.text.MessageFormat;

import crypt.actor.Actor;
import crypt.actor.Artifact;
import crypt.actor.Carter;
import crypt.actor.Point;
import crypt.input.Input;
import crypt.view.GameView;

public class Game {

  private static final int POINT_TO_ARTIFACT_REMOVE = 10;
  public Input input;
  public Board board;
  private int currentLevel;

  public Input getInput() {
    return input;
  }

  public void setInput(Input gameInput) {
    input = gameInput;
  }

  public Board getBoard() {
    return board;
  }

  public void setLevel(Board board, int level) {
    this.board = board;
    this.currentLevel = level;
    countArtifacts();
  }

  public void run() throws InterruptedException
  {
    while (true)
    {
      input.update();
      board.update();
      drawGameViews();

      // Aguardar 100ms
      Thread.sleep(100);
    }

  }

  public boolean collide(Actor source, Actor target) {

    Point targetPosition = target.getPosition().copy();
    boolean hasCollided = target.collide(source);
    if (hasCollided) {
      board.moveActor(source, targetPosition);
    }
    return hasCollided;

  }

  private final GameView[] gameViews = new GameView[2];
  private int totalGameViews = 0;

  public void addGameView(GameView gameView) {
    if (totalGameViews > gameViews.length) {
      throw new IllegalStateException(MessageFormat.format("Cannot add more that {0} game views.", gameViews.length));
    }
    if (gameView == null) {
      throw new IllegalArgumentException("gameView");
    }

    gameViews[totalGameViews++] = gameView;
  }

  private void drawGameViews() {
    for (GameView gameView : gameViews) {
      if (gameView != null) {
        gameView.draw();
      }
    }
  }

  int points = 0;

  public int getPoints() {
    return points;
  }

  public void addPoints(int pointsToAdd) {
    this.points += pointsToAdd;
  }

  int totalArtifacts = 0;

  private void countArtifacts() {
    for (Actor actor : getBoard().getActors())
    {
      if (actor instanceof Artifact)
        ++totalArtifacts;
    }
  }

  public int getNumberOfArtifacts() {
    return totalArtifacts;
  }

  public void removeArtifact(Artifact artifactToRemove) {
    getBoard().removeActor(artifactToRemove, true);
    --totalArtifacts;
    addPoints(POINT_TO_ARTIFACT_REMOVE);
  }

  public void removeCarter(Carter carter) {
    if (totalArtifacts == 0) {
      getBoard().removeActor(carter, true);
      // TODO: Trocar de nível
    }
  }

}