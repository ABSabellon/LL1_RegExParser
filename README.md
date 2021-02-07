## CMPILER- Regular Expression Recognizer

### Aileen Sabellon
### CMPILER S11

####Follows the LL(1) Parser Grammar:

``` Java
  start -> chars
        | LP start RP oper
        | EPSILON
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
  OPERATIONS -> [ '?'| '*'| '+' ]
  UNION -> 'U'
  LP -> '('
  RP -> ')'  
```

####First Set

``` Java
first(start) -> { [a-z0-9], [ '?'| '*'| '+' ],'E', '(', ε }
first(chars) -> { [a-z0-9] }
first(oper) -> { [ '?'| '*'| '+' ],'U', ε }
first(comb) -> { 'U' }
first(factor) -> { [a-z0-9], [ '?'| '*'| '+' ], '(', ε, 'E' }
first(more) -> { [a-z0-9], [ '?'| '*'| '+' ],'U', 'E', '(', ε  }

    
```
#### Follow Set

``` Java
follow(start) -> { ')', $ }
follow(chars) -> { ')', $  }
follow(oper) -> { ')', $  }
follow(comb) -> { ')', $  }
follow(factor) -> { ')', $  }
follow(more) -> { ')', $  }

    
```

#### Look Ahead Table

|           | [a-z0-9]      |['?', '*', '+'] |        'E'      |        'U'      |        '('      |        ')'      |         ε       |         $       |
| --------- |:-------------:|:--------------:|:---------------:|:---------------:|:---------------:|:---------------:|:---------------:| ---------------:|
| start     | start -> chars|  start -> oper | start -> EPSILON|      ERROR      |start -> LP start RP|       ERROR     |  start -> oper  |         ε       |
| chars     | chars -> ALPHANUM OPER|  ERROR | ERROR|  ERROR   |      ERROR      |      ERROR      |       ERROR     |         ε       |
| oper      |     ERROR     |oper -> OPERATIONS|     ERROR     |  oper -> UNION  |      ERROR      |       ERROR     |     oper -> ε   |         ε       |
| comb      |     ERROR     |      ERROR     |       ERROR     |  comb -> UNION  |      ERROR      |       ERROR     |       ERROR     |         ε       |
| factor    |factor -> chars| factor -> oper |factor -> EPSILON|      ERROR      |factor-> LP start RP|    ERROR     |  factor -> oper |         ε       |
| more      | more -> chars |  more -> oper  | more -> EPSILON |   more -> oper  |more-> LP start RP|      ERROR     |   more -> oper  |         ε       |
 
