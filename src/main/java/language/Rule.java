
package language;

import basicdatastructures.List;

import java.util.Objects;

/**
 * A class representing a rule in a context-free grammar. A rule has one parent and one child with one or more elements. Parents and children are Strings.
 *
 */
public class Rule {

    private String parent;
    private List child;

    public Rule() {
    }

    public Rule(String parent, String... child) {
        this.parent = parent;
        this.child = new List<String>();
        for (String element : child) {
            this.child.add(element);
        }
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<String> getChild() {
        return child;
    }

    public void setChild(List<String> child) {
        this.child = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return Objects.equals(this.parent, rule.parent) &&
                Objects.equals(this.child, rule.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, child);
    }

    @Override
    public String toString() {
        return "Rule{" +
                "parent='" + parent + '\'' +
                " -> child=" + child +
                '}';
    }
}
