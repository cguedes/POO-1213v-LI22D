
public class Point {

  public int x, y;

  public Point() {
    set(0, 0);
  }

  public Point(Point p) {
    set(p.x, p.y);
  }

  public Point(int x, int y) {
    set(x, y);
  }

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