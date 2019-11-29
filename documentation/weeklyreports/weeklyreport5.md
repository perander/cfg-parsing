### Weekly report 5

#### What did I do? What's new in the program?
- Fixed Earley parser. It now passes the tests it previously didn't.
- Implemented and tested MultiMap and List
    - MultiMap fits the purposes of this project better than the previously used HashMap, because the map needs to contain several values per key.
    - The key and value sets are now represented as lists with each element only appearing once. It is not as efficient as a set would be, and i might still implement a set because of that. 
- Rewrote the tests, got rid of mock and as a side effect managed to fix the bug in Earley parser
- Added the plugins mentioned in the peer review
- Started testing performance
- Reviewed another students project

#### What did I learn?
- Got reminded on how HashMap, HashSet and a MultiMap work
 
#### What was left unclear or caused problems?
- the bug in Earley parser, but it doesn't seem to be a problem anymore

#### What am I going to do next?
- Test performance with larger inputs
- Optimise the algorithms and see how can i change their performance (they're still somehow unoptimised and non-efficient)
- Improve usability and validate inputs

#### Time spent

mon | tue | wed | thu | fri | sat | sun | **Total**
--- | --- | --- | --- | --- | --- | --- | ---
  | 4h  |     | 5h  | 9h |     |    |     18h
    
