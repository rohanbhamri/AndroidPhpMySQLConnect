# AndroidPhpMySQLConnect
This project shows how to connect php and andorid

BELOW ARE ALL THE STEPS FOR CREATING THE APP WITH PHP CONNECTIVITY

PHP Script : 
Step 1: create a folder named includes.

Step 2: create a new file in includes folder named constants.php

Step 3: in constants.php define data base requirements, like USERNAME, PASSWORD, DATABASE_NAME, & HOST.

Step 4: create a new file name DbConnect.php in the same directory.

Step 5: in Dbconnect.php create a class name DbConnect. This class will handle the connection to the database. For connecting to the database follow few steps.

          a. Open your database and create a new database. (you can name your database anything but don't forget to change the name of the database in your constants.php file.)
          
          b. After creating a database create a table named users (table name can be anything but also should be changed in DbOperations.php files.)
          
          c. Table should contain *id with primary key and autoincrement*, *username*, *email*, *password* after these feilds you can add more feilds to your database.

Step 6: Create another file in the same directory named DbOperations.php

Step 7: In DbOperations.php all the operations will happen like creating a user, geting users data, logging in the user etc. 

Step 8: Now Create a new folder in the root directory(Outside includes folder).

Step 9: In this folder create registerUser.php file.

Step 10: in registerUser.php file see following steps.

          a. First includes the files needed. 
        
          b. create an empty array name *response*
          
          c. check request methond if the method is post then countinue else in response show error.
          
          d. if method is post check the required values are not null if null then show error .
          
          e. if not null create new DbOperations and create user by sending the parameters to createUser function of DbOperations class.
          
          f. if createUser works show error equals false else show error.
          
          g. at last echo response in json format.
        
        
        
 
