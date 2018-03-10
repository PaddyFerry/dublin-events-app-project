                                                                                      
<?php
        //Creating a connection
    $con = mysqli_connect("159.65.84.145","customer","customer","app");
    mysqli_set_charset($con,"utf8");
    if (mysqli_connect_errno())
    {
       echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

        $lat = $_GET["lat"];
        $long = $_GET["long"];
        $where = $_GET["where"];
        $category = $_GET["category"];

        $sql = "SELECT * FROM venuesTest where category $where '$category' ORDER by SQRT(($lat - lat)*($lat - lat) + ($long - lon)*($long - lon))";
        $result = mysqli_query($con ,$sql);

        while ($row = mysqli_fetch_assoc($result)) {

                $array[] = $row;
        }
        header('Content-Type:Application/json');
        echo json_encode($array);

    mysqli_free_result($result);
    mysqli_close($con);

 ?>
