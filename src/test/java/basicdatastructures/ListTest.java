package basicdatastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ListTest {
    private List list;

    @Before
    public void setup() {
        list = new List<Integer>();
    }

    @Test
    public void getReturnsObjectAtCorrectPosition() {
        list.add(0);
        list.add(1);
        list.add(1);

        assertTrue((Integer) 0 == list.get(0));
        assertTrue((Integer) 1 == list.get(1));
        assertTrue((Integer) 1 == list.get(2));
    }

    @Test
    public void sizeWorks() {
        assertTrue(0 == list.size());
        list.add(0);
        assertTrue(1 == list.size());
        list.add(1);
        assertTrue(2 == list.size());
        list.add(1);
    }

    @Test
    public void sizeGrowsExpectedly() {
        assertTrue(0 == list.size());

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        assertTrue(10 == list.size());

        for (int i = 11; i < 21; i++) {
            list.add(i);
        }

        assertTrue(20 == list.size());
    }


    @Test
    public void containsWorks() {
        assertTrue(!list.contains(0));

        list.add(0);

        assertTrue(list.contains(0));
    }


}
