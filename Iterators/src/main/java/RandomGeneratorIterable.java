import java.util.Iterator;

public class RandomGeneratorIterable implements Iterable<Integer> {

  private int numIntegersToGenerate;

  public RandomGeneratorIterable(int numIntegersToGenerate) {
    this.numIntegersToGenerate = numIntegersToGenerate;
  }

  public Iterator<Integer> iterator() {
    return new RandomGeneratorIterator(numIntegersToGenerate);
  }

  public static void main(String[] args) {

    // iterationWithIteratorInterfaceMethods();

    iterationWithFoeachSyntax();

  }

  private static void iterationWithFoeachSyntax() {

    RandomGeneratorIterable randomIterable = new RandomGeneratorIterable(6);

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
    RandomGeneratorIterable randomIterable = new RandomGeneratorIterable(6);

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
