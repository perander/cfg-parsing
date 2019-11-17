package ui;

import language.Grammar;
import language.Rule;
import parser.Cyk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    //receive grammar input
    //validate
    //parse grammar
    //receive word input
    //validate
    //parse word

    //listener for quitting
    public void start() throws IOException {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader user = new BufferedReader(reader);

        Grammar grammar = new Grammar();
        Cyk cykParser = new Cyk();

        while (true) {
            System.out.println("Give a rule, elements simply separated by a space, in the format S NP VP. Stop by pressing Enter. ");
            String s = user.readLine();
            if (s.isEmpty()) {
                break;
            }

            Rule rule = prepareRule(s);

            //validate (chomsky normal form: max 2 child elements)

            grammar.addRule(rule);
        }

        System.out.println("Give a sentence:");
        String sentence = user.readLine();

        //validate
        cykParser.setGrammar(grammar);
        System.out.println(cykParser.belongsToLanguage(grammar, sentence));
    }

    private Rule prepareRule(String s) {
        Rule rule = new Rule();

        String[] temp = s.split(" ");

        String parent = temp[0];
        List<String> child = new ArrayList<>();

        //the rest of the array are the child's elements
        for (int i = 1; i < temp.length; i++) {
            child.add(temp[i]);
        }

        rule.setParent(parent);
        rule.setChild(child);

        return rule;
    }
}
