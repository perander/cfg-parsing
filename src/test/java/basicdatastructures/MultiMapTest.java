package basicdatastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MultiMapTest {
    private MultiMap map;

    @Before
    public void setup() {
        map = new MultiMap<String, Integer>();
    }

    @Test
    public void isEmptyWorks() {
        assertTrue(map.isEmpty());

        map.put("one", 1);

        assertTrue(!map.isEmpty());
    }

    @Test
    public void putWorks() {
        assertTrue(map.isEmpty());

        map.put("one", 1);

        assertTrue(map.keySet().contains("one"));
        assertTrue(map.valueSet().contains(1));
    }

    @Test
    public void getValueByKeyWorks() {
        assertTrue(map.isEmpty());

        map.put("one", 1);
        List<Integer> values = map.get("one");

        assertTrue(values.size()==1 && values.contains(1));

        map.put("one", 2);
        values = map.get("one");

        assertTrue(values.size()==2 && values.contains(1) && values.contains(2));
    }

    @Test
    public void keySetOnlyHasDistinctKeys() {
        assertTrue(map.isEmpty());

        map.put("one", 1);

        assertTrue(map.keySet().size()==1 && map.keySet().contains("one"));

        map.put("one", 2);

        assertTrue(map.keySet().size()==1 && map.keySet().contains("one"));

        map.put("two", 2);

        assertTrue(map.keySet().size()==2 && map.keySet().contains("one") && map.keySet().contains("two"));
    }

    @Test
    public void valueSetOnlyHasDistinctKeys() {
        assertTrue(map.isEmpty());

        map.put("one", 1);

        assertTrue(map.valueSet().size()==1 && map.valueSet().contains(1));

        map.put("two", 1);

        assertTrue(map.valueSet().size()==1 && map.valueSet().contains(1));

        map.put("two", 2);

        assertTrue(map.valueSet().size()==2 && map.valueSet().contains(1) && map.valueSet().contains(2));
    }
}
