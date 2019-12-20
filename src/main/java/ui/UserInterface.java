package ui;

import basicdatastructures.List;
import language.Grammar;
import parser.Cyk;
import parser.Earley;
import parser.Parser;
import utils.Validator;


import java.io.*;

public class UserInterface {
    public Validator validator;
    private String instructions;

    public UserInterface() {
        this.validator = new Validator();
        instructions = "\nInstructions: \n1. try some demo grammars\n2. try your own grammar\nhelp: see instructions\nquit: stop the program\n";
    }

    public void start() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader user = new BufferedReader(reader);
        System.out.println(instructions);

        while (true) {
            String input = user.readLine();
            if (checkIfQuitOrHelp(input, user)) {
                continue;
            } else if (input.equals("1")) {
                startDemo(user);
            } else if (input.equals("2")) {
                startUserInputReader(user);
            } else {
                System.out.println(instructions);
            }
        }
    }

    private void startDemo(BufferedReader user) throws IOException {
        List<String> files = new List();
        File resources = new File("src/resources");
        if (resources.listFiles().length == 0) {
            System.out.println("There are no files yet :(\n\n" + instructions);
        } else {
            for (int i = 0; i < resources.listFiles().length; i++) {
                files.add(resources.listFiles()[i].toString());
            }
            while (true) {
                System.out.println("Choose a grammar to test (a number between " + 1 + " and " + (files.size()) + "):");
                for (int i = 0; i < files.size(); i++) {
                    System.out.println(i + 1 + ": " + files.get(i).split("/")[2]);
                }
                String s = user.readLine();
                if (checkIfQuitOrHelp(s, user)) {
                    break;
                } else {
                    try {
                        Integer choice = Integer.parseInt(s);
                        if (choice > 0 && choice <= files.size()) {
                            startFileReader(files.get(choice - 1));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Give a number between " + 1 + " and " + (files.size()) + ")\n");
                    }
                }
            }
        }
    }

    public void startFileReader(String directory) throws IOException {
        File grammarFile = new File(directory + "/grammar.txt");
        File phraseFile = new File(directory + "/phrase.txt");

        BufferedReader reader = new BufferedReader(new FileReader(grammarFile));
        String grammarUnparsed = reader.readLine();
        Grammar grammar = validator.prepareGrammar(grammarUnparsed);

        Cyk cykParser = new Cyk();
        cykParser.setGrammar(grammar);

        readPhrase(cykParser, phraseFile);

        Earley earleyParser = new Earley();
        earleyParser.setGrammar(grammar);

        readPhrase(earleyParser, phraseFile);
    }

    private void readPhrase(Parser parser, String phrase) {
        scan(parser, phrase);
    }

    private void readPhrase(Parser parser, File phraseFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(phraseFile));
        String phraseUnparsed = reader.readLine();
        while (phraseUnparsed != null) {
            scan(parser, phraseUnparsed);
            phraseUnparsed = reader.readLine();
        }
    }

    private void scan(Parser parser, String phraseUnparsed) {
        if (phraseUnparsed != null) {
            String[] phrase = validator.preparePhrase(phraseUnparsed);

            long start = System.nanoTime();
            boolean belongs = parser.belongsToLanguage(phrase);
            long end = System.nanoTime();

            double took = (end - start) / 1e9;

            System.out.println(parser.getName());
            output(belongs, phraseUnparsed, took);
        }
    }

    public void startUserInputReader(BufferedReader user) throws IOException {
        Grammar grammar = new Grammar();
        Cyk cykParser = new Cyk();
        Earley earleyParser = new Earley();

        while (true) {
            System.out.println("Give a rule, in the format \nparent -> child:\nor\nparent -> child1 | child2\nStop by pressing Enter. ");
            String s = user.readLine();
            if (s.isEmpty()) {
                break;
            }
            if (!checkIfQuitOrHelp(s, user)) {
                validator.prepareRule(s, grammar);
            } else {
                return; //kinda annoying since now the grammar cannot contain the word help :D
            }
        }

        if (!validator.validateChomsky(grammar)) {
            System.out.println("the grammar is not in Chomsky normal form\n");
        }
        cykParser.setGrammar(grammar);
        earleyParser.setGrammar(grammar);

        System.out.println("Give a phrase:");
        while (true) {
            String phrase = user.readLine();
            if (!checkIfQuitOrHelp(phrase, user)) {
                readPhrase(cykParser, phrase);
                readPhrase(earleyParser, phrase);
            } else {
                return;
            }
        }
    }

    private void output(boolean belongs, String phrase, Double took) {
        if (belongs) {
            System.out.println("The phrase \"" + phrase + "\" belongs to the language");
        } else {
            System.out.println("The phrase \"" + phrase + "\" does not belong to the language");
        }
        System.out.println("Took " + took + " s\n");
    }

    private boolean checkIfQuitOrHelp(String command, BufferedReader user) throws IOException {
        if (command.equalsIgnoreCase("help")) {
            System.out.println(instructions);
        } else if (command.equalsIgnoreCase("quit")) {
            while (true) {
                System.out.println("Do you want to exit ? (y/n)");
                String s = user.readLine();
                if (s.equalsIgnoreCase("y")) {
                    System.exit(0);
                } else if (s.equalsIgnoreCase("n")) {
                    return false; //user input quit but didn't mean to quit
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
