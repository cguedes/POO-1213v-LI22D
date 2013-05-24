import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

  private final T[] data;
  private final int length;
  private int index = 0;

  public ArrayIterator(T[] data, int length) {
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

  public static void main(String[] args) {

    String[] phrase = { "Hello", "beautifull", "world", "of", "Java", null, null };
    Iterator<String> iter = new ArrayIterator<String>(phrase, 3);

    System.out.println("Phrase words:");
    while (iter.hasNext())
    {
      String word = iter.next();
      System.out.println(word);
    }
    // iter.next();

    // Auto boxing
    Integer i = 2;
    // Auto unboxing
    int j = i;

    Integer[] numbers = { 10, 4, 23, 8, 11, 3, 4 };
    Iterator<Integer> iterNumbers = new ArrayIterator<Integer>(numbers, numbers.length);

    System.out.println("Phrase words:");
    while (iterNumbers.hasNext())
    {
      Integer intValue = iterNumbers.next();
      System.out.println(intValue);
    }

  }

}
