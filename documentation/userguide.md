###User guide

####Inputs

The grammar is entered as rules. The rules are strings of 1 or more characters, not containing spaces. The rules can be chained or entered individually. The grammar should be in the Chomsky normal form (although my validator is not yet perfect, so there's a chance it might not complain about a certain kind of a wrong grammar. I hope to fix this soon.)

The rules should be entered like this:
- `:` separates rules from each other
- `->` separates the parent from the right-hand side rules (or the children)
- `|` separates the possibly chained right-hand side rules
- a space separates the individual elements of the right-hand side rules


#####An example grammar, entered row by row:
- `s -> np vp:`
- `vp -> v np | vp adv | v:`
- `np -> n:`
- `v -> fish:`
- `n -> fish | robots:`
- `adv -> today`

This grammar is also used in the tests, for example the `ValidatorTest`.
