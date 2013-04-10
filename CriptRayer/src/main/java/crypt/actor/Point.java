package crypt.actor;

import java.text.MessageFormat;

public class Point {

  public static final Point LEFT = new Point(-1, 0);
  public static final Point RIGHT = new Point(+1, 0);
  public static final Point UP = new Point(0, -1);
  public static final Point DOWN = new Point(0, +1);

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

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    if (!(other instanceof Point))
      return false;

    Point otherPoint = (Point) other;
    return this.x == otherPoint.x && this.y == otherPoint.y;
  }

  @Override
  public String toString() {
    return MessageFormat.format("({0}, {1})", x, y);
  }

}