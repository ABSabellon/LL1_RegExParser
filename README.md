## CMPILER- Regular Expression Recognizer

### Aileen Sabellon
### CMPILER S11

####Follows the LL(1) Parser Grammar:

``` Java
  start -> term | EPSILON | LP start | oper
  term ->  ALPHANUM oper
  oper -> OPERATIONS prod | withUnion | ε
  withUnion -> UNION factor
  factor -> start | EPSILON
  prod-> start | withUnion | ε
  
  Lexers:
  
  ALPHANUM -> [a-z0-9]
  EPSILON -> 'E'
  OPERATIONS -> '?'| '*'| '+'
  UNION -> 'U'
  LP -> '('
  RP -> ')'  
```

####First Set
``` Java
    first(start) ->
    first(term) -> 
    first(oper) -> 
    first(withUnion) -> 
    first(factor) -> 
    first(prod) -> 
```

