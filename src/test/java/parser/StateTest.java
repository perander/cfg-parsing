package parser;

import basicdatastructures.List;
import language.Rule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StateTest {
    State state;

    Rule rule;

    private String parent;
    private List<String> child;

    @Before
    public void setup() {
        parent = "s";

        child = new List();
        child.add("np");
        child.add("vp");
    }

    @Test
    public void getNextElementReturnsTheNextElement() {
        rule = new Rule();
        rule.setChild(child);

        state = new State(rule, 0, 0);

        assertTrue("np".equals(state.getnextElement()));

        state.incrementDot();
        assertTrue(1 == state.getDot());
        assertTrue("vp".equals(state.getnextElement()));

        state.incrementDot();
        assertTrue(2 == state.getDot());
        assertTrue(state.getnextElement() == null);
    }
}
