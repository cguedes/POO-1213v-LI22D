package crypt.boardloader;

import crypt.Board;
import crypt.Game;

public interface BoardLoader {

  Board load(Game game);

}
