package parser;


public class EarleyTest {
    /*private State[][] T;
    private String phrase;
    private String[] words;

    //don't know if this is ideal:D
    private String parent, parent2, parent3;
    private List<String> child, child2, child3;
    private Set<String> allParents;
    private Set<List<String>> allChildren;
    private List<String> parentlist, parentlist2, parentlist3, terminals;
    private List<List<String>> childlist, childlist2, childlist3;

    @InjectMocks
    Earley earley;

    @Mock
    Grammar grammarMock;

    @Mock
    State stateMock;

    @Mock
    State stateMock2;

    @Mock
    Rule ruleMock;

    @Mock
    Rule ruleMock2;

    @Mock
    Rule ruleMock3;

    @Mock
    Rule startRuleMock;


    @Before
    public void setup() {
        //make phrase
        parent = "s";
        parent2 = "np";
        parent3 = "vp";

        child = new ArrayList<>();
        child.add("np");
        child.add("vp");

        child2 = new ArrayList<>();
        child2.add("n");

        child3 = new ArrayList<>();
        child3.add("v");

        parentlist = new ArrayList<>();
        parentlist.add(parent);
        parentlist2 = new ArrayList<>();
        parentlist2.add(parent2);
        parentlist3 = new ArrayList<>();
        parentlist3.add(parent3);

        childlist = new ArrayList<>();
        childlist.add(child);
        childlist2 = new ArrayList<>();
        childlist2.add(child2);
        childlist3 = new ArrayList<>();
        childlist3.add(child3);

        terminals = new ArrayList<>();
        terminals.add("n");
        terminals.add("v");

        allParents = new HashSet<>();
        allParents.add(parent);
        allParents.add(parent2);
        allParents.add(parent3);
        allChildren = new HashSet<>();
        allChildren.add(child);
        allChildren.add(child2);
        allChildren.add(child3);

        phrase = "n v";
        words = new String[]{"n", "v"};

        T = new State[3][3];
        earley = new Earley(grammarMock);
    }

    /*@Test
    public void belongsToLanguageWorks() {
        when(grammarMock.getRoot()).thenReturn(parent);
        when(grammarMock.getTerminals()).thenReturn(terminals);

        when(ruleMock.getParent()).thenReturn(parent);
        when(ruleMock.getChild()).thenReturn(child);
        when(ruleMock2.getParent()).thenReturn(parent2);
        when(ruleMock2.getChild()).thenReturn(child2);
        when(ruleMock3.getParent()).thenReturn(parent3);
        when(ruleMock3.getChild()).thenReturn(child3);

        when(startRuleMock.getParent()).thenReturn("start");
        when(startRuleMock.getChild()).thenReturn(parentlist);

        when(stateMock.getRule()).thenReturn(startRuleMock);
        when(stateMock.getDot()).thenReturn(0);
        when(stateMock.getOrigin()).thenReturn(0);

        assertTrue(earley.belongsToLanguage(grammarMock, phrase));
    }

    @Test
    public void predictWorks() {
        when(stateMock.getnextElement()).thenReturn(parent2);
        when(ruleMock.getParent()).thenReturn(parent2);
        when(ruleMock.getChild()).thenReturn(child2);

        List<Rule> rules = new ArrayList<>();
        rules.add(ruleMock);

        when(grammarMock.getRulesByParent(parent2)).thenReturn(rules);

        when(stateMock2.getRule()).thenReturn(ruleMock);
        when(stateMock2.getDot()).thenReturn(0);
        when(stateMock2.getOrigin()).thenReturn(0);

        assertTrue(T[0][0] == null);

        earley.predict(T, stateMock, 0);

        State found = T[0][0];

        assertTrue(stateMock2.getRule().equals(found.getRule()));
        assertTrue(stateMock2.getOrigin() == found.getOrigin());
        assertTrue(stateMock2.getDot() == found.getDot());
    }

    @Test
    public void scanWorks() {
        when(stateMock.getRule()).thenReturn(ruleMock);
        when(stateMock.getDot()).thenReturn(0);
        when(stateMock.getOrigin()).thenReturn(0);
        when(stateMock.getnextElement()).thenReturn("n");

        when(ruleMock.getParent()).thenReturn(parent2);
        when(ruleMock.getChild()).thenReturn(child2);

        when(stateMock2.getRule()).thenReturn(ruleMock);
        when(stateMock2.getDot()).thenReturn(1);
        when(stateMock2.getOrigin()).thenReturn(0);

        earley.scan(T, stateMock, 0, words);

        State found = T[1][0];

        assertTrue(stateMock2.getRule().equals(found.getRule()));
        assertTrue(stateMock2.getOrigin() == found.getOrigin());
        assertTrue(stateMock2.getDot() == found.getDot());

        when(stateMock.getnextElement()).thenReturn("v");

        T = new State[words.length + 1][words.length + 1];

        earley.scan(T, stateMock, 0, words);
        found = T[1][0];

        assertTrue(found == null);
    }

    @Test
    public void completeWorks() {
        when(ruleMock.getParent()).thenReturn(parent); //s
        when(ruleMock.getChild()).thenReturn(child); //np vp

        when(ruleMock2.getParent()).thenReturn(parent2); //np
        when(ruleMock2.getChild()).thenReturn(child2); //n

        when(stateMock.getRule()).thenReturn(ruleMock);
        when(stateMock.getDot()).thenReturn(0);
        when(stateMock.getOrigin()).thenReturn(0);
        when(stateMock.getnextElement()).thenReturn("np");

        when(stateMock2.getRule()).thenReturn(ruleMock2);
        when(stateMock2.getDot()).thenReturn(1);
        when(stateMock2.getOrigin()).thenReturn(0);

        T[0][0] = stateMock;

        earley.complete(T, stateMock2, 1);

        when(stateMock.getDot()).thenReturn(1);

        State found = T[1][0];

        assertTrue(stateMock.getRule().equals(found.getRule()));
        assertTrue(stateMock.getOrigin() == found.getOrigin());
        assertTrue(stateMock.getDot() == found.getDot());
    }

    @Test
    public void lastCellIncludesCompletedStartStateWorks() {

    }

    @Test
    public void nextEmptyFindsNextEmptyCell() {
        assertTrue(0 == earley.nextEmpty(T, 0));
        T[0][0] = stateMock;
        assertTrue(1 == earley.nextEmpty(T, 0));
    }*/


}
