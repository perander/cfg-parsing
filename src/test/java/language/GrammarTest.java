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

    @InjectMocks
    Grammar grammar;

    @Mock
    Rule ruleMock;

    @Mock
    Rule ruleMock2;

    @Before
    public void setup() {
        grammarEmpty = new Grammar();
        grammar = new Grammar();

        ruleMock = Mockito.mock(Rule.class);

        String parent = "s";

        List<String> child = new ArrayList<>();
        child.add("np");
        child.add("vp");

        Mockito.when(ruleMock.getParent()).thenReturn(parent);
        Mockito.when(ruleMock.getChild()).thenReturn(child);

        ruleMock2 = Mockito.mock(Rule.class);

        String parent2 = "np";

        List<String> child2 = new ArrayList<>();
        child2.add("n");

        Mockito.when(ruleMock2.getParent()).thenReturn(parent2);
        Mockito.when(ruleMock2.getChild()).thenReturn(child2);
    }

    @Test
    public void addRuleAddsRule() {
        grammar.addRule(ruleMock);
        List<Rule> rules = grammar.getRules();

        String parent = "s";

        List<String> child = new ArrayList<>();
        child.add("np");
        child.add("vp");

        assertTrue(1 == grammar.getRules().size());

        Rule rule = rules.get(0);

        assertTrue(ruleMock.equals(rule));

        assertTrue(parent.equals(rule.getParent()));
        assertTrue(child.equals(rule.getChild()));
    }

    @Test
    public void getParentsByChildReturnsCorrectParents() {
        grammar.addRule(ruleMock);
        grammar.addRule(ruleMock2);

        String parent = "s";

        List<String> child = new ArrayList<>();
        child.add("np");
        child.add("vp");

        assertTrue(grammar.getParentsByChild(child).size() == 1);
        assertTrue(grammar.getParentsByChild(child).contains(parent));
    }

    @Test
    public void getParentsByChildReturnsNullWhenNoParents() {
        grammar.addRule(ruleMock);
        grammar.addRule(ruleMock2);

        List<String> child = new ArrayList<>();
        child.add("s");

        assertTrue(grammar.getParentsByChild(child) == null);
    }

    @Test
    public void addRuleUpdatesMapping() {
        grammar.addRule(ruleMock);

        Mockito.verify(ruleMock, Mockito.times(3)).getParent();
        Mockito.verify(ruleMock, Mockito.times(3)).getChild();
    }

    @Test
    public void getRootReturnsStartingSymbol() {
        String root = "s";

        grammar.addRule(ruleMock);
        grammar.addRule(ruleMock2);

        assertTrue(root.equals(grammar.getRoot()));
    }

    @Test
    public void getParentsReturnsAllParents() {
        grammar.addRule(ruleMock);
        grammar.addRule(ruleMock2);

        String parent = "s";
        String parent2 = "np";

        assertTrue(grammar.getAllParents().size() == 2);
        assertTrue(grammar.getAllParents().contains(parent));
        assertTrue(grammar.getAllParents().contains(parent2));
    }

    @Test
    public void getTerminalsReturnsTerminals() {
        List<String> terminals = new ArrayList<>();
        terminals.add("n");
        terminals.add("vp");

        grammar.addRule(ruleMock);
        grammar.addRule(ruleMock2);

        assertTrue(grammar.getTerminals().contains("n"));
        assertTrue(!grammar.getTerminals().contains("np"));
    }

}
