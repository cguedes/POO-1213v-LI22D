package collections;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class ArrayListTests {

  @Test
  public void initialSizeOfArrayShouldBeZero() {

    ArrayList list = new ArrayList();
    assertEquals(0, list.size());

  }

}
