package crypt.view;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;

import crypt.Game;

public class GUIGameView {

  public GUIGameView(Game game) {

    JFrame window = new JFrame("Crypt Raider");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createGridWithActors(window, game);
    window.pack();
    window.setVisible(true);
  }

  private void createGridWithActors(JFrame window, Game game) {

    // LayoutManager layout = new FlowLayout(FlowLayout.LEFT);
    int numRows = 2, numCols = 2;
    LayoutManager layout = new GridLayout(numRows, numCols);
    window.getContentPane().setLayout(layout);

    window.getContentPane().add(new JButton("Olá"));
    window.getContentPane().add(new JButton(" aula "));
    window.getContentPane().add(new JButton(" de POO"));

  }

}
