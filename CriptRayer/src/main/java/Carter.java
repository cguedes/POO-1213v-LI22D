
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
    this.getPosition().add(direction);
  }




}

