package language;

import basicdatastructures.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GrammarTest {
    private Grammar grammar;

    private Rule rule1;
    private Rule rule2;

    private String parent1, parent2;
    private List<String> child1, child2;

    @Before
    public void setup() {
        grammar = new Grammar();

        parent1 = "s";
        parent2 = "np";

        child1 = new List<>();
        child1.add("np");
        child1.add("vp");

        child2 = new List<>();
        child2.add("n");

        rule1 = new Rule();
        rule1.setParent(parent1);
        rule1.setChild(child1);

        rule2 = new Rule();
        rule2.setParent(parent2);
        rule2.setChild(child2);
    }

    @Test
    public void addRuleAddsRule() {
        assertTrue(0 == grammar.getRules().size());

        grammar.addRule(rule1);

        assertTrue(1 == grammar.getRules().size());

        Rule rule = grammar.getRules().get(0);

        assertTrue(parent1.equals(rule.getParent()));
        assertTrue(child1.equals(rule.getChild()));
    }

    @Test
    public void getParentsByChildReturnsCorrectParents() {
        assertTrue(grammar.getParentsByChild(child1).size() == 0);

        grammar.addRule(rule1);
        grammar.addRule(rule2);

        assertTrue(grammar.getParentsByChild(child1).size() == 1);
        assertTrue(grammar.getParentsByChild(child1).contains(parent1));
        assertTrue(grammar.getParentsByChild(child2).size() == 1);
        assertTrue(grammar.getParentsByChild(child2).contains(parent2));

        List<String> parentAsChild = new List<>();
        parentAsChild.add(parent1);

        assertTrue(grammar.getParentsByChild(parentAsChild).size() == 0);
    }

    @Test
    public void getRootReturnsStartingSymbol() {
        grammar.addRule(rule2);

        assertTrue(parent2.equals(grammar.getRoot()));

        grammar.addRule(rule1);

        assertTrue(parent1.equals(grammar.getRoot()));
    }

    @Test
    public void getTerminalsReturnsTerminals() {
        List<String> terminals = new List<>();
        terminals.add("n");
        terminals.add("vp");

        grammar.addRule(rule1);
        grammar.addRule(rule2);

        assertTrue(grammar.getTerminals().contains("n"));
        assertTrue(!grammar.getTerminals().contains("np"));
    }

    @Test
    public void getParentsReturnsAllParents() {
        grammar.addRule(rule1);
        grammar.addRule(rule2);

        assertTrue(grammar.getAllParents().size() == 2);
        assertTrue(grammar.getAllParents().contains(parent1));
        assertTrue(grammar.getAllParents().contains(parent2));
    }

    @Test
    public void getChildrenReturnsAllChildren() {
        grammar.addRule(rule1);
        grammar.addRule(rule2);

        assertTrue(grammar.getAllChildren().size() == 2);
        assertTrue(grammar.getAllChildren().contains(child1));
        assertTrue(grammar.getAllChildren().contains(child2));
    }

    @Test
    public void getRulesByParent() {
        grammar.addRule(rule1);
        grammar.addRule(rule2);

        List<Rule> rules;
        rules = grammar.getRulesByParent(parent1);
        assertTrue(rules.size() == 1);
        assertTrue(rules.contains(rule1));
    }
}
