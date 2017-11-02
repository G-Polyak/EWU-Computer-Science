package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Arrays;

public class Main extends Application {

	private static Label mStatus;
	private static Integer bottomNum = 1234;

	@Override
	public void start(Stage primaryStage) throws Exception {

		SevenSegment[] segments = new SevenSegment[5];
		for (int i = 0; i < 5; i++) {
			segments[i] = new SevenSegment(i);
		}
		Button btn = new Button("Increment");
		btn.setAlignment(Pos.CENTER);
		btn.setOnAction(event -> increment(segments));
		StackPane mid = new StackPane(btn);
		StackPane.setAlignment(btn, Pos.CENTER);
		VBox v = new VBox(segments[0]);
		HBox h = new HBox(segments[1], segments[2], segments[3], segments[4]);
		GridPane holder = new GridPane();
		holder.setAlignment(Pos.CENTER);
		holder.add(v, 0, 0);
		holder.add(mid, 0, 1);
		holder.add(h, 0, 2);
		BorderPane root = new BorderPane();
		root.setCenter(holder);
		// add the menus
		root.setTop(buildMenus());
		mStatus = new Label("Everything is Copacetic");
		ToolBar toolBar = new ToolBar(mStatus);
		root.setBottom(toolBar);
		Scene scene = new Scene(root);
		primaryStage.setTitle("Seven Segment");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void increment(SevenSegment[] segments) {

		int inc = segments[0].getDisplayVal();
		segments[0].setDisplayVal(inc + 1);
		segments[0].draw();
		if (segments[0].isDim()) {
			bottomNum++;
			incrementBottom(segments);
		}

	}

	private void incrementBottom(SevenSegment[] segments) {

		String s = bottomNum.toString();
		int[] a = (Arrays.stream(s.split("")).mapToInt(Integer::parseInt)).toArray();
		segments[1].setDisplayVal(a[0]);
		segments[2].setDisplayVal(a[1]);
		segments[3].setDisplayVal(a[2]);
		segments[4].setDisplayVal(a[3]);
		segments[1].draw();
		segments[2].draw();
		segments[3].draw();
		segments[4].draw();

	}

	private static MenuBar buildMenus() {

		// build a menu bar
		MenuBar menuBar = new MenuBar();
		// File menu with just a quit item for now
		Menu fileMenu = new Menu("_File");
		MenuItem quitMenuItem = new MenuItem("_Quit");
		quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q,
				KeyCombination.CONTROL_DOWN));
		quitMenuItem.setOnAction(actionEvent -> Platform.exit());
		fileMenu.getItems().add(quitMenuItem);
		// Help menu with just an about item for now
		Menu helpMenu = new Menu("_Help");
		MenuItem aboutMenuItem = new MenuItem("_About");
		aboutMenuItem.setOnAction(actionEvent -> onAbout());
		helpMenu.getItems().add(aboutMenuItem);
		menuBar.getMenus().addAll(fileMenu, helpMenu);
		return menuBar;

	}

	private static void setStatus(String s) {
		mStatus.setText(s);
	}

	private static void onAbout() {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("George Polyak, CSCD 370 Lab 3, Fall 2017");
		alert.showAndWait();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
