package parser;

import basicdatastructures.List;
import language.Grammar;
import language.Rule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EarleyTest {
    private State[][] T;
    private String phrase;
    private String[] words;

    private String parent, parent2, parent3;
    private List<String> child, child2, child3;
    private List<String> allParents;
    private List<List<String>> allChildren;
    private List<String> parentlist, parentlist2, parentlist3, terminals;
    private List<List<String>> childlist, childlist2, childlist3;
    
    private Earley earley;
    private Grammar grammar;
    private State state;
    private State state2;
    private Rule rule;
    private Rule rule2;
    private Rule rule3;
    private Rule startRule;


    @Before
    public void setup() {
        //make phrase
        parent = "s";
        parent2 = "np";
        parent3 = "vp";

        child = new List();
        child.add("np");
        child.add("vp");

        child2 = new List();
        child2.add("n");

        child3 = new List();
        child3.add("v");

        parentlist = new List();
        parentlist.add(parent);
        parentlist2 = new List();
        parentlist2.add(parent2);
        parentlist3 = new List();
        parentlist3.add(parent3);

        childlist = new List();
        childlist.add(child);
        childlist2 = new List();
        childlist2.add(child2);
        childlist3 = new List();
        childlist3.add(child3);

        terminals = new List();
        terminals.add("n");
        terminals.add("v");

        allParents = new List();
        allParents.add(parent);
        allParents.add(parent2);
        allParents.add(parent3);
        allChildren = new List();
        allChildren.add(child);
        allChildren.add(child2);
        allChildren.add(child3);

        rule = new Rule();
        rule2 = new Rule();
        rule3 = new Rule();

        rule.setParent(parent);
        rule.setChild(child);
        rule2.setParent(parent2);
        rule2.setChild(child2);
        rule3.setParent(parent3);
        rule3.setChild(child3);

        grammar = new Grammar();
        grammar.addRule(rule);
        grammar.addRule(rule2);
        grammar.addRule(rule3);

        phrase = "n v";
        words = new String[]{"n", "v"};

        T = new State[3][3];
        earley = new Earley(grammar);
    }

    @Test
    public void belongsToLanguageWorks() {
        assertTrue(earley.belongsToLanguage(grammar, phrase));
    }

    @Test
    public void predictWorks() {
        List<Rule> rules = new List<>();
        rules.add(rule);

        //state has the dot before the parent of state2 (parent2)
        state = new State(rule, 0, 0);
        state2 = new State(rule2, 0, 0);

        assertTrue(T[0][0] == null);

        earley.predict(T, state, 0);

        State found = T[0][0];

        assertTrue(state2.getRule().equals(found.getRule()));
        assertTrue(state2.getOrigin() == found.getOrigin());
        assertTrue(state2.getDot() == found.getDot());
    }

    @Test
    public void scanWorks() {
        state = new State(rule2, 0, 0); //before scan next: n
        state2 = new State(rule2, 1, 0); //after scan next: null

        earley.scan(T, state, 0, words);

        State found = T[1][0];

        assertTrue(state2.getRule().equals(found.getRule()));
        assertTrue(state2.getOrigin() == found.getOrigin());
        assertTrue(state2.getDot() == found.getDot());
    }

    @Test
    public void completeWorks() {
        state = new State(rule, 0, 0); //next np
        state2 = new State(rule2, 1, 0); // completed state with np as parent

        T[0][0] = state;

        earley.complete(T, state2, 1);

        state.incrementDot();

        State found = T[1][0];

        assertTrue(state.getRule().equals(found.getRule()));
        assertTrue(state.getOrigin() == found.getOrigin());
        assertTrue(state.getDot() == found.getDot());
    }
}
