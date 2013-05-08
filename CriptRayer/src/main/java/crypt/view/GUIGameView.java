package crypt.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import crypt.BoardListener;
import crypt.Game;
import crypt.actor.Actor;
import crypt.actor.Point;
import crypt.input.Input;

public class GUIGameView implements GameView, BoardListener,
    Input, KeyListener {

  private final Game game;

  private final JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
  private final JPanel pnlActorsGrid = new JPanel();
  private final JLabel lblPoints = new JLabel();
  private final JLabel lblNumArtifacts = new JLabel();

  public GUIGameView(Game game) {
    this.game = game;

    JFrame window = new JFrame("Crypt Raider");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    window.getContentPane().setLayout(new BorderLayout());
    window.getContentPane().add(pnlTop, BorderLayout.NORTH);
    window.getContentPane().add(pnlActorsGrid);

    buildTopPanel();
    buildGridWithActors(game);

    window.setResizable(false);
    window.pack();
    window.setVisible(true);

    window.addKeyListener(this);
    releaseAllKeys();

    game.getBoard().setBoardListener(this);
  }

  private void buildTopPanel() {
    pnlTop.add(new JLabel("Points: "));
    pnlTop.add(lblPoints);
    pnlTop.add(new JLabel("Artifacts: "));
    pnlTop.add(lblNumArtifacts);
  }

  private void buildGridWithActors(Game game) {

    int numRows = game.getBoard().getNumRows();
    int numCols = game.getBoard().getNumCols();
    LayoutManager layout = new GridLayout(numRows, numCols);
    pnlActorsGrid.setLayout(layout);

    Point position = new Point();
    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        position.set(c, r);
        Actor actor = game.getBoard().getActorAt(position);
        JLabel actorView = createActorView(actor);
        pnlActorsGrid.add(actorView);
      }
    }

  }

  private JLabel createActorView(Actor actor) {
    JLabel actorView = new JLabel();
    updateActor(actor, actorView);
    return actorView;
  }

  private JLabel getActorViewAt(Point position) {
    int componentIdx = position.y * game.getBoard().getNumCols() + position.x;
    return (JLabel) pnlActorsGrid.getComponent(componentIdx);
  }

  private void updateActor(Actor actor, JLabel actorView) {
    Icon icon = getActorIcon(actor);
    actorView.setIcon(icon);
  }

  private Icon getActorIcon(Actor actor) {
    String actorImageName = actor.getClass().getSimpleName();
    Icon icon = new ImageIcon("images/actors/" + actorImageName + ".png");
    return icon;
  }

  @Override
  public void actorUpdated(Actor actor) {
    JLabel actorView = getActorViewAt(actor.getPosition());
    updateActor(actor, actorView);
  }

  @Override
  public void draw() {
    int points = game.getPoints();
    lblPoints.setText(Integer.toString(points));

    int numArtifacts = game.getNumberOfArtifacts();
    lblNumArtifacts.setText(Integer.toString(numArtifacts));
  }

  protected boolean[] keysDown = new boolean[255];

  protected void releaseAllKeys() {
    for (int i = 0; i < keysDown.length; i++) {
      keysDown[i] = false;
    }
  }

  @Override
  public boolean isKeyDown(char key) {
    return keysDown[key];
  }

  @Override
  public void update() {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    char key = Character.toUpperCase(e.getKeyChar());
    if (Character.isLetter(key)) {
      keysDown[key] = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    char key = Character.toUpperCase(e.getKeyChar());
    if (Character.isLetter(key)) {
      keysDown[key] = false;
    }
  }

  @Override
  public void keyTyped(KeyEvent _) {
  }

}
