# bank-app

## Build and run the program by executing the run.sh script

```
./run.sh
```


The bank system by default is initialized with two accounts - 'Kwamina' and 'Ernest' that both have initial balances of 50.0

## Available commmands

The following are available commands and the parameters expected.

### create-account username initialBalance

<strong> username: </strong> a String containing only letters <br />
<strong> initialBalance: </strong> a non-negative value greater than 0

#### throws UserExistsException if a user with username already exists in the bank
***
  
### withdraw username acctNum amount

<strong> username: </strong> a String containing only letters <br />
<strong> acctNum: </strong> a six digit integer
<strong> amount: </strong> a non-negative value greater than 0

#### throws NoSuchUserException, InsufficientFundsException, InvalidAmountException
***

### deposit username acctNum amount

<strong> username: </strong> a String containing only letters <br />
<strong> acctNum: </strong> a six digit integer
<strong> amount: </strong> a non-negative value greater than 0

#### throws NoSuchUserException, InvalidAmountException
***

### transfer username1 acctNum1 username2 acctNum2 amount

<strong> username1: </strong> a String containing only letters <br />
<strong> acctNum1: </strong> a six digit integer
<strong> username2: </strong> a String containing only letters <br />
<strong> acctNum2: </strong> a six digit integer
<strong> amount: </strong> a non-negative value greater than 0

#### throws NoSuchUserException, InsufficientFundsException, InvalidAmountException
***

### display-users
***

### display-account username

<strong> username: </strong> a String containing only letters <br />

#### throws NoSuchUserException if a user with username does not exist in the bank

## Sample run

```
Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : help

-----------------------------------------------------------------------------
|	                 COMMANDS                                               |
-----------------------------------------------------------------------------
|	create-account <username> <initialAmount>                               |
|	withdraw <username> <acctNum> <amount>                                  |
|	deposit <username> <acctNum> <amount>                                   |
|	transfer <username1> <accountNum1> <username2> <accountNum2> <amount>   |
|	display-users                                                           |
|	display-account <username>                                              |
-----------------------------------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : display-users

-----------------------------------------------------
|	USERS                  |	ACCOUNTS            |
-----------------------------------------------------
|	Kwamina                |	Acct num: 399882	|
|	Ernest                 |	Acct num: 139219	|
-----------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : create-account Hodor 500

---------------------------------------------------------------
|	SUCCESS    |	Account for user Hodor created successfully |
---------------------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : display-account Hodor

-----------------------------------------------------
|	ACCOUNT NUMBER              |	BALANCE         |
-----------------------------------------------------
|	297119          			|	500.0           |
-----------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : display-users

-----------------------------------------------------
|	USERS                  |	ACCOUNTS            |
-----------------------------------------------------
|	Kwamina                |	Acct num: 399882	|
|	Hodor                  |	Acct num: 297119	|
|	Ernest                 |	Acct num: 139219	|
-----------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : deposit Kwamina 399882 250

------------------------------------------------------------
|	SUCCESS    |	250 has been added to Kwamina's account. |
------------------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : withdraw Ernest 139219 10

----------------------------------------------------------------
|	SUCCESS    |	10 has been withdrawn from Ernest's account. |
----------------------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : display-account Ernest

-----------------------------------------------------
|	ACCOUNT NUMBER              |	BALANCE         |
-----------------------------------------------------
|	139219          			|	40.0            |
-----------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : transfer Hodor 297119 Ernest 139219 70

------------------------------------------------------------------------
|	SUCCESS    |	70 was successfully transferred from Hodor to Ernest |
------------------------------------------------------------------------

Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : display-account Ernest

-----------------------------------------------------
|	ACCOUNT NUMBER              |	BALANCE         |
-----------------------------------------------------
|	139219          			|	110.0           |
-----------------------------------------------------

```

## Some error cases

Invalid command format

```
Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : withdraw ernest 10

------------------------------------------------------------------
|	ERROR    |	Expected: withdraw <username> <acctNum> <amount> |
------------------------------------------------------------------

```

No user with the name provided in the bank

```
Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : display-account Jon

-------------------------------------------------
|	ERROR    |	No user by the name Jon exists. |
-------------------------------------------------

```

Insufficient funds to perform an operation

```
Enter command [Type in 'DONE' to terminate the program. Type in 'HELP' for commands] : withdraw Hodor 297119 500

-----------------------------------------------------------------------------------
|	ERROR    |	Insufficient funds to withdraw amount: 500.0 from acctNum: 297119 |
-----------------------------------------------------------------------------------

```

## Future work

I abstracted the Persistence layer by creating a Database interface that is currently being implemented by a class that uses a HashMap to store accounts and user information. By doing this, I can create classes in the future that connect to other datastores (SQL, MongoDB, PostgreSQL) and replace my LocalDB instance with them to support true persistence.
