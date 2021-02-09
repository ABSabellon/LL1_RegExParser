## CMPILER- Regular Expression Recognizer

### Aileen Sabellon
### CMPILER S11

## USED:

    - IDE used: INTELLIJ
    - Library used: Java ver 15.0.2

## How to run the program:

#### Running GUI:

	1. Compile and Run the MainWithGUI.java in the src folder.
![GUI](https://github.com/ABSabellon/LL1_RegExParser/blob/master/etc/imgs/GUI.PNG)

    2. Getting Test Cases Input:
		- The left side of the Interface is available to write the test cases.
		
		- Test cases can also be obtained by opening the file in the File Menu Bar.

            - The Directory is automatically set to the txtFiles Folder.
![openFile](https://github.com/ABSabellon/LL1_RegExParser/blob/master/etc/imgs/openFile.PNG)

	3. Press Run to print the result in the right side of the Interface.
		- There are two tabs for the result the Parser and the Lexer
		
		- The Parser shows if the whole string is Rejected or Accepted by the LL(1) parser Grammar
			each Strings are separated by next line ('\n').
![parserTab](https://github.com/ABSabellon/LL1_RegExParser/blob/master/etc/imgs/parserTab.PNG)

             - The Lexer shows the token type of each scanned characters.

![lexerTab](https://github.com/ABSabellon/LL1_RegExParser/blob/master/etc/imgs/lexerTab.PNG)


#### Follows the LL(1) Parser Grammar:

``` Java

  regEx -> chars
        | LP regEx RP oper
        | eps 
  eps -> E | E comb
  chars ->  ALPHANUM oper
  oper -> OPERATIONS more
        | comb 
        | ε      
  comb -> UNION regEx
  more-> regEx
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

#### First Set

``` Java

first(regEx) -> { '(', [a-z0-9], 'E' }
first(eps) -> { 'E', 'U' }
first(chars) -> { [a-z0-9] }
first(oper) -> { [ '?'| '*'| '+' ],'U', ε }
first(comb) -> { 'U' }
first(more) -> { '(', [a-z0-9], 'U', 'E', ε  }

    
```

#### Follow Set

``` Java

follow(regEx) -> { ')', $ }
follow(chars) -> { ')', $  }
follow(oper) -> { ')', $  }
follow(comb) -> { ')', $  }
follow(factor) -> { ')', $  }
follow(more) -> { ')', $  }

    
```

#### Look Ahead Table

|           |        [a-z0-9]        |   ['?', '*', '+']  |          'E'       |        'U'      |           '('        |        ')'      |         ε       |         $       |
| --------- |:----------------------:|:------------------:|:------------------:|:---------------:|:--------------------:|:---------------:|:---------------:| ---------------:|
| start     |    start -> regEx $    |                    |   start -> regEx   |                 | start -> regEx $     |                 |                 |                 |
| regEx     |    regEx -> chars      |                    |     regEx -> E     |                 | regEx -> LP regEx RP |                 |                 |                 |
| eps       |                        |                    |   eps -> E comb    |                 |                      |                 |                 |                 |
| chars     | chars -> ALPHANUM OPER |                    |                    |                 |                      |                 |                 |                 |
| oper      |                        | oper -> OPERATIONS |                    |  oper -> comb   |                      |                 |    oper -> ε    |                 |
| comb      |                        |                    |                    |  comb -> UNION  |                      |                 |                 |                 |
| more      |     more -> regEx      |                    |  more -> regEx     |  more -> regEx  |     more-> regEx     |                 |    more -> ε    |                 |


















