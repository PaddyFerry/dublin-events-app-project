<?php
        //Creating a connection
    $con = mysqli_connect("159.65.84.145","customer","customer","app");
    mysqli_set_charset($con,"utf8");
    if (mysqli_connect_errno())
    {
       echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
        $order = $_GET["order"];

        $sql = "Select * from eventsTest ORDER BY $order";

        $result = mysqli_query($con ,$sql);

        while ($row = mysqli_fetch_assoc($result)) {

                $array[] = $row;
        }
        header('Content-Type:Application/json');

        echo json_encode($array);

    mysqli_free_result($result);

    mysqli_close($con);

 ?>

