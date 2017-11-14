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
} else {
	post();
}

function get()
{

	global $pdo;
	if (empty($_GET)) {

		$sql = 'SELECT * FROM pasta_noodles ORDER BY name';
		$stmt = $pdo->prepare($sql);
		$stmt->execute();
		$var = json_encode($stmt->fetchAll());
		if (isset($var)) {
			echo $var;
		} else {
			echo http_response_code(404);
		}

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
		if (isset($_GET['classification'])) {
			$classification = $_GET['classification'];
		}
		if (isset($_GET['delete'])) {
			$delete = $_GET['delete'];
		}
		if ($delete == 'true') { //delete == true required for delete

			$sql = 'DELETE FROM pasta_noodles WHERE id = ? AND id IN (SELECT id FROM pasta_noodles)';
			$stmt = $pdo->prepare($sql);
			$stmt->execute([$id, $name, $description, $uses]);
			echo http_response_code(200);

		} else {

			if (isset($classification)) {

				try {

					$sql = 'SELECT * FROM pasta_noodles WHERE classification = ?';
					$stmt = $pdo->prepare($sql);
					$stmt->execute([$classification]);
					$var = json_encode($stmt->fetchAll());
					if (isset($var)) {
						echo $var;
					} else {
						echo http_response_code(404);
					}

				} catch (PDOException $e) {
					echo http_response_code(400);
					die();
				}

			} else {

				try {

					$sql = 'SELECT * FROM pasta_noodles WHERE id = ? OR name = ? OR description = ? OR uses = ?';
					$stmt = $pdo->prepare($sql);
					$stmt->execute([$id, $name, $description, $uses]);
					$var = json_encode($stmt->fetchAll());
					if (isset($var)) {
						echo $var;
					} else {
						echo http_response_code(404);
					}

				} catch (PDOException $e) {
					echo http_response_code(400);
					die();
				}

			}

		}

	}

}

function post()
{

	global $pdo;
	if (empty($_REQUEST['options'])) {

		if (isset($_REQUEST['name'])) {
			$name = $_REQUEST['name'];
		}
		if (isset($_REQUEST['description'])) {
			$description = $_REQUEST['description'];
		}
		if (isset($_REQUEST['uses'])) {
			$uses = $_REQUEST['uses'];
		}
		if (isset($_REQUEST['classification'])) {
			$classification = $_REQUEST['classification'];
		}
		if (isset($_REQUEST['id'])) { //id required for edit

			$id = $_REQUEST['id'];
			$sql = 'UPDATE pasta_noodles SET name = ?, description = ?, uses = ?, classification = ? WHERE id = ?';
			$stmt = $pdo->prepare($sql);
			$stmt->execute([$name, $description, $uses, $classification, $id]);
			echo http_response_code(200);

		} else {

			$sql = 'INSERT INTO pasta_noodles (name, description, uses, classification) VALUES (?, ?, ?, ?)';
			$stmt = $pdo->prepare($sql);
			$stmt->execute([$name, $description, $uses, $classification]);
			echo http_response_code(200);

		}

	} else if ($_REQUEST['options'] == 'true') {

		echo http_response_code(200);
		echo "\nAllow: GET, POST\n";
		echo "See API documentation page for instructions on how to use this endpoint";

	} else {
		echo http_response_code(400);
	}

}