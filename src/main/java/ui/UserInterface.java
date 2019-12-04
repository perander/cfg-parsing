package ui;

import language.Grammar;
import language.Rule;
import parser.Cyk;
import parser.Earley;
import utils.Validator;

import java.io.*;

public class UserInterface {
    public Validator validator;

    public UserInterface() {
        this.validator = new Validator();
    }

    public void startFileReader() throws IOException {
        File file = new File("../resources/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String phrase = "";
        Grammar grammar = new Grammar();

        boolean isPhrase = false;
        String line = reader.readLine();
        while (line != null) {
            if (line.equals("-")) {
                isPhrase = true;
            }

            if (isPhrase) {
                phrase = line;
            } else {
                Rule rule = validator.prepareRule(line);
                grammar.addRule(rule);
            }
        }

        Cyk cykParser = new Cyk();
        Earley earleyParser = new Earley();

        cykParser.setGrammar(grammar);
        System.out.println(cykParser.belongsToLanguage(grammar, phrase));

        earleyParser.setGrammar(grammar);
        System.out.println(earleyParser.belongsToLanguage(grammar, phrase));
    }

    public void startUserInputReader() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader user = new BufferedReader(reader);

        Grammar grammar = new Grammar();
        Cyk cykParser = new Cyk();
        Earley earleyParser = new Earley();

        while (true) {
            System.out.println("Give a rule, elements simply separated by a space, in the format S NP VP. Stop by pressing Enter. ");
            String s = user.readLine();
            if (s.isEmpty()) {
                break;
            }

            Rule rule = validator.prepareRule(s);

            //validate (chomsky normal form: max 2 child elements)

            grammar.addRule(rule);
        }

        System.out.println("Give a sentence:");
        String sentence = user.readLine();

        //validate
        cykParser.setGrammar(grammar);
        System.out.println(cykParser.belongsToLanguage(grammar, sentence));

        earleyParser.setGrammar(grammar);
        System.out.println(earleyParser.belongsToLanguage(grammar, sentence));
    }
}
