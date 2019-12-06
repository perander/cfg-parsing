### Weekly report 6

#### What did I do? What's new in the program?
- Start validating the inputs. I added a package `utils` with a class `Validator`, which is used by the ui.
    - The class prepares entered rules, the grammar and checks whether it is in the Chomsky normal form. (In the future, it could also transform grammars to that form)
- Tested the `Validator` class
- Changed the way how the rules should be entered, since the peer reviewers reported some confusion about it. Now it's clearer I would say, and also makes entering the rules a bit quicker since the children can be chained.
- 
    
#### What did I learn?
- More about the Chomsky normal form
- More about parsing strings
- More about git
- Also took a closer look into gradle and hopefully learnt something about it too

#### What was left unclear or caused problems?
- I had generated some methods for my data structures classes, and the `contains` method in the class `List` was in the end the reason for one bug. Because it was so deep, I took quite some time to find it.

#### What am I going to do next?
- Make it possible to read input from a file
- Proofread the `Validator`, since it started to work so late
- Document the undocumented things
- Improve the UI and make it user friendlier
- Improve the documents about implementation and using the program

#### Time spent

mon | tue | wed | thu | fri | sat | sun | **Total**
--- | --- | --- | --- | --- | --- | --- | ---
    |     | 5h  | 2h  | 6h  |     |     |  13h