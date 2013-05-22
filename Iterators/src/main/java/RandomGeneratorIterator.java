import java.util.Iterator;
import java.util.Random;

public class RandomGeneratorIterator implements Iterator<Integer> {

  private int numIntegersToGenerate;
  private Random random = new Random();

  public RandomGeneratorIterator(int numIntegersToGenerate) {
    this.numIntegersToGenerate = numIntegersToGenerate;
  }

  @Override
  public boolean hasNext() {
    return numIntegersToGenerate > 0;
  }

  @Override
  public Integer next() {
    int value = random.nextInt(6) + 1; // generates values in interval [1, 6]
    --numIntegersToGenerate;
    return value;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  public static void main(String[] args) {

    Iterator<Integer> rg = new RandomGeneratorIterator(6);

    int i = 1;
    while (rg.hasNext()) {
      Integer val = rg.next();
      System.out.println(i++ + ": " + val);
    }

    System.out.println();
    rg = new RandomGeneratorIterator(6);
    i = 1;
    while (rg.hasNext()) {
      Integer val = rg.next();
      System.out.println(i++ + ": " + val);
    }

  }

}
