package language;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rule {

    private String parent;
    private List<String> child;

    public Rule() {
    }

    public Rule(String parent, String... child) {
        this.parent = parent;
        this.child = new ArrayList<>();
        for (String element: child) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(parent, rule.parent) &&
                Objects.equals(child, rule.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, child);
    }
}
