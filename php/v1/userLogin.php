<?php
require_once '../includes/DbOperations.php';
    $response = array();

    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        if(isset($_POST['email']) and isset($_POST['password'])){
            $db = new DbOperations();
            if($db->userLogin($_POST['email'], $_POST['password'])){
                $userDetails = $db->getUserDetails($_POST['email']);
                $response['error'] = false;
                $response['message'] = 'Login Successful';
                $response['id'] = $userDetails['id'];
                $response['fullname'] = $userDetails['fullname'];
                $response['email'] = $userDetails['email'];
            }
            else{
                $response['error'] = true;
                $response['message'] = "Invalid ID or Password.";    
            }
        }
        else{        
            $response['error'] = true;
            $response['message'] = "Required values are missing...";
        }
    }
    else{
        $response['error'] = true;
        $response['message'] = "Invalid Request";
    }
    echo json_encode($response);