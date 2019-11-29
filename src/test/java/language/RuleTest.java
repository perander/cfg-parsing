package language;

import basicdatastructures.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RuleTest {

    private Rule ruleGiven;
    private Rule ruleEmpty;
    private String parent;
    private List<String> child = new List();

    @Before
    public void setupRule() {
        parent = "s";
        child.add("np");
        child.add("vp");
        ruleGiven = new Rule("s", "np", "vp");
        ruleEmpty = new Rule();
    }

    @Test
    public void setParentSetsParent() {
        ruleEmpty.setParent(parent);
        assertTrue(ruleEmpty.getParent().equals(parent));
    }

    @Test
    public void setChildSetsChild() {
        ruleEmpty.setChild(child);
        assertTrue(ruleEmpty.getChild().equals(child));
    }

    @Test
    public void rulesAreEqualWithSameParentAndChildren() {
        ruleEmpty.setParent(parent);
        ruleEmpty.setChild(child);

        assertTrue(ruleGiven.equals(ruleEmpty));
    }


}
