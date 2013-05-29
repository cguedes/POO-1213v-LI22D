package collections;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;

public class ArrayListTests {

  @Test
  public void initialSizeOfArrayShouldBeZero() {
    ArrayList list = new ArrayList();
    assertEquals(0, list.size());
  }

  @Test
  public void initialSizeOfArrayShouldBeZeroEvenWithInitialCapacityOf20() {
    ArrayList list = new ArrayList(20);
    assertEquals(0, list.size());
  }

  @Test
  public void shouldAddElement() {
    ArrayList list = new ArrayList();
    boolean res = list.add(42);
    assertTrue(res);
    assertEquals(1, list.size());
  }

  @Test
  public void shouldAddTreeElement() {
    ArrayList list = new ArrayList();
    assertTrue(list.add(42));
    assertTrue(list.add(2));
    assertTrue(list.add(10));
    assertEquals(3, list.size());
  }

  @Test
  public void shouldGetValueWithIndex() {
    ArrayList list = new ArrayList();
    assertTrue(list.add(42));
    assertTrue(list.add(2));
    assertTrue(list.add(10));

    Integer valueAt0 = list.get(0);
    assertEquals(42, valueAt0.intValue());

    Integer valueAt1 = list.get(1);
    assertEquals(2, valueAt1.intValue());

    Integer valueAt2 = list.get(2);
    assertEquals(Integer.valueOf(10), valueAt2);
  }

  @Test
  @Ignore
  public void shouldGrowOfCapacityWasReached() {
    ArrayList list = new ArrayList(2);
    assertTrue(list.add(42));
    assertTrue(list.add(2));

    assertTrue(list.add(10));
  }

  @Test
  public void shouldIterateOverArrayList() {
    int[] expectedValues = { 42, 2, 10 };
    int currentExpectedIndex = 0;

    ArrayList list = new ArrayList();
    for (int v : expectedValues)
      list.add(v);

    Iterator<Integer> iter = list.iterator();
    while (iter.hasNext()) {
      Integer val = iter.next();
      System.out.println(val);
      assertEquals(
          expectedValues[currentExpectedIndex++],
          val.intValue());
    }

    assertEquals(list.size(), currentExpectedIndex);

  }

  @Test
  public void shouldUseIndexOfToReturnIndexOfNullValueAndNonNullValue() {
    ArrayList list = new ArrayList(10);
    list.add(42);
    list.add(2);
    list.add(null);
    list.add(10);
    list.add(3);
    list.add(5);

    int idx = list.indexOf(null);
    assertEquals(2, idx);

    idx = list.indexOf(3);
    assertEquals(4, idx);

  }

  @Test
  public void shouldClearArrayWithElements() {
    ArrayList list = new ArrayList(10);
    list.add(42);
    list.add(2);
    list.add(null);
    list.add(10);
    list.add(3);
    list.add(5);
    assertEquals(6, list.size());

    list.clear();
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
  }

  @Test
  public void shouldUseIndexOfWithEqualsOfArgumentToFindElement() {
    ArrayList list = new ArrayList(10);
    list.add(42);
    list.add(2);
    list.add(null);
    list.add(10);
    list.add(3);
    list.add(5);

    int idx = list.indexOf(new FindByText("10"));
    assertEquals(3, idx);

    idx = list.indexOf(new FindByText("1234"));
    assertEquals(-1, idx);

    assertTrue(list.contains(new FindByText("2")));
  }

  private static class FindByText {
    private final String text;

    public FindByText(String text) {
      this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
      System.out.println("DEBUG: I'm executing equals against " + obj);
      if (obj == null)
        return false;
      return text.equals(obj.toString());
    }

  }

  // ArrayList methods
  //
  // .get(idx) : E
  // .indexOf(obj) : int
  // .addAll(Collection)
  // .clear()
  // .contains(obj) : boolean
  // .isEmpty()
  // .iterator(): Iterator
  // .toArray()

}
