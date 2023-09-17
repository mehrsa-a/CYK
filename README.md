# CYK
it's the implementation of CYK algorithm and checks if a string belongs to a context free grammer or not.

to run the program:

### input

in the first line of input, the string w is entered.

in the second line of input, the natural number n is entered.

- n: the number of grammar production rules

in the next n lines, a production rule is entered in each line in the form below:

$$S->AB|a|-$$

consider that:

- epsilon shows by `-`, so this charackter is not in the language alphabet.

- the input grammer is in the chomsky normal form.

- the initial symbol name is `S`.

### output

the `yes` shows that the string belongs to the given grammer and the `no` shows that its not.
