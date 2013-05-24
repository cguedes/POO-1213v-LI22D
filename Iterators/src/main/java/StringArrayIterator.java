import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringArrayIterator implements Iterator<String> {

  private final String[] data;
  private final int length;
  private int index = 0;

  public StringArrayIterator(String[] data, int length) {
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
  public String next() {
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

    Iterator<String> iter = new StringArrayIterator(phrase, 9);

    System.out.println("Phrase words:");
    while (iter.hasNext())
    {
      String word = iter.next();
      System.out.println(word);
    }
    // iter.next();

  }

}
