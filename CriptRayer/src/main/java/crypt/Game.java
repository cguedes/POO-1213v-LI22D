package crypt;

import java.text.MessageFormat;

import crypt.actor.Actor;
import crypt.actor.Artifact;
import crypt.actor.Carter;
import crypt.actor.DestroyableActor;
import crypt.actor.Door;
import crypt.actor.Key;
import crypt.actor.Point;
import crypt.boardloader.OriginalGameBoardLoader;
import crypt.input.Input;
import crypt.scores.Highscores;
import crypt.view.GameView;

public class Game {

  private static final int POINT_TO_ARTIFACT_REMOVE = 10;
  private static final int POINT_TO_OPEN_DOOR = 15;
  private static final int POINT_FOR_ACTOR_DESTRUCTION = 3;

  public Input input;
  public Board board;
  private int currentLevel;

  private String username = "BART";
  private Highscores highscores = new Highscores();

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
    currentLevel = level;
    countArtifacts();
    fireLevelChanged();
  }

  private void nextLevel() {
    Board board = new OriginalGameBoardLoader(currentLevel + 1).load(this);
    setLevel(board, currentLevel + 1);
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

  private GameListener gameListener = null;

  private void setGameListener(GameListener gameListener) {
    if (this.gameListener != null)
      throw new IllegalStateException("There are already a game listener");
    this.gameListener = gameListener;
  }

  private void fireLevelChanged() {
    if (gameListener != null) {
      gameListener.levelChanged(currentLevel);
    }
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

    if (gameView instanceof GameListener) {
      setGameListener((GameListener) gameView);
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
    totalArtifacts = 0;
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
      nextLevel();
    }
  }

  public void openDoor(Key key) {

    for (Actor actor : getBoard().getActors()) {
      if (actor instanceof Door) {
        getBoard().removeActor(actor, true);
        addPoints(POINT_TO_OPEN_DOOR);
        return;
      }
    }

  }

  public Actor getActorAtDelta(Actor referenceActor, Point direction) {
    return getBoard().getActorAt(referenceActor.getPosition().copy().add(direction));
  }

  public Actor getNextActorInSameDirection(Actor first, Actor second) {
    Point dir = second.getPosition().copy().sub(first.getPosition());
    Point targetPosition = second.getPosition().copy().add(dir);
    return getBoard().getActorAt(targetPosition);
  }

  public void destroyActorsInRectangularArea(Point center, int blastRange) {
    Point position = new Point();
    for (int r = -blastRange; r <= blastRange; ++r)
      for (int c = -blastRange; c <= blastRange; ++c)
      {
        position.set(center.x + c, center.y + r);
        Actor target = getBoard().getActorAt(position);
        if (target instanceof DestroyableActor) {
          DestroyableActor dActor = (DestroyableActor) target;

          if (!(r == 0 && c == 0)) {
            dActor.destroy();
          }
          getBoard().removeActor(target, true);
          addPoints(POINT_FOR_ACTOR_DESTRUCTION);
        }
      }
  }

  public void carterDestroyed() {
    // Save highscores
    highscores.tryAddEntry(username, points, currentLevel);
  }

}