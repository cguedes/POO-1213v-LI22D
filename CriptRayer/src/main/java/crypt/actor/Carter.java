package crypt.actor;

import crypt.Game;

public class Carter extends Actor {
  
  public Carter(Point position, Game game) {
    super('R', position, game);
  }

  @Override
  public void update() { 

    Point direction = new Point(0, 0);
    if(game.getInput().isKeyDown('A')) direction.set(-1,  0);
    if(game.getInput().isKeyDown('D')) direction.set(+1,  0);
    if(game.getInput().isKeyDown('W')) direction.set( 0, -1);
    if(game.getInput().isKeyDown('S')) direction.set( 0, +1);

    Point nextPosition = new Point(this.getPosition());
    nextPosition.add(direction);

    Actor actor = game.getBoard().getActorAt(nextPosition);
    if(actor == null) {
      this.getPosition().set(nextPosition.x, nextPosition.y);
    }

    if(actor instanceof Dirt) {
      game.getBoard().removeActor(actor);
      this.getPosition().set(nextPosition.x, nextPosition.y);
    }
    
  }




}

