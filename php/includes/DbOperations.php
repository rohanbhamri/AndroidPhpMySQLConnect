<?php

    class DbOperations{

        private $con;

        function __construct()
        {
            require_once dirname(__FILE__)."/DbConnect.php";
            $db = new DbConnect();
            $this->con = $db->connect();
        }
         
        /* insert data into the table */

        function createUser($fullname, $email, $password){
            $password = md5($password);
            $stmt = $this->con->prepare("INSERT INTO users(fullname,email,password) VALUES(?,?,?);");
            $stmt->bind_param("sss", $fullname, $email, $password);
            if($stmt->execute()){
                return true;
            }
            else{
                return false;
            }

        }
    }