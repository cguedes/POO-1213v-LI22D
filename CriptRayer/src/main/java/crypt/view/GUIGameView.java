package crypt.view;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import crypt.Game;
import crypt.actor.Actor;
import crypt.actor.Point;

public class GUIGameView {

  public GUIGameView(Game game) {

    JFrame window = new JFrame("Crypt Raider");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createGridWithActors(window, game);
    window.setResizable(false);
    window.pack();
    window.setVisible(true);
  }

  private void createGridWithActors(JFrame window, Game game) {

    int numRows = game.getBoard().getNumRows();
    int numCols = game.getBoard().getNumCols();
    LayoutManager layout = new GridLayout(numRows, numCols);
    window.getContentPane().setLayout(layout);

    Point position = new Point();
    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        position.set(c, r);
        Actor actor = game.getBoard().getActorAt(position);
        JLabel actorView = getActorView(actor);
        window.getContentPane().add(actorView);
      }
    }

  }

  private JLabel getActorView(Actor actor) {
    JLabel actorView = new JLabel();
    String actorImageName = actor.getClass().getSimpleName();
    Icon icon = new ImageIcon("images/actors/" + actorImageName + ".png");
    actorView.setIcon(icon);
    return actorView;
  }

}
