<?php
require_once '../includes/DbOperations.php';
    $response = array();

    if($_SERVER['REQUEST_METHOD'] == 'POST'){

        if(isset($_POST['fullname']) and isset($_POST['email']) and isset($_POST['password'])){

            $db = new DbOperations();

            if($db->createUser($_POST['fullname'],$_POST['email'],$_POST['password'])){
                $response['error'] = false;
                $response['message'] = 'User Registered Successfully';
            }
            else{
                $response['error'] = true;
                $response['message'] = 'User Registeration Failed';
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