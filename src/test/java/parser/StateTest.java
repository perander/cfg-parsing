package parser;

import language.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StateTest {
    @InjectMocks
    State state;

    @Mock
    Rule ruleMock;

    private String parent;
    private List<String> child;

    @Before
    public void setup() {
        parent = "s";

        child = new ArrayList<>();
        child.add("np");
        child.add("vp");
    }

    @Test
    public void getNextElementReturnsTheNextElement() {
        Mockito.when(ruleMock.getChild()).thenReturn(child);

        state = new State(ruleMock, 0, 0);

        String next = state.getnextElement();
        assertTrue("np".equals(state.getnextElement()));

        state.incrementDot();
        assertTrue(1 == state.getDot());
        assertTrue("vp".equals(state.getnextElement()));

        state.incrementDot();
        assertTrue(2 == state.getDot());
        assertTrue(state.getnextElement() == null);
    }
}
