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

        public function createUser($fullname, $email, $password){
            if($this->isUserExist($fullname, $email)){
                return 0;
            }
            else{
                $password = md5($password);
                $stmt = $this->con->prepare("INSERT INTO users(fullname,email,password) VALUES(?,?,?);");
                $stmt->bind_param("sss", $fullname, $email, $password);
                if($stmt->execute()){
                    return 1;
                }
                else{
                    return 2;
                }
            }
        }
        private function isUserExist($fullname,$email){
            $stmt = $this->con->prepare("SELECT id FROM users WHERE fullname=? AND email=?;");
            $stmt->bind_param('ss', $fullname, $email);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }


        public function userLogin($email, $password){
            $password = md5($password);
            $stmt = $this->con->prepare("SELECT id FROM users WHERE email=? AND password=?;");
            $stmt->bind_param('ss', $email, $password);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

        public function getUserDetails($email){
            $stmt = $this->con->prepare("SELECT * FROM users WHERE email=?;");
            $stmt->bind_param('s', $email);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();    
        }
    }