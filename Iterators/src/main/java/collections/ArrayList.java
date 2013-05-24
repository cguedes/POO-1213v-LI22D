package collections;

public class ArrayList /* implements List<Integer> */{

  private Integer[] data;
  private int size = 0;

  public ArrayList() {
    this(10);
  }

  public ArrayList(int initialCapacity) {
    data = new Integer[initialCapacity];
  }

  public int size() {
    return size;
  }

}
