package parser;

import language.Grammar;
import org.junit.Before;
import org.junit.Test;
import ui.UserInterface;

import static org.junit.Assert.assertTrue;

public class ParserTest {
    private Grammar grammar, grammar2, grammar3;
    private String phrase, phrase2, phrase3;
    private String ruleAsString, ruleAsString2, ruleAsString3;

    private UserInterface ui;

    @Before
    public void setup() {
        ui = new UserInterface();
        ruleAsString = "s np vp:vp v np:vp vp adv:vp v:np n:v fish:n fish:n robots:adv today-robots fish fish today";
        ruleAsString2 = "s np vp:vp v np:vp v:np n:v fish:n fish:n robots-fish fish";
        ruleAsString3 = "s np vp:vp v:np n:v fish:n fish-fish fish";

        phrase = parsePhrase(ruleAsString);
        phrase2 = parsePhrase(ruleAsString2);
        phrase3 = parsePhrase(ruleAsString3);

        grammar = parseGrammar(ruleAsString);
        grammar2 = parseGrammar(ruleAsString2);
        grammar3 = parseGrammar(ruleAsString3);
    }

    private String parsePhrase(String input) {
        return input.split("-")[1];
    }

    private Grammar parseGrammar(String input) {
        Grammar grammar = new Grammar();
        String[] rules = input.split("-")[0].split(":");

        for (String rule : rules) {
            grammar.addRule(ui.validator.prepareRule(rule));
        }
        return grammar;
    }

    @Test
    public void cykWorks() {
        Parser cyk = new Cyk(grammar);
        assertTrue(cyk.belongsToLanguage(grammar, phrase));
        cyk = new Cyk(grammar2);
        assertTrue(cyk.belongsToLanguage(grammar2, phrase2));
        cyk = new Cyk(grammar3);
        assertTrue(cyk.belongsToLanguage(grammar3, phrase3));
    }

    @Test
    public void earleyWorks() {
        Parser earley = new Earley(grammar);
        assertTrue(earley.belongsToLanguage(grammar, phrase));
        earley = new Earley(grammar2);
        assertTrue(earley.belongsToLanguage(grammar2, phrase2));
        earley = new Earley(grammar3);
        assertTrue(earley.belongsToLanguage(grammar3, phrase3));
    }
}
