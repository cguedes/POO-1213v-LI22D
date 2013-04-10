package crypt.actor;

import crypt.Game;

public class Carter extends Actor {
  
  public Carter(Point position) {
    super('R', position);
  }

  @Override
  public void update() { 

    Point direction = new Point(0, 0);
    if(Game.input.isKeyDown('A')) direction.set(-1,  0);
    if(Game.input.isKeyDown('D')) direction.set(+1,  0);
    if(Game.input.isKeyDown('W')) direction.set( 0, -1);
    if(Game.input.isKeyDown('S')) direction.set( 0, +1);

    Point nextPosition = new Point(this.getPosition());
    nextPosition.add(direction);

    Actor actor = Game.board.getActorAt(nextPosition);
    if(actor == null) {
      this.getPosition().set(nextPosition.x, nextPosition.y);
    }

    if(actor instanceof Dirt) {
      Game.board.removeActor(actor);
      this.getPosition().set(nextPosition.x, nextPosition.y);
    }
    
  }




}

