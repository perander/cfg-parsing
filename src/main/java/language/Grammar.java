package language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Grammar {
    private List<Rule> rules; //could be a set

    //one parent to many children of possibly many elements (S -> NP VP | VP NP)
    private HashMap<String, List<List<String>>> parentToChild = new HashMap<>();

    //possibly many parents to one children of possibly many elements (S -> NP VP, NP -> NP VP)
    private HashMap<List<String>, List<String>> childToParent = new HashMap<>();

    public Grammar() {
        this.rules = new ArrayList<>();
    }

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
