package utils;

import basicdatastructures.List;
import language.Grammar;
import language.Rule;
import org.junit.Before;
import org.junit.Test;
import ui.UserInterface;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    private Grammar grammar1, grammar2, grammar3;
    private String ruleAsString1, ruleAsString2, ruleAsString3;

    private UserInterface ui;
    private Validator validator;

    @Before
    public void setup() {
        ui = new UserInterface();
        validator = new Validator();

        //incorrect input: rule too long
        ruleAsString1 = "s -> np vp:" +
                "vp -> v:" +
                "np -> n:" +
                "n -> fish:" +
                "np -> fish s fish";

        //incorrect input: mixing terminals and non-terminals
        ruleAsString2 = "s -> np vp:" +
                "vp -> v:" +
                "np -> n:" +
                "n -> fish:" +
                "np -> fish s";

        //correct input
        ruleAsString3 = "s -> np vp:" +
                "vp -> v np | vp adv | v:" +
                "np -> n:" +
                "v -> fish:" +
                "n -> fish | robots:" +
                "adv -> today";

        grammar1 = validator.prepareGrammar(ruleAsString1);
        grammar2 = validator.prepareGrammar(ruleAsString2);
        grammar3 = validator.prepareGrammar(ruleAsString3);
    }

    @Test
    public void prepareGrammarWorks() {
        for (int i = 0; i < grammar3.getRules().size(); i++) {
            System.out.println(grammar3.getRules().get(i));
        }
        assertTrue(grammar3.getRules().size() == 9);
        assertTrue(grammar3.getAllParents().size() == 6);
    }

    @Test
    public void validateRuleWorksWithCorrectRules() {
        List<Rule> rules = grammar3.getRules();

        for (int i = 0; i < rules.size(); i++) {
            assertTrue(validator.validateRule(rules.get(i)));
        }
    }

    @Test
    public void validateRuleDoesNotWorkWithIncorrectRules() {
        List<Rule> rules = grammar1.getRules();
        boolean works = true;

        for (int i = 0; i < rules.size(); i++) {
            if (!validator.validateRule(rules.get(i))) {
                works = false;
            }
        }
        assertFalse(works);
    }

    @Test
    public void validateChomskyWorksWithCorrectInput() {
        assertTrue(validator.validateChomsky(grammar3));
    }

    @Test
    public void validateChomskyWorksWithIncorrectInput() {
        System.out.println(grammar2.getTerminals());
        System.out.println(grammar2.getTerminals().size());

        assertFalse(validator.validateChomsky(grammar2));
    }
}