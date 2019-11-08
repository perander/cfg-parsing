
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

        //the amount of words (=terminal symbols) is used when determining the table size
        int n = words.length;

        // Gammar size (number of possible rules) +1 because the first one does not have a parent.
        // Right now it's redundantly big, will be optimised later.
        int m = childtoparent.keySet().size() + parenttochild.keySet().size() + 1;


        //CYK-ALGORITHM (2 parts)

        //the 3-dimensional table is going to be used to construct a parse tree.
        String[][][] T = new String[2*n+1][2*n+1][m+1];
        //initial values: words as one row in the table
        //calling the dimensions: T[row][column][page]
        for (int i = 0; i < n; i++) T[i][i][0] = words[i];

        //fill the bottommost row first with simple rules: fill in the parents whose children have only 1 element
        for (int i = 0; i < n; i++) {
            for (int depth = 0; depth < m; depth++) {
                //fetch the content of a cell
                List<String> possibleRule = new ArrayList<>();
                if(T[i][i][depth] != null) possibleRule.add(T[i][i][depth]);
                else continue;
                //compare the cell content with every rule (could be optimised by only comparing to the ones with only one element)
                for (List<String> rule : childtoparent.keySet()) {
                    if (rule.equals(possibleRule)) {
                        //if found a matching rule, add it's parents to the next empty page corresponding to the cell
                        for (String parent : childtoparent.get(rule)) {
                            T[i][i][nextEmpty(T, i, i)] = parent;
                        }
                    }
                }
            }
        }

        //continuing to the other rows of the table, starting from the next one from the bottommost one

        //length of the substring, starting from shortest
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //2 page counters (=max elements per rule right now)
                    for (int page1 = 0; page1 < m; page1++) {
                        for (int page2 = 0; page2 < m; page2++) {
                            //fetch the contents of the cells
                            List<String> possibleRule = new ArrayList<>();
                            if (T[i][i + j][page1] != null && T[i + j + 1][i + len][page2] != null) {
                                possibleRule.add(T[i][i + j][page1]);
                                possibleRule.add(T[i + j + 1][i + len][page2]);
                            } else continue;

                            //compare the cell content with every rule (could be optimised by only comparing to the ones with only two elements)
                            for (List<String> rule : childtoparent.keySet()) {
                                if (rule.equals(possibleRule)) {
                                    //if found a matching rule, add it's parents to the next empty page corresponding to the current cell
                                    for (String parent : childtoparent.get(rule)) {
                                        T[i][i + len][nextEmpty(T, i, i + len)] = parent;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //now finally deciding whether the sentence belongs to the language generated by the grammar
        boolean belongs = false;

        for (int i = 0; i < m+1; i++) {
            String s = T[0][n-1][i];
            //if it is a parent and if it does not have a parent itself (= if it is the initial symbol)
            if (parenttochild.keySet().contains(s) && !childtoparent.keySet().contains(s)) {
                belongs = true;
            }
        }

        System.out.println(belongs);
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
            if (t[row][column][depth] == null) return depth;
        }
        return -1; //something went wrong (rules repeated or something) (right now this is just a test, will fix later)
    }
}
