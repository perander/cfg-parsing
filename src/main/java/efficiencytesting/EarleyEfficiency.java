package efficiencytesting;

import language.Grammar;
import parser.Earley;
import parser.Parser;
import ui.UserInterface;

public class EarleyEfficiency {
    public static void main(String... args) {
        UserInterface ui = new UserInterface();
        String ruleAsString = "s np vp:vp v np:vp vp adv:vp v:np n:v fish:n fish:n robots:adv today-robots fish fish today";

        String phrase = ruleAsString.split("-")[1];
        Grammar grammar = new Grammar();

        String[] rules = ruleAsString.split("-")[0].split(":");

        for (String rule : rules) {
            grammar.addRule(ui.prepareRule(rule));
        }

        Parser earley = new Earley(grammar);

        long start = System.nanoTime();
        boolean belongs = earley.belongsToLanguage(grammar, phrase);
        long end = System.nanoTime();

        System.out.println("The phrase belongs to the language: " + belongs);
        System.out.println("Took " + ((end - start) / 1e9) + "s");
    }
}
