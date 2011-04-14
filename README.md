# TKTL Clojure course 2011
[course page](http://wiki.helsinki.fi/display/lambda/Clojure-kurssi)


## Random things to keep in mind

Emacs SLIME

Evaluoi merkattu alue inferior lispissä
mark region, C-c C-r 

evaluoi edellinen ekspressio
C-c C-e

käynnistä inferior list (run-lisp)
C-c C-z

### Lein Swankin readmestä

* M-TAB: Autocomplete symbol at point
* C-x C-e: Eval the form under the point
* C-c C-k: Compile the current buffer
* M-.: Jump to the definition of a var
* C-c S-i: Inspect a value
* C-c C-m: Macroexpand the call under the point
* C-c C-d C-d: Look up documentation for a var
* C-c C-z: Switch from a Clojure buffer to the repl buffer
* C-c M-p: Switch the repl namespace to match the current buffer
* C-c C-w c: List all callers of a given function