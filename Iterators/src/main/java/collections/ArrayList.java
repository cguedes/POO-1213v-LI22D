package collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList /* implements List<Integer> */{

  private Integer[] data;
  private int size = 0;

  public ArrayList() {
    this(10);
  }

  public ArrayList(int initialCapacity) {
    data = new Integer[initialCapacity];
  }

  public boolean addAll(Collection<Integer> col) {
    // ensureCapacity(col.size());
    for (Integer val : col)
      add(val);

    // Iterable<Integer> iterable = (Iterable<Integer>) col;
    // Iterator<Integer> iter = iterable.iterator();
    // while (iter.hasNext()) {
    // Integer val = iter.next();
    // add(val);
    // }

    return true;
  }

  public int size() {
    return size;
  }

  public boolean add(Integer elem) {
    // TODO: Ensure capacity
    data[size++] = elem;
    return true;
  }

  public Integer get(int idx) {
    return data[idx];
  }

  public int indexOf(Object obj) {

    if (obj == null)
    {
      for (int i = 0; i < size(); i++) {
        if (get(i) == null)
          return i;
      }
    }
    else {
      for (int i = 0; i < size(); i++) {
        if (obj.equals(get(i)))
          return i;
      }
    }

    return -1;
  }

  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void clear() {
    for (int i = 0; i < size; i++) {
      data[i] = null;
    }
    size = 0;

  }

  public Integer[] toArray() {
    return Arrays.copyOf(data, size);
  }

  public Iterator<Integer> iterator() {
    return new Iter<Integer>(this.data, this.size);
  }

  public class Iter<T> implements Iterator<T> {

    private final T[] data;
    private final int length;
    private int index = 0;

    public Iter(T[] data, int length) {
      this.data = data;
      this.length = length;

      if (data == null)
        throw new IllegalArgumentException("data");

      if (length > data.length || length < 0)
        throw new IllegalArgumentException("length");
    }

    @Override
    public boolean hasNext() {
      return index < length;
    }

    @Override
    public T next() {
      if (!hasNext())
        throw new NoSuchElementException();

      return data[index++];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

  // ArrayList methods
  //
  // .addAll(Collection)
  // .toArray()
}
