package efficiency;

import language.Grammar;
import parser.Earley;
import parser.Parser;
import utils.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used for speed tests for the Earley parser. It parses one phrase and records the time in nanoseconds.
 */
public class EarleyEfficiency {
    public static void main(String... args) throws IOException {
        Validator validator = new Validator();

        String grammarUnparsed = "s -> l r|l a|p p:p -> l r|l a|p p:a -> p r:l -> (:r -> ):";

        File phraseFile = new File("src/resources/parentheses/phrase.txt");
        BufferedReader reader = new BufferedReader(new FileReader(phraseFile));
        String phraseUnparsed = reader.readLine();

        Grammar grammar = validator.prepareGrammar(grammarUnparsed);
        String[] phrase = validator.preparePhrase(phraseUnparsed);

        Parser earley = new Earley(grammar);

        long start = System.nanoTime();
        boolean belongs = earley.belongsToLanguage(grammar, phrase);
        long end = System.nanoTime();

        System.out.println("The phrase belongs to the language: " + belongs);
        System.out.println("Took " + ((end - start) / 1e6) + "ms");
    }
}
