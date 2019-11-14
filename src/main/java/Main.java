import language.Grammar;
import language.Rule;
import parser.Cyk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String... args) throws IOException {
        //Maps representing the rules as parent - child(ren) relations:

        //one parent to many children of possibly many elements (S -> NP VP | VP NP)
        HashMap<String, List<List<String>>> parentToChild = new HashMap<>();

        //possibly many parents to one children of possibly many elements (S -> NP VP, NP -> NP VP)
        HashMap<List<String>, List<String>> childToParent = new HashMap<>();

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader user = new BufferedReader(reader);

        List<Rule> rules;

        //a minimal "user interface"
        while (true) {
            System.out.println("Give a rule, elements simply separated by a space, in the format S NP VP. Stop by pressing Enter. ");
            String s = user.readLine();
            if (s.isEmpty()) break;

            //after splitting, first element (temp[0]) will be the parent symbol and the rest of the array children symbols
            String[] temp = s.split(" ");

            Rule rule = new Rule();

            String parent = temp[0];
            rule.setParent(parent);

            List<String> child = new ArrayList<>();
            parentToChild.putIfAbsent(parent, new ArrayList<>());

            for (int i = 1; i < temp.length; i++) {
                child.add(temp[i]);

            }
            rule.setChild(child);

            /*Collecting children to a parent. There might be many, like N -> fish, N -> robots.
            The order of the elements of a child matters, that's why using a list.*/
            parentToChild.get(parent).add(child);

            /*Collecting parents to children. There might be many, like N -> fish, V -> fish.
            Here the order of parents does not matter, but still using a list:d.*/
            childToParent.putIfAbsent(child, new ArrayList<>());
            childToParent.get(child).add(parent);
        }

        System.out.println("Give a sentence:");
        String sentence = user.readLine();

        Grammar grammar = new Grammar();

        Cyk CYKparser = new Cyk(grammar);
        System.out.println(CYKparser.belongsToLanguage(grammar, sentence));
    }
}
