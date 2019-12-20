package parser;

import basicdatastructures.List;
import language.Grammar;
import language.Rule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CykTest {

    private String[][][] t;
    private String[] phrase;

    private String parent, parent2;
    private List<String> child, child2;
    private List<String> allParents;
    private List<List<String>> allChildren;
    private List<String> parentlist, parentlist2;
    private List<List<String>> childlist, childlist2;

    private Cyk cyk;
    private Grammar grammar = new Grammar();
    private Rule rule1 = new Rule();
    private Rule rule2 = new Rule();


    @Before
    public void setup() {
        t = new String[5][5][10];
        //make phrase
        parent = "s";
        parent2 = "np";

        child = new List();
        child.add("np");
        child.add("vp");

        child2 = new List();
        child2.add("n");

        rule1.setParent(parent);
        rule1.setChild(child);
        rule2.setParent(parent2);
        rule2.setChild(child2);

        parentlist = new List();
        parentlist.add(parent);
        parentlist2 = new List();
        parentlist2.add(parent2);

        childlist = new List();
        childlist.add(child);
        childlist2 = new List();
        childlist2.add(child2);

        allParents = new List();
        allParents.add(parent);
        allParents.add(parent2);
        allChildren = new List();
        allChildren.add(child);
        allChildren.add(child2);

        phrase = new String[]{"n", "vp"};

        grammar.addRule(rule1);
        grammar.addRule(rule2);

        cyk = new Cyk(grammar);
    }

    @Test
    public void belongsToLanguageWorks() {
        assertTrue(cyk.belongsToLanguage(grammar, phrase));

        phrase = new String[]{"n", "n"};

        assertFalse(cyk.belongsToLanguage(grammar, phrase));
    }

    @Test
    public void searchFindsAMatchWithMatchingRules() {
        List<String> possibleRule = new List();
        possibleRule.add("np");
        possibleRule.add("vp");

        t = cyk.search(t, possibleRule, allChildren, 0, 0);

        assertTrue(t[0][0][0].equals("s"));
        assertTrue(t[0][0][1] == null);
    }


    @Test
    public void fillFirstRowWorks() {
        int length = 2;
        int maxDepth = 4;

        t = cyk.fillFirstRow(t, length, maxDepth, allChildren, phrase);

        assertTrue(t[0][0][0].equals("n"));
        assertTrue(t[0][0][1].equals("np"));
        assertTrue(t[1][1][0].equals("vp"));
        assertTrue(t[1][1][1] == null);
    }

    @Test
    public void fillRestWorks() {
        int length = 2;
        int maxDepth = 4;

        t = cyk.fillFirstRow(t, length, maxDepth, allChildren, phrase);
        t = cyk.fillRest(t, length, maxDepth, allChildren);

        assertTrue(t[0][0][0].equals("n"));
        assertTrue(t[0][0][1].equals("np"));
        assertTrue(t[1][1][0].equals("vp"));
        assertTrue(t[1][1][1] == null);

        assertTrue(t[0][1][0].equals("s"));
    }

    @Test
    public void topRulesIncludeStartingSymbolWorks() {
        int length = 2;
        int maxDepth = 4;

        t = cyk.fillFirstRow(t, length, maxDepth, allChildren, phrase);
        t = cyk.fillRest(t, length, maxDepth, allChildren);

        assertTrue(t[0][0][0].equals("n"));
        assertTrue(t[0][0][1].equals("np"));
        assertTrue(t[1][1][0].equals("vp"));
        assertTrue(t[1][1][1] == null);

        assertTrue(t[0][1][0].equals("s"));

        boolean found = cyk.topRulesIncludeStartingSymbol(t, 2, 10, allParents, allChildren);

        assertTrue(found);
    }

    @Test
    public void nextEmptyFindsNextEmptyCell() {
        assertTrue(0 == cyk.nextEmpty(t, 0, 0));

        t[0][0][0] = "s";

        assertTrue(1 == cyk.nextEmpty(t, 0, 0));
    }
}
