package efficiency;

import language.Grammar;
import parser.Cyk;
import parser.Parser;
import utils.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used for speed tests for the CYK algorithm. It parses one phrase and records the time in nanoseconds.
 */
public class CykEfficiency {
    public static void main(String... args) throws IOException {
        Validator validator = new Validator();

        String grammarUnparsed = "s -> l r|l a|p p:p -> l r|l a|p p:a -> p r:l -> (:r -> ):";

        File phraseFile = new File("src/resources/parentheses/phrase.txt");
        BufferedReader reader = new BufferedReader(new FileReader(phraseFile));
        String phraseUnparsed = reader.readLine();

        Grammar grammar = validator.prepareGrammar(grammarUnparsed);
        String[] phrase = validator.preparePhrase(phraseUnparsed);

        Parser cyk = new Cyk(grammar);

        long start = System.nanoTime();
        boolean belongs = cyk.belongsToLanguage(grammar, phrase);
        long end = System.nanoTime();

        System.out.println(phraseUnparsed + " - " + belongs);
        System.out.println("Took " + ((end - start) / 1e6) + "ms");
    }
}
