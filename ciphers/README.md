# Ciphers

## What

We have three cipher and  three decipher functions. Each have a key that you pass in as a parameter to encode /decode. There is also an analysis function for the Caesar cipher that finds the key. The ciphers are
- [Caesar](http://practicalcryptography.com/ciphers/caesar-cipher/):  it shifts all letter by the number 'key' given as a parameter
- [Vigenere](http://practicalcryptography.com/ciphers/vigenere-gronsfeld-and-autokey-cipher/): it shifts all letter from message by the corresponding shift in the 'key'
- [Transposition](https://en.wikipedia.org/wiki/Transposition_cipher): performs the transition cipher on the message by reorganizing the letters and eventually adding characters

## What's happening?
The documentation provide in the `message.java` is already pretty detailed. Other than the afore mentioned 7 functions, we also have
a function to clean up the string: `makeValid` which modifies message to remove any character that is not a letter and turn Upper Case into Lower Case. 

Oh, and I should mention that Caesar Analysis is based on the [frequency of the letter 'e'](https://learncryptography.com/attack-vectors/frequency-analysis/), because it appears the most in most messages. 
So if we find the most frequent encoded character, we can figure out the key, and see if the message now makes sense. 
