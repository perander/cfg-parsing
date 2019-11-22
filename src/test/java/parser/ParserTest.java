package parser;

import language.Grammar;
import org.junit.Before;
import org.junit.Test;
import ui.UserInterface;

import static org.junit.Assert.assertTrue;

public class ParserTest {
    private Grammar grammar;
    private String phrase;
    private String ruleAsString, ruleAsString2, ruleAsString3;

    @Before
    public void setup() {
        UserInterface ui = new UserInterface();
        grammar = new Grammar();
        ruleAsString = "s np vp:vp v np:vp vp adv:vp v:np n:v fish:n fish:n robots:adv today-robots fish fish today";
        ruleAsString2 = "s np vp:vp v np:vp v:np n:v fish:n fish:n robots-fish fish";
        ruleAsString3 = "s np vp:vp v:np n:v fish:n fish-fish fish";

        String parse = ruleAsString2;

        phrase = parse.split("-")[1];

        String[] rules = parse.split("-")[0].split(":");

        for (String rule : rules) {
            grammar.addRule(ui.prepareRule(rule));
        }
    }

    @Test
    public void cykWorks() {
        Parser cyk = new Cyk(grammar);
        assertTrue(cyk.belongsToLanguage(grammar, phrase));
    }

    @Test
    public void earleyWorks() {
        Parser earley = new Earley(grammar);
        assertTrue(earley.belongsToLanguage(grammar, phrase));
    }
}
