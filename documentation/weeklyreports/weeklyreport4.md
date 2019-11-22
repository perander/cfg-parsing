### Weekly report 4

#### What did I do? What's new in the program?
- Implemented Earley parser
- Tested Earley and made it work with some inputs (but not some! still under inspection)
- Created and tested class State for the Earley parser
- Extracted ui so that the main method only works as a starter
- Created an option to read input as a file

#### What did i not do?
- Implement a whole basic data structure myself. Barely started with the most simple one.
- Completely finish the basic functionality of the program. Still need to find why Earley works with some grammars and not with some. I haven't yet found an example that breaks Cyk, potential test cases are welcome.
- Test List
- Write testing or other documents
- Start testing efficency of the algorithms

#### What did I learn?
- How the Earley parser works
- More on how a list works / could work
    - for example how the addition could be optimised in different ways (my first version is not optimised because of the lack of time)

#### What was left unclear or caused problems?
- Testing again, especially for the Earley parser. I ended up testing only the deciding 'main' method, in the class `ParserTest` and without mocks, which was way less cumbersome, more compact and easier to understand.
    - At this point i questioned the whole point of mocking in the context of this project

#### What am I going to do next?
- Start implementing the rest of the data structures

#### Time spent

mon | tue | wed | thu | fri | sat | sun | **Total**
--- | --- | --- | --- | --- | --- | --- | ---
 3h | 4h  |     | 3h  | 6h  |     | 1h  |   17h  
    
