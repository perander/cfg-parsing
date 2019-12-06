package utils;

import basicdatastructures.List;
import language.Grammar;
import language.Rule;

public class Validator {

    /**
     * Checks whether the given grammar is in the Chomsky normal form.
     *
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

            //rule too long
            if (child.size() > 2){
                System.out.println("too long: " + child);
                return false;
            }

            //mixing terminals and non-terminals
            if (child.size() > 1) {
                if ((terminals.contains(child.get(0)) && parents.contains(child.get(1))) ||
                        (parents.contains(child.get(0)) && terminals.contains(child.get(1)))) {
                    System.out.println("mixing terminals and non-terminals: " + child);
                    return false;
                }
            }

            //more than one terminal in a row
            if (terminals.contains(child.get(0)) && child.size() != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the child has too many elements (over 2)
     *
     * @param rule
     * @return true if the child has 2 or fewer elements, false otherwise
     */
    public boolean validateRule(Rule rule) {
        rule.getChild().get(0).split(" ");

        //System.out.println("rule " + rule.getChild() + " size: " + rule.getChild().size());
        if (rule.getChild() == null || rule.getChild().size() > 2) {
            return false;
        }
        return true;
    }

    /**
     * Parses the given string to a rule.
     *
     * @param s
     * @return a rule parsed from the string
     */
    public Rule prepareRule(String s) {
        Rule rule = new Rule();

        String[] temp = s.split(" ");

        String parent = temp[0];
        List child = new List();

        //the rest of the array are the child's elements
        for (int i = 1; i < temp.length; i++) {
            child.add(temp[i]);
        }

        rule.setParent(parent);
        rule.setChild(child);

        return rule;
    }

    /**
     * In the format S -> NV | VN
     *
     * @param s
     * @return
     */
    public Grammar prepareGrammar(String s) {
        Grammar grammar = new Grammar();
        //separate rules
        String[] rules = s.split(":");
        for (int i = 0; i < rules.length; i++) {
            //separate parent and children
            String[] rule = rules[i].split("->");
            String parent = rule[0];
            //separate individual children
            String[] children = rule[1].split("\\|");

            for (String child : children) {
                grammar.addRule(prepareNewRule(parent.trim(), child.trim()));
            }
        }
        return grammar;
    }

    /**
     * In the format S -> NV
     *
     * @param parent
     * @param child
     * @return
     */
    public Rule prepareNewRule(String parent, String child){
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
}
