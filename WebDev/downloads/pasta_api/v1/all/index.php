<?php

include_once '../creds.php';

$dsn = "mysql:host=$host;dbname=$db;charset=utf8mb4";
$opt = [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES => false,
];
$pdo = new PDO($dsn, $user, $pass, $opt);
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    get();
} else if($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    options();
} else {
	post();
}

function get()
{

    global $pdo;
    if (empty($_GET)) {

        $sql = 'SELECT * FROM pasta';
        $stmt = $pdo->prepare($sql);
        $stmt->execute();
        echo json_encode($stmt->fetchAll());

    } else {

	    if (isset($_GET['id'])) {
		    $id = $_GET['id'];
	    }
	    if (isset($_GET['name'])) {
		    $name = $_GET['name'];
	    }
	    if (isset($_GET['description'])) {
		    $description = $_GET['description'];
	    }
	    if (isset($_GET['uses'])) {
		    $uses = $_GET['uses'];
	    }
	    if (isset($_GET['delete'])) {
	    	$delete = $_GET['delete'];
	    }
	    if($delete == 'true') { //delete == true required for delete

		    $sql = 'DELETE FROM pasta WHERE id = ? OR name = ? OR description = ? OR uses = ?';
		    $stmt = $pdo->prepare($sql);
		    $stmt->execute([$id, $name, $description, $uses]);
		    echo http_response_code(200);

	    } else {

		    $sql = 'SELECT * FROM pasta WHERE id = ? OR name = ? OR description = ? OR uses = ?';
		    $stmt = $pdo->prepare($sql);
		    $stmt->execute([$id, $name, $description, $uses]);
		    echo json_encode($stmt->fetchAll());

	    }

    }

}

function post()
{

    global $pdo;
    $name = $_REQUEST['name'];
    $description = $_REQUEST['description'];
    $uses = $_REQUEST['uses'];
    if (!empty($_REQUEST['id'])) { //id required for edit

        $id = $_REQUEST['id'];
        $sql = 'UPDATE pasta SET name = ?, description = ?, uses = ? WHERE id = ?';
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$name, $description, $uses, $id]);
        echo http_response_code(200);

    } else {

        $sql = 'INSERT INTO pasta (name, description, uses) VALUES (?, ?, ?)';
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$name, $description, $uses]);
        echo http_response_code(200);

    }

}

function options() {

	echo $http_response_header;

}