
public class Point {

  public int x, y;

  public void set(int _x, int _y)
  {
    x = _x;
    y = _y;
  }

  public void add(Point p)
  {
    x += p.x;
    y += p.y;
  }

}