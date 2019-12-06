## Requirements and goals

#### What is the problem at hand?

- To compare two parsing algorithms, CYK and Earley, for deciding whether a word belongs to a language given by a context-free grammar
  - Implementing the algorithms
  - Comparing their time and space complexities
  - Experimenting with different kinds of inputs and input sizes
    - What kind of things have an effect on the performance?
  - Parsing text files
  - If there's enough time / idea for further development:
    - To accept a grammar in any form as an input and to transform it to the Chomsky normal form if needed

#### Data structures and algorithms used in the project, and reasons why

- CYK-algorithm
  - I recently learnt this on a conceptual level and would like to try implementing it, too
- Earley parser
  - Simulates non-deterministic finite automaton
- (Hash)map
  - To store the grammar (a plan)
- Set
- Array(List)
- Linked list

#### How are the inputs and how are they used?

- A context-free grammar
  - In Chomsky normal form (either required or transforming any other form)
    - Need to verify the correct form
  - Strings saved in a hashmap (probably)
  - language.Grammar given to the parsers first, then parsing the input text
- Text to be parsed
  - As simple strings
  - As a text file
    - Goal to use different inputs to compare the parsers
  
#### Goals for time and space complexities

- CYK
  - Time: O(mn<sup>3</sup>)
  - Space: O(n<sup>2</sup>)
- Earley recogniser
  - Time: O(m<sup>2</sup>n<sup>3</sup>)
  - Space: O(n)
- m: number of rules in the grammar
- n: number of symbols per string / words per sentence 

#### Sources

- Context-free grammars & other generalities
  - https://en.wikipedia.org/wiki/Context-free_grammar#Parsing
  - https://medium.com/100-days-of-algorithms/day-93-first-follow-cfe283998e3e
  - http://staff.icar.cnr.it/ruffolo/progetti/projects/10.Parsing%20Earley/A%20Comparison%20of%20CYK%20and%20Earley%20Parsing%20Algorithms-cykeReport.pdf
- CYK
  - https://materiaalit.github.io/intro-to-ai/part5/
- Earley
  - https://medium.com/100-days-of-algorithms/day-94-earley-parser-3fffdb33edc7
  - https://pdfs.semanticscholar.org/7f94/0d7e2b75480496d186eeaa58ab2f6f07c810.pdf

