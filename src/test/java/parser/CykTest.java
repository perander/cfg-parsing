package parser;


import language.Grammar;
import language.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CykTest {
    private String[][][] T;
    private String phrase;
    private String[] words;

    //don't know if this is ideal:D
    private String parent, parent2;
    private List<String> child, child2;
    private Set<String> allParents;
    private Set<List<String>> allChildren;
    private List<String> parentlist;
    private List<String> parentlist2;
    private List<List<String>> childlist;
    private List<List<String>> childlist2;

    @InjectMocks
    Cyk cyk;

    @Mock
    Grammar grammarMock;

    @Mock
    Rule ruleMock;

    @Mock
    Rule ruleMock2;

    //Setting up a small example grammar and the data structures used in the class
    @Before
    public void setup() {
        T = new String[2][2][4];
        //make phrase
        parent = "s";
        parent2 = "np";

        child = new ArrayList<>();
        child.add("np");
        child.add("vp");

        child2 = new ArrayList<>();
        child2.add("n");

        parentlist = new ArrayList<>();
        parentlist.add(parent);
        parentlist2 = new ArrayList<>();
        parentlist2.add(parent2);

        childlist = new ArrayList<>();
        childlist.add(child);
        childlist2 = new ArrayList<>();
        childlist2.add(child2);

        allParents = new HashSet<>();
        allParents.add(parent);
        allParents.add(parent2);
        allChildren = new HashSet<>();
        allChildren.add(child);
        allChildren.add(child2);

        phrase = "n vp";
        words = new String[] {"n", "vp"};

        cyk = new Cyk(grammarMock);

    }

    @Test
    public void belongsToLanguageWorks() {
        T = new String[5][5][10];

        Mockito.when(grammarMock.getParentsByChild(child)).thenReturn(parentlist);
        Mockito.when(grammarMock.getParentsByChild(child2)).thenReturn(parentlist2);


        Mockito.when(grammarMock.getAllParents()).thenReturn(allParents);
        Mockito.when(grammarMock.getAllChildren()).thenReturn(allChildren);

        assertTrue(cyk.belongsToLanguage(grammarMock, phrase));

        T = new String[5][5][10];
        phrase = "n n";

        assertFalse(cyk.belongsToLanguage(grammarMock, phrase));
    }

    @Test
    public void searchFindsAMatchWithMatchingRules() {
        List<String> possibleRule = new ArrayList<>();
        possibleRule.add("np");
        possibleRule.add("vp");

        Mockito.when(grammarMock.getParentsByChild(child)).thenReturn(parentlist);
        //Mockito.when(grammarMock.getParentsByChild(child2)).thenReturn(parentlist2);

        T = cyk.search(T, possibleRule, allChildren, 0, 0);

        assertTrue(T[0][0][0].equals("s"));
        assertTrue(T[0][0][1] == null);
    }

    @Test
    public void fillFirstRowWorks() {
        T = new String[2][2][4];

        int length = 2;
        int maxDepth = 4;

        //Mockito.when(grammarMock.getParentsByChild(child)).thenReturn(parentlist);
        Mockito.when(grammarMock.getParentsByChild(child2)).thenReturn(parentlist2);

        T = cyk.fillFirstRow(T, length, maxDepth, allChildren, words);

        assertTrue(T[0][0][0].equals("n"));
        assertTrue(T[0][0][1].equals("np"));
        assertTrue(T[1][1][0].equals("vp"));
        assertTrue(T[1][1][1] == null);
    }

    @Test
    public void fillRestWorks() {
        T = new String[5][5][10];

        int length = 2;
        int maxDepth = 4;

        Mockito.when(grammarMock.getParentsByChild(child)).thenReturn(parentlist);
        Mockito.when(grammarMock.getParentsByChild(child2)).thenReturn(parentlist2);

        T = cyk.fillFirstRow(T, length, maxDepth, allChildren, words);
        T = cyk.fillRest(T, length, maxDepth, allChildren);

        assertTrue(T[0][0][0].equals("n"));
        assertTrue(T[0][0][1].equals("np"));
        assertTrue(T[1][1][0].equals("vp"));
        assertTrue(T[1][1][1] == null);

        assertTrue(T[0][1][0].equals("s"));
    }

    @Test
    public void topRulesIncludeStartingSymbolWorks() {
        T = new String[5][5][10];

        int length = 2;
        int maxDepth = 4;

        Mockito.when(grammarMock.getParentsByChild(child)).thenReturn(parentlist);
        Mockito.when(grammarMock.getParentsByChild(child2)).thenReturn(parentlist2);

        T = cyk.fillFirstRow(T, length, maxDepth, allChildren, words);
        T = cyk.fillRest(T, length, maxDepth, allChildren);

        assertTrue(T[0][0][0].equals("n"));
        assertTrue(T[0][0][1].equals("np"));
        assertTrue(T[1][1][0].equals("vp"));
        assertTrue(T[1][1][1] == null);

        assertTrue(T[0][1][0].equals("s"));

        boolean found = cyk.topRulesIncludeStartingSymbol(T, 2, 10, allParents, allChildren);

        assertTrue(found);
    }

    @Test
    public void nextEmptyFindsNextEmptyCell() {
        assertTrue(0 == cyk.nextEmpty(T, 0, 0));

        T[0][0][0] = "s";

        assertTrue(1 == cyk.nextEmpty(T, 0, 0));
    }
}
