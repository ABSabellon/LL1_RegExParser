## CMPILER- Regular Expression Recognizer

### Aileen Sabellon
### CMPILER S11

####Follows the LL(1) Parser Grammar:

``` Java
  start -> start 
        | LP start RP 
        | oper
  chars ->  ALPHANUM oper
  oper -> OPERATIONS more
        | comb 
        | ε
  comb -> UNION factor
  factor -> start 
        | EPSILON
  more-> start
        | comb
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
first(start) -> { }
first(chars) -> { }
first(oper) -> { }
first(comb) -> { }
first(factor) -> { }
first(more) -> { }

    
```
#### Follow Set

``` Java
follow(start) -> { }
follow(chars) -> { }
follow(oper) -> { }
follow(comb) -> { }
follow(factor) -> { }
follow(more) -> { }

    
```

#### Look Ahead Table

|           | [a-z0-9]      |['?', '*', '+'] |        'E'      |        'U'      |        '('      |        ')'      |         ε       |         $       |
| --------- |:-------------:|:--------------:|:---------------:|:---------------:|:---------------:|:---------------:|:---------------:| ---------------:|
| start     | start -> term |  start -> term | start -> EPSILON|  start -> oper  |        '('      |        ')'      |         ε       |         $       |

