package language;

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
public class GrammarTest {
    private Grammar grammarEmpty;
    //testattava
    @InjectMocks
    Grammar grammar;

    //testiä varten erikseen tehty
    @Mock
    Rule ruleMock;

    @Mock
    Rule ruleMock2;

    @Before
    public void setupRule() {
        grammarEmpty = new Grammar();

        ruleMock = Mockito.mock(Rule.class);

        List<String> child = new ArrayList<>();
        child.add("np");
        child.add("vp");

        Mockito.when(ruleMock.getParent()).thenReturn("s");
        Mockito.when(ruleMock.getChild()).thenReturn(child);
    }

    @Test
    public void addRuleAddsRule() {
        grammarEmpty.addRule(ruleMock);
        List<Rule> rules = grammarEmpty.getRules();

        String parent = "s";

        List<String> child = new ArrayList<>();
        child.add("np");
        child.add("vp");

        assertTrue(parent.equals(rules.get(0).getParent()));
        assertTrue(child.equals(rules.get(0).getChild()));
    }

    @Test
    public void addRuleUpdatesMapping() {
        grammar.addRule(ruleMock);

        Mockito.verify(ruleMock, Mockito.times(3)).getParent();
        Mockito.verify(ruleMock, Mockito.times(3)).getChild();

    }

}
