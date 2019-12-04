## Implementation

### Project structure

![structure diagram](../documentation/img/structure.png "structure")

- The project has now 5 packages belonging to the program itself
    - `basicdatastructures`
        - List and MultiMap
    - `language`
        - Represents the rules and the grammar, uses the basic data structures
    - `parser`
        - The two parser algorithms, use the elements from the packages language
    - `ui`
        - Receives the inputs, calls validator and parsers
    - `validator`   
        - Validates the inputs, prepares the data structures for ui
    
- Additionally there is a package `efficiencytesting` containing classes testing the efficiency of the parsers.
I decided to test the parsers in separate runnable classes so that state of the processor or the JVM would have less an effect on the result.


### Time and space complexities, comparison


### Possible flaws and improvements
- The program could be extended to also produce the parse trees for the phrase. There could be many of them, and at least the CYK can be modified easily to produce multiple parse trees.

### Sources
- https://materiaalit.github.io/intro-to-ai/part5/, part 2.3.
- https://en.wikipedia.org/wiki/CYK_algorithm
- https://en.wikipedia.org/wiki/Earley_parser

