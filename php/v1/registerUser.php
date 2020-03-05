<?php
require_once '../includes/DbOperations.php';
    $response = array();

    if($_SERVER['REQUEST_METHOD'] == 'POST'){

        if(isset($_POST['fullname']) and isset($_POST['email']) and isset($_POST['password'])){

            $db = new DbOperations();
            $result = $db->createUser($_POST['fullname'],$_POST['email'],$_POST['password']);
            if($result == 1){
                $response['error'] = false;
                $response['message'] = 'User Registered Successfully';
            }
            elseif($result == 2){
                $response['error'] = true;
                $response['message'] = 'User Registeration Failed';
            }
            elseif($result == 0){
                $response['error'] = true;
                $response['message'] = 'User Already Exists';
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Required feilds are missing';
        }
    }
    else{
        $response['error'] = true;
        $response['message'] = "Invalid Request";
    }

    echo json_encode($response);