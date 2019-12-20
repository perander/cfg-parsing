package utils;

import basicdatastructures.List;
import language.Grammar;
import language.Rule;

public class Validator {

    /**
     * Checks whether the given grammar is in the Chomsky normal form.
     * <p>
     * This requires that each rule has 1 or 2 children: either two non-terminal children or one terminal child.
     * There should not be rules with no children.
     *
     * @param grammar
     * @return true if the grammar is in the Chomsky normal form, false otherwise.
     */
    public boolean validateChomsky(Grammar grammar) {
        List<String> parents = grammar.getAllParents();
        List<String> terminals = grammar.getTerminals();

        List<Rule> rules = grammar.getRules();

        for (int i = 0; i < rules.size(); i++) {
            Rule rule = rules.get(i);

            List<String> child = rule.getChild();

            if (childTooLong(child) || mixedTerminals(child, terminals, parents) || tooManyTerminals(child, terminals)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the given child consists of more than 1 terminal symbol
     *
     * @param child
     * @param terminals the terminal symbols of the grammar
     * @return true if there are more than 1 terminal, false otherwise
     */
    private boolean tooManyTerminals(List<String> child, List<String> terminals) {
        //more than one terminal in a row
        if (terminals.contains(child.get(0)) && child.size() != 1) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the given child has more than 2 symbols
     *
     * @param child
     * @return true if there are more than 2 symbols, false otherwise
     */
    private boolean childTooLong(List<String> child) {
        if (child.size() > 2) {
            System.out.println("too long: " + child);
            return true;
        }
        return false;
    }

    /**
     * Checks whether a given child consists of both terminal and non-terminal symbols
     *
     * @param child
     * @param terminals the terminal symbols of the grammar
     * @param parents   the non-terminal symbols of the grammar
     * @return true if the child consists of both kinds of symbols, false otherwise
     */
    private boolean mixedTerminals(List<String> child, List<String> terminals, List<String> parents) {
        //mixing terminals and non-terminals
        if (child.size() > 1) {
            if ((terminals.contains(child.get(0)) && parents.contains(child.get(1))) ||
                    (parents.contains(child.get(0)) && terminals.contains(child.get(1)))) {
                System.out.println("mixing terminals and non-terminals: " + child);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the child has too many elements (over 2) or too little (none)
     *
     * @param rule
     * @return true if the child has 2 or 1 elements, false otherwise
     */
    public boolean validateRule(Rule rule) {
        if (rule.getChild().size() == 0 || rule.getChild() == null) {
            System.out.println("too short");
            return false;
        } else {
            rule.getChild().get(0).split(" ");
            //TODO: System.out.println("rule " + rule.getChild() + " size: " + rule.getChild().size());
            if (rule.getChild().size() > 2) {
                System.out.println("too long");
                return false;
            }
        }
        return true;
    }

    /**
     * Parses the given string to a rule and adds it to the given grammar.
     * The given string may contain multiple children per parent, in format parent -> child1 | child2
     *
     * @param s       the input string representing the rule
     * @param grammar the rule will be added to this grammar
     */
    public void prepareRule(String s, Grammar grammar) {
        if (!s.contains("->") || s.split("->").length < 2) {
            System.out.println("Write the rule in format \"parent -> child\"");
        } else {
            String[] rule = s.split("->");
            String parent = rule[0];
            //separate individual children
            String[] children = rule[1].split("\\|");
            for (String child : children) {
                grammar.addRule(prepareNewRule(parent.trim(), child.trim()));
            }
        }
    }

    /**
     * Prepares the grammar by parsing rules and adding them to the grammar
     *
     * @param s a string representing a grammar
     * @return
     */
    public Grammar prepareGrammar(String s) {
        Grammar grammar = new Grammar();
        //separate rules
        String[] rules = s.split(":");
        for (int i = 0; i < rules.length; i++) {
            //separate parent and children
            prepareRule(rules[i], grammar);
        }
        return grammar;
    }

    /**
     * In the format parent -> child
     *
     * @param parent
     * @param child
     * @return
     */
    public Rule prepareNewRule(String parent, String child) {
        Rule rule = new Rule();
        List<String> elements = new List();
        String[] childElements = child.split(" ");

        for (int i = 0; i < childElements.length; i++) {
            elements.add(childElements[i]);
        }

        rule.setParent(parent);
        rule.setChild(elements);

        if (validateRule(rule)) {
            return rule;
        }
        return rule;
    }

    /**
     * Parses the given phrase
     *
     * @param phrase
     * @return
     */
    public String[] preparePhrase(String phrase) {
        String[] parsed;
        if (phrase != null) {
            if (phrase.contains(" ")) {
                parsed = phrase.split(" ");
            } else {
                parsed = phrase.split("");
            }
            return parsed;
        }
        return null;
    }
}
