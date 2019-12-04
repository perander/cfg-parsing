package utils;

import basicdatastructures.List;
import language.Rule;

public class Validator {

    public Rule prepareRule(String s) {
        Rule rule = new Rule();

        String[] temp = s.split(" ");

        String parent = temp[0];
        List child = new List();

        //the rest of the array are the child's elements
        for (int i = 1; i < temp.length; i++) {
            child.add(temp[i]);
        }

        rule.setParent(parent);
        rule.setChild(child);

        return rule;
    }
}
