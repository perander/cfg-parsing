package parser;

import basicdatastructures.List;
import language.Grammar;
import language.Rule;

/**
 * This class uses the Earley-parser to decide whether a given phrase belongs to a language generated
 * by the given context-free grammar.
 */
public class Earley implements Parser {
    private Grammar grammar;

    public Earley() {
    }

    public Earley(Grammar grammar) {
        this.grammar = grammar;
    }

    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }

    /**
     * {@inheritDoc}
     *
     * @param grammar a context-free grammar (in Chomsky normal form)
     * @param phrase  a string representing a phrase
     * @return
     */
    @Override
    public boolean belongsToLanguage(Grammar grammar, String phrase) {
        //TODO: parsing the input needs to be done somewhere else
        String[] words = phrase.split(" ");

        //create starting state containing the root rule
        Rule startRule = new Rule("start", grammar.getRoot());
        State startState = new State(startRule, 0, 0);

        State[][] t = init(words);

        t = parse(t, words, startState);

        return lastColumnIncludesCompletedStartState(t, words.length, startState);
    }

    /**
     * Initilises the state array.
     *
     * @param words the given phrase as array
     * @return
     */
    public State[][] init(String[] words) {
        State[][] t = new State[words.length + 1][grammar.getAllParents().size() * grammar.getAllChildren().size()];
        return t;
    }

    /**
     * Parses the words-array.
     *
     * @param t          a two-dimensional state array
     * @param words      given phrase as an array
     * @param startState starting state
     * @return the state array after parsing
     */
    public State[][] parse(State[][] t, String[] words, State startState) {
        t = fill(t, 0, startState);

        for (int i = 0; i <= words.length; i++) {
            for (State state : t[i]) {
                if (state != null) {
                    //if state not finished
                    if (!state.isFinished()) {
                        String next = state.getnextElement();
                        if (!grammar.getTerminals().contains(next)) { //if next non-terminal
                            t = predict(t, state, i);
                        } else { //if next terminal
                            t = scan(t, state, i, words);
                        }
                    } else {
                        t = complete(t, state, i);
                    }
                }
            }
        }
        return t;
    }

    /**
     * Adds the given state to the given column in the state array if it's not yet included.
     *
     * @param t     state array
     * @param k     column number
     * @param state given state
     * @return the filled state array
     */
    public State[][] fill(State[][] t, int k, State state) {
        int max = t[k].length;

        for (int col = 0; col < max; col++) {
            if (t[k][col] != null && t[k][col].equals(state)) {
                return t;
            }
        }

        for (int col = 0; col < max; col++) {
            if (t[k][col] == null) {
                t[k][col] = state;
                return t;
            }
        }
        return t;
    }

    /**
     * Given the next element in the given state, finds all rules with the parent same as the next element.
     * Creates a state for each rule with the dot in the beginning (value 0).
     * Adds the states to the column corresponding to the column number k.
     *
     * @param t     state array
     * @param state given state
     * @param k     column number
     * @return the state array
     */
    public State[][] predict(State[][] t, State state, int k) {
        String next = state.getnextElement();

        List<Rule> rules = grammar.getRulesByParent(next);

        for (int i = 0; i < rules.size(); i++) {
            Rule rule = rules.get(i);
            State newState = new State(rule, 0, k);
            t = fill(t, k, newState);
        }
        return t;
    }

    /**
     * Given the next element in the given state, scans the word array at index k for a possible match.
     * If the next element is equal to the k:th word, the state is copied,
     * dot incremented by one, and added to the state array.
     *
     * @param t     state array
     * @param state given state
     * @param k     column number
     * @param k     column number
     * @param words phrase as array
     * @return state array
     */
    public State[][] scan(State[][] t, State state, int k, String[] words) {
        String next = state.getnextElement();

        if (k < words.length && next.equals(words[k])) {
            State newState = new State(state.getRule(), state.getDot() + 1, state.getOrigin());
            //newState.incrementDot();
            t = fill(t, k + 1, newState);
        }
        return t;
    }

    /**
     * Given a completed state, searches the column corresponding to the state's origin for states
     * which have rules with the completed states parent as the next element.
     * Copies them, increments the dot by one and adds them to the state array.
     *
     * @param t     state array
     * @param state given state
     * @param k     column number
     * @return the state array
     */
    public State[][] complete(State[][] t, State state, int k) {
        String parent = state.getRule().getParent();

        for (State s : t[state.getOrigin()]) {
            if (s != null && s.getnextElement() != null && s.getnextElement().equals(parent)) {
                State newState = new State(s.getRule(), s.getDot(), s.getOrigin());
                newState.incrementDot();
                t = fill(t, k, newState);
            }
        }
        return t;
    }

    /**
     * Checks whether the last column includes the completed start state.
     *
     * @param t          state array
     * @param length     word length
     * @param startState the start state
     * @return true if the completed start state is found, false if not
     */
    public boolean lastColumnIncludesCompletedStartState(State[][] t, int length, State startState) {
        boolean found = false;
        State completed = new State(startState.getRule(), startState.getDot(), startState.getOrigin());
        completed.incrementDot();

        for (State state : t[length]) {
            if (state != null && state.equals(completed)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
