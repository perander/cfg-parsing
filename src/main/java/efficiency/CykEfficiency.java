package efficiency;

import language.Grammar;
import parser.Cyk;
import parser.Parser;
import ui.UserInterface;
import utils.Validator;

public class CykEfficiency {
    public static void main(String... args) {
        UserInterface ui = new UserInterface();
        Validator validator = new Validator();

        String grammarUnparsed = "s -> np vp:vp -> v np | vp adv | v:np -> n:v -> fish:n -> fish | robots:adv -> today";
        String phrase = "robots fish fish today";

        Grammar grammar = validator.prepareGrammar(grammarUnparsed);
        String[] words = validator.preparePhrase(phrase);

        Parser cyk = new Cyk(grammar);

        long start = System.nanoTime();
        boolean belongs = cyk.belongsToLanguage(grammar, words);
        long end = System.nanoTime();

        System.out.println("The phrase belongs to the language: " + belongs);
        System.out.println("Took " + ((end - start) / 1e9) + "s");
    }
}
