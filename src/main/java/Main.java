
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
        HashMap<String, List<List<String>>> parenttochild = new HashMap<>();

        //possibly many parents to one children of possibly many elements (S -> NP VP, NP -> NP VP)
        HashMap<List<String>, List<String>> childtoparent = new HashMap<>();

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader user = new BufferedReader(reader);

        //a minimal "user interface"
        while (true) {
            System.out.println("Give a rule, elements simply separated by a space, in the format S NP VP. Stop by pressing Enter. ");
            String s = user.readLine();
            if (s.isEmpty()) break;

            //after splitting, first element (temp[0]) will be the parent symbol and the rest of the array children symbols
            String[] temp = s.split(" ");

            String parent = temp[0];

            List<String> child = new ArrayList<>();
            parenttochild.putIfAbsent(parent, new ArrayList<>());

            for (int i = 1; i < temp.length; i++) {
                child.add(temp[i]);
            }

            /*Collecting children to a parent. There might be many, like N -> fish, N -> robots.
            The order of the elements of a child matters, that's why using a list.*/
            parenttochild.get(parent).add(child);

            /*Collecting parents to children. There might be many, like N -> fish, V -> fish.
            Here the order of parents does not matter, but still using a list:d.*/
            childtoparent.putIfAbsent(child, new ArrayList<>());
            childtoparent.get(child).add(parent);
        }

        System.out.println("Give a sentence:");
        String sentence = user.readLine();
        String[] words = sentence.split(" ");

        //height and width of the 3-dimensional table is defined by the amount of words
        int n = words.length - 1;

        //'grammar size', number of possible rules, +1 because S does not have a parent
        int m = childtoparent.keySet().size() + 1;

        //CYK with Hashmap

        //the 3-dimensional table is going to be used to construct a parse tree.
        String[][][] T = new String[n+1][n+1][m+1];
        //initial values: words as one row in the table
        for (int i = 0; i < n; i++) T[i][i][0] = words[i];

        //length of the substring, starting from shortest
        for (int len = 0; len < n-1; len++) {
            //i = row and column
            for (int i = 0; i < n; i++) {
                //list all children rules ...
                for (List<String> rule : childtoparent.keySet()) {
                    //... and check each page per cell(i, i+len) ...
                    for (int depth = 0; depth < m; depth++) {
                        //... whether it has the rule.
                        if (rule.equals(T[i][i+len][depth])) {
                            //if yes, add all the rules parents to the cells 'list'
                            for (String parent : childtoparent.get(rule)) T[i][i+len][nextEmpty(T, i, i+len)] = parent;
                        }
                        //check for each possible 'pyramid top' pair (here the max elements per rule is 2)
                        for (int k = 0; k < n; k++) {
                            //TODO: here you could discard all possible rules with an empty cell
                            List<String> possibleRule = new ArrayList<>();
                            possibleRule.add(T[i][i+k][depth]);
                            possibleRule.add(T[i+k+1][i+len][depth]);

                            if (rule.equals(possibleRule)) {
                                for (String parent : childtoparent.get(rule)) T[i][i+len][nextEmpty(T, i, i+len)] = parent;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m+1; i++) {
            System.out.println(T[0][n][i]);
        }


        if (parenttochild.keySet().contains(T[0][n][0]) && !childtoparent.keySet().contains(T[0][n][0])) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    /**
     * Finds the next empty page of a cell(i,j) in the 3-dimensional table T
     * @param t 3-dimensional table
     * @param row row number
     * @param column column number
     * @return the index of the next empty page of cell(i,j)
     */
    private static int nextEmpty(String[][][] t,  int row, int column) {
        for (int depth = 0; depth < t[row][column].length; depth++) {
            if (t[row][column][depth].isEmpty()) return depth;
        }
        return -1; //something went wrong (rules repeated or something) (right now this is just a test, will fix later)
    }
}
