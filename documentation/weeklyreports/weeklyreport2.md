### Weekly report 2

#### What did I do?
- Implemented the CYK-algorithm based on a couple of pseudocode examples. This is a very basic and initial version, absolutely not efficient and will be optimised quite some in the future.
- Thought about the CYK-algorithm and intuitive ways to do it, since i found mistakes in some of the pseudocodes.
- Wrote a very minimal user interface, where the user can input a grammar and a sentence
- Wrote the second weekly report

#### What's new in the program?
- A very rough and unpolished version of the CYK-algorithm. It should work on the inputs i tried. It doesn't have any checking for correctly formatted grammar or other incorrect inputs.
- A very simple and non-fault-proof textual 'user interface'
    - only takes in grammar rules in a specific format: rules stated as S NP VP, for example
    - grammar represented as two HashMaps: parenttochildren and childrentoparent. This is because the information to both sides will be needed.

#### What did I learn?
- How the CYK-algorithm actually works

#### What was left unclear or caused problems?
- Problems were caused by pseudocodes with mistakes or otherwise confusing formatting
- I didn't yet write any new classes, I just wanted to try out implementing the algorithm first. Right now quite unorganised and documented only in the comments.

#### What am I going to do next?
- I might try to use some other kind of a data structure than a double HashMap to represent the grammar.
- Optimise the CYK-algorithm and it's efficiency
- Refactor and test, test coverage visible
- Try to implement the Earley parser

#### Time spent

| mon | tue | wed | thu | fri | sat | sun | **Total**
| --- | --- | --- | --- | --- | --- | --- | ---
|     |     | 4h  |     | 6h  |     |     |   10h
    
