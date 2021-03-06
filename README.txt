CMPILER- Regular Expression Recognizer

Aileen Sabellon
CMPILER S11

IDE:

    - IDE used: INTELLIJ

How to run the program:

Running GUI:

	1. Compile and Run the MainWithGUI.java in the src folder.
	
	2. Getting Test Cases Input:
		-The left side of the Interface is available to write the test cases.
		
		-Test cases can also be obtained by opening the file in the File Menu Bar, Directory is
		 automatically set to the txtFiles Folder.
		 
	3. Press Run to print the result in the right side of the Interface.
		- There are two tabs for the result the Parser and the Lexer
		
		- The Parser shows if the whole string is Rejected or Accepted by the LL(1) parser Grammar
			each Strings are separated by next line ('\n').
			
		- The Lexer shows the token type of each scanned characters.


Follows the LL(1) Parser Grammar:


  start -> chars
        | LP start RP oper
        | E
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


First Set


    first(start) -> { '(', [a-z0-9], 'E' }
    first(chars) -> { [a-z0-9] }
    first(oper) -> { [ '?'| '*'| '+' ],'U', ε }
    first(comb) -> { 'U' }
    first(factor) -> { '(', [a-z0-9], 'E'}
    first(more) -> { '(', [a-z0-9], 'U', 'E', ε  }


Follow Set


    follow(start) -> { ')', $ }
    follow(chars) -> { ')', $  }
    follow(oper) -> { ')', $  }
    follow(comb) -> { ')', $  }
    follow(factor) -> { ')', $  }
    follow(more) -> { ')', $  }

#### Look Ahead Table

|           |        [a-z0-9]        |   ['?', '*', '+']  |          'E'       |        'U'      |           '('        |        ')'      |         ε       |         $       |
| --------- |:----------------------:|:------------------:|:------------------:|:---------------:|:--------------------:|:---------------:|:---------------:| ---------------:|
| start     |    start -> chars      |                    |      start -> E    |                 | start -> LP start RP |                 |                 |                 |
| chars     | chars -> ALPHANUM OPER |                    |                    |                 |                      |                 |                 |                 |
| oper      |                        | oper -> OPERATIONS |                    |  oper -> UNION  |                      |                 |    oper -> ε    |                 |
| comb      |                        |                    |                    |  comb -> UNION  |                      |                 |                 |                 |
| factor    |    factor -> chars     |                    | factor -> EPSILON  |                 | factor-> LP start RP |                 |                 |                 |
| more      |     more -> chars      |                    |  more -> EPSILON   |   more -> comb  |  more-> LP start RP  |                 |    more -> ε    |                 |


















