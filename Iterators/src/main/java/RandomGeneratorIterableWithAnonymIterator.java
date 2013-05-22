import java.util.Iterator;

public class RandomGeneratorIterableWithAnonymIterator implements Iterable<Integer> {

  private int numIntegersToGenerate;

  public RandomGeneratorIterableWithAnonymIterator(int numIntegersToGenerate) {
    this.numIntegersToGenerate = numIntegersToGenerate;
  }

  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {

      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public Integer next() {
        return null;
      }

      @Override
      public void remove() {

      }
    };
  }

  public static void main(String[] args) {

    // iterationWithIteratorInterfaceMethods();

    iterationWithFoeachSyntax();

  }

  private static void iterationWithFoeachSyntax() {

    RandomGeneratorIterableWithAnonymIterator randomIterable = new RandomGeneratorIterableWithAnonymIterator(6);

    int i = 1;
    for (Integer val : randomIterable) {
      System.out.println(i++ + ": " + val);
    }

    System.out.println();
    i = 1;
    for (Integer val : randomIterable) {
      System.out.println(i++ + ": " + val);
    }

  }

  private static void iterationWithIteratorInterfaceMethods() {
    RandomGeneratorIterableWithAnonymIterator randomIterable = new RandomGeneratorIterableWithAnonymIterator(6);

    Iterator<Integer> rg = randomIterable.iterator();

    int i = 1;
    while (rg.hasNext()) {
      Integer val = rg.next();
      System.out.println(i++ + ": " + val);
    }

    System.out.println();
    rg = randomIterable.iterator();
    i = 1;
    while (rg.hasNext()) {
      Integer val = rg.next();
      System.out.println(i++ + ": " + val);
    }
  }

}
