package language;

import basicdatastructures.List;
import basicdatastructures.MultiMap;

/**
 * A class representing a context-free grammar. It consists of a list of rules and provides information about them.
 *
 */
public class Grammar {
    private List<Rule> rules;

    private MultiMap<String, List<String>> parentToChild;
    private MultiMap<List<String>, String> childToParent;

    public Grammar() {
        this.rules = new List();
        this.parentToChild = new MultiMap();
        this.childToParent = new MultiMap();
    }

    /**
     * Adds a new rule to the grammar and updates the child-parent-mappings.
     *
     * @param rule
     */
    public void addRule(Rule rule) {
        this.rules.add(rule);
        this.updateMapping(rule);
    }

    public List<Rule> getRules() {
        return rules;
    }

    public List<String> getAllParents() {
        return this.parentToChild.keySet();
    }

    public List<List<String>> getAllChildren() {
        return this.childToParent.keySet();
    }

    public List<List<String>> getChildrenByParent(String parent) {
        return this.parentToChild.get(parent);
    }

    public List getParentsByChild(List<String> child) {
        return this.childToParent.get(child);
    }

    /**
     * Get all rules which have {@code parent} as parent.
     *
     * @param parent
     * @return list of rules
     */
    public List<Rule> getRulesByParent(String parent) {
        List<Rule> selected = new List();

        for (int i = 0; i < rules.size(); i++) {
            Rule rule = rules.get(i);
            if (rule.getParent().equals(parent)) {
                selected.add(rule);
            }
        }
        return selected;
    }

    /**
     * Find the starting symbol of the grammar. The symbol does not have a parent and it's not an element of any child.
     *
     * @return starting symbol if found, null otherwise
     */
    public String getRoot() {
        String root = null;

        List<String> parents = getAllParents();
        for (int i = 0; i < parents.size(); i++) {
            String parent = parents.get(i);

            List<String> parentAsChild = new List();
            parentAsChild.add(parent);

            if (this.getParentsByChild(parentAsChild).size() == 0 && !partOfChild(parent)) {
                root = parent;
                break;
            }
        }
        return root;
    }

    /**
     * Checks whether a given element is a part of a child of some rule in the grammar.
     *
     * @param element
     * @return true if the element is a part of a child, false otherwise.
     */
    private boolean partOfChild(String element) {
        List<List<String>> children = getAllChildren();
        for (int i = 0; i < children.size(); i++) {
            List<String> child = children.get(i);
            if (child.contains(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the terminal symbols of the grammar. The terminal symbols are one-element children with no children themselves.
     *
     * @return a list of terminal symbols
     */
    public List<String> getTerminals() {
        List<String> terminals = new List();
        List<List<String>> children = this.getAllChildren();

        for (int i = 0; i < children.size(); i++) {
            List<String> child = children.get(i);
            if (child.size() == 1 && getChildrenByParent(child.get(0)).size() == 0) {
                terminals.add(child.get(0));
            }
        }
        return terminals;
    }

    /**
     * Updates the parent-child-mappings.
     *
     * @param rule
     */
    private void updateMapping(Rule rule) {
        /*Collecting children to a parent. There might be many, like N -> fish, N -> robots.
        The order of the elements of a child matters, that's why using a list.*/

        this.parentToChild.put(rule.getParent(), rule.getChild());

        /*Collecting parents to children. There might be many, like N -> fish, V -> fish.
        Here the order of parents does not matter, but still using a list :d.*/

        this.childToParent.put(rule.getChild(), rule.getParent());
    }
}
