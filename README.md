## CMPILER- Regular Expression Recognizer

### Aileen Sabellon
### CMPILER S11

####Follows the LL(1) Parser Grammar:

``` Java
  start -> term 
         | EPSILON 
         | LP start RP 
         | oper
  term ->  ALPHANUM oper
  oper -> OPERATIONS prod 
         | withUnion 
         | ε
  withUnion -> UNION factor
  factor -> start 
         | EPSILON
  prod-> start 
          | withUnion 
          | ε
  
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
first(start) -> { [a-z0-9], ['?', '*', '+'] , 'E', '(',  'U', ε }
first(term) -> { [a-z0-9] }
first(oper) -> { ['?', '*', '+'], 'U', ε }
first(withUnion) -> { 'U' }
first(factor) -> { [a-z0-9]. 'E', '(', ['?', '*', '+'], ε}
first(prod) -> { [a-z0-9]., 'E', '(', 'U', ε}

    
```
#### Follow Set

``` Java
follow(start) -> { ')', $}
follow(term) -> { ')', $ } 
follow(oper) -> { ')', $ } 
follow(withUnion) -> { ')', $ } 
follow(factor) -> { ')', $ } 
follow(prod) -> { ')', $ } 
    
```

#### Look Ahead Table

|           | [a-z0-9]      |['?', '*', '+'] |        'E'      |        'U'      |        '('      |        ')'      |         ε       |         $       |
| --------- |:-------------:|:--------------:|:---------------:|:---------------:|:---------------:|:---------------:|:---------------:| ---------------:|
| start     | start -> term |  start -> term | start -> EPSILON|  start -> oper  |

