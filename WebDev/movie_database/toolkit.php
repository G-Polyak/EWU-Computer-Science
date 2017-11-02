<?php

include_once 'creds.php';

$dsn = "mysql:host=$host;dbname=$db;charset=utf8mb4";
$opt = [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES => false,
];
$pdo = new PDO($dsn, $user, $pass, $opt);
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    get();
} else {
    post();
}

function get()
{

    global $pdo;
    if (empty($_GET)) {

        $sql = 'SELECT * FROM movies';
        $stmt = $pdo->prepare($sql);
        $stmt->execute();
        echo json_encode($stmt->fetchAll());

    } else {

        $id = $_GET['id'];
        $sql = 'DELETE FROM movies WHERE id = ?';
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$id]);
        echo http_response_code(200);

    }

}

function post()
{

    global $pdo;
    $title = $_REQUEST['title'];
    $year = $_REQUEST['year'];
    $studio = $_REQUEST['studio'];
    $description = $_REQUEST['description'];
    $price = $_REQUEST['price'];
    if (!empty($_REQUEST['id'])) {

        $id = $_REQUEST['id'];
        $sql = 'UPDATE movies SET title = ?, year = ?, studio = ?, description = ?, price = ? WHERE id = ?';
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$title, $year, $studio, $description, $price, $id]);
        echo http_response_code(200);

    } else {

        $sql = 'INSERT INTO movies (title, year, studio, description, price) VALUES (?, ?, ?, ?, ?)';
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$title, $year, $studio, $description, $price]);
        echo http_response_code(200);

    }

}