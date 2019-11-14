### Weekly report 3

####TODO
- checkstyle
- enum parsers (for two parsers:D)
- package structure: src.main.java.cfg-parsing.language etc (the project name included)

#### What did I do? What's new in the program?
- I refactored the previous weeks code so that the rules, the grammar and the parser are now represented as classes.
- I wrote tests for the classes `Rule`, `Grammar` and `Cyk`.
    - For the sake of testing, all methods are public right now even though only the first deciding one `belongsToLanguage(Grammar grammar, String phrase)` needs to be public.
I also considered making a new class for the 'submethods', but right now it seemed a bit too much, since only the CYK-class would need them.
    - I used Mockito to inject objects to the tested one.
- I made a `Parser` interface which CYK now implements. The interface has right now only the method to decide whether a certain phrase belongs to a language.
Since I will implement another parser in the future, I thought an interface would give the program structure some clarity.
- The test coverage can be generated with `gradle jacocoTestReport`.

#### What did I learn?
- How to test with Mockito, on a basic level
- How to test overall, although I still think there's quite some for me to learn about testing.

#### What was left unclear or caused problems?
- When testing a public method which uses a lot of private methods doing almost all of the actual work, I didn't quite know how to test it and actually get an informative feedback on where exactly something went wrong.
    - My solution was to make the private methods public just to be able to test them, but I will change this later I think.
    - I especially wanted to test the private methods, since after refactoring, the program didn't work anymore, so I decided to try to debug it by writing tests.

#### What am I going to do next?
- Finally start implementing the Earley parser
- Start implementing own data structures (ArrayList, HashMap, HashSet)
- At some point validating the inputs in the user interface

#### Time spent
| mon | tue | wed | thu | fri | sat | sun | **Total**
|--- | --- | --- | --- | --- | --- | --- | ---
|    |  3h |     | 4h  |     |     |  3h |     