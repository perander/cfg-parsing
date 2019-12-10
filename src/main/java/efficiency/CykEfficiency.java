package efficiency;

import language.Grammar;
import parser.Cyk;
import parser.Parser;
import ui.UserInterface;

public class CykEfficiency {
    public static void main(String... args) {
        UserInterface ui = new UserInterface();
        String ruleAsString = "s np vp:vp v np:vp vp adv:vp v:np n:v fish:n fish:n robots:adv today-robots fish fish today";

        String phrase = ruleAsString.split("-")[1];
        Grammar grammar = new Grammar();

        String[] rules = ruleAsString.split("-")[0].split(":");

        for (String rule : rules) {
            grammar.addRule(ui.validator.prepareRule(rule));
        }

        Parser cyk = new Cyk(grammar);

        long start = System.nanoTime();
        boolean belongs = cyk.belongsToLanguage(grammar, phrase);
        long end = System.nanoTime();

        System.out.println("The phrase belongs to the language: " + belongs);
        System.out.println("Took " + ((end - start) / 1e9) + "s");
    }
}
