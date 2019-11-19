package language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Grammar {
    private List<Rule> rules; //could be a set

    //one parent to many children of possibly many elements (S -> NP VP | VP NP)
    private HashMap<String, List<List<String>>> parentToChild;

    //possibly many parents to one children of possibly many elements (S -> NP VP, NP -> NP VP)
    private HashMap<List<String>, List<String>> childToParent;

    public Grammar() {
        this.rules = new ArrayList<>();
        this.parentToChild = new HashMap<>();
        this.childToParent = new HashMap<>();
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

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public HashMap<String, List<List<String>>> getParentToChild() {
        return parentToChild;
    }

    public void setParentToChild(HashMap<String, List<List<String>>> parentToChild) {
        this.parentToChild = parentToChild;
    }

    public HashMap<List<String>, List<String>> getChildToParent() {
        return childToParent;
    }

    public void setChildToParent(HashMap<List<String>, List<String>> childToParent) {
        this.childToParent = childToParent;
    }

    public Set<String> getAllParents() {
        return this.parentToChild.keySet();
    }

    public Set<List<String>> getAllChildren() {
        return this.childToParent.keySet();
    }

    public List<List<String>> getChildrenByParent(String parent) {
        return this.parentToChild.get(parent);
    }

    public List<String> getParentsByChild(List<String> child) {
        return this.childToParent.get(child);
    }

    /**
     * Get all rules which have {@code parent} as parent.
     *
     * @param parent
     * @return list of rules
     */
    public List<Rule> getRulesByParent(String parent) {
        List<Rule> selected = new ArrayList<>();

        for (Rule rule : rules) {
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
        for (String parent : getAllParents()) {
            List<String> parentAsChild = new ArrayList<>();
            parentAsChild.add(parent);

            if (this.getParentsByChild(parentAsChild) == null && !partOfChild(parent)) {
                return parent;
            }
        }
        return null;
    }

    /**
     * Checks whether a given element is a part of a child of some rule in the grammar.
     *
     * @param element
     * @return true if the element is a part of a child, false otherwise.
     */
    private boolean partOfChild(String element) {
        for (List<String> child : getAllChildren()) {
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
        List<String> terminals = new ArrayList<>();

        for (List<String> child : getAllChildren()) {
            if (child.size() == 1) {
                if (getChildrenByParent(child.get(0)) == null) {
                    terminals.add(child.get(0));
                }
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
        this.parentToChild.putIfAbsent(rule.getParent(), new ArrayList<>());
        this.parentToChild.get(rule.getParent()).add(rule.getChild());

        /*Collecting parents to children. There might be many, like N -> fish, V -> fish.
        Here the order of parents does not matter, but still using a list:d.*/
        this.childToParent.putIfAbsent(rule.getChild(), new ArrayList<>());
        this.childToParent.get(rule.getChild()).add(rule.getParent());
    }
}
