package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {

	private static Label mStatus;
	private static Canvas mTempCanvas = new Canvas(400, 400);
	private static Canvas mPermCanvas = new Canvas(400, 400);
	private static Point2D mFrom, mTo;
	private static Color mColor = Color.RED;
	private static int mWidth = 1;
	private static String mColorString = "red";
	private static ToggleGroup widthGroup = new ToggleGroup();
	private static ToggleGroup colorGroup = new ToggleGroup();
	private static VBox mVBox = new VBox(buildMenus());
	private static String mToolbarPos = "left";
	private static ToolBar mToolbar = new ToolBar();
	private static BorderPane mRoot = new BorderPane();
	private static boolean mNeedSave = false;
	private static File mThisFile = null;
	private static Stage mStage;
	private static ArrayList<Line> mLines = new ArrayList<Line>();

	@Override
	public void start(Stage primaryStage) throws Exception {

		mStage = primaryStage;
		StackPane stackPane = new StackPane(mTempCanvas, mPermCanvas);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(stackPane);
		makeCanvasWhite();
		mRoot.setCenter(scrollPane);
		mPermCanvas.setOnMousePressed(this::onMousePressed);
		mPermCanvas.setOnMouseDragged(this::onMouseDragged);
		mPermCanvas.setOnMouseReleased(this::onMouseReleased);
		mStage.setOnCloseRequest(actionEvent -> {actionEvent.consume(); onExit();});
		// add the menus
		mRoot.setTop(mVBox);
		mRoot.setLeft(buildToolbar());
		mStatus = new Label("Everything is Copacetic");
		ToolBar statusBar = new ToolBar(mStatus);
		mRoot.setBottom(statusBar);
		Scene scene = new Scene(mRoot, 500, 500);
		mStage.setTitle("(untitled)");
		mStage.setScene(scene);
		mStage.show();

	}

	private void onMouseReleased(MouseEvent mouseEvent) {

		setStatus("Mouse has been released");
		mTo = new Point2D(mouseEvent.getX(), mouseEvent.getY());
		mTempCanvas.getGraphicsContext2D().clearRect(0, 0, 400, 400);
		makeCanvasWhite();
		if (mTempCanvas.contains(mTo)) {

			Line aLine = new Line(mFrom.getX(), mFrom.getY(), mTo.getX(), mTo.getY(), mWidth, mColor);
			aLine.draw(mPermCanvas);
			mLines.add(aLine);
			mPermCanvas.getGraphicsContext2D().setStroke(Color.BLACK);

		}
		mNeedSave = true;
		if (!mStage.getTitle().contains("*")) {
			mStage.setTitle(mStage.getTitle() + "*");
		}

	}

	private void onMouseDragged(MouseEvent mouseEvent) {

		if (mTempCanvas.contains(mouseEvent.getX(), mouseEvent.getY())) {

			mTempCanvas.getGraphicsContext2D().clearRect(0, 0, 400, 400);
			makeCanvasWhite();
			setStatus("Mouse has been dragged");
			mTo = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			drawLine();

		}

	}

	private void onMousePressed(MouseEvent mouseEvent) {

		setStatus("Mouse has been pressed");
		mFrom = new Point2D(mouseEvent.getX(), mouseEvent.getY());

	}

	private void drawLine() {

		mTempCanvas.getGraphicsContext2D().strokeLine(mFrom.getX(), mFrom.getY(), mTo.getX(), mTo.getY());

	}

	private static void makeCanvasWhite() {

		mTempCanvas.getGraphicsContext2D().setFill(Color.WHITE);
		mTempCanvas.getGraphicsContext2D().fillRect(0, 0, mTempCanvas.getWidth(), mTempCanvas.getHeight());

	}

	private static MenuBar buildMenus() {

		// build a menu bar
		MenuBar menuBar = new MenuBar();
		// File menu with just a exit item for now
		Menu fileMenu = new Menu("_File");
		MenuItem newMenuItem = new MenuItem("_New");
		newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N,
				KeyCombination.CONTROL_DOWN));
		newMenuItem.setOnAction(actionEvent -> onNew());
		MenuItem openMenuItem = new MenuItem("_Open");
		openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O,
				KeyCombination.CONTROL_DOWN));
		openMenuItem.setOnAction(actionEvent -> onOpen());
		MenuItem saveMenuItem = new MenuItem("_Save");
		saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S,
				KeyCombination.CONTROL_DOWN));
		saveMenuItem.setOnAction(actionEvent -> onSave(false));
		MenuItem saveAsMenuItem = new MenuItem("Save _As");
		saveAsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.A,
				KeyCombination.CONTROL_DOWN));
		saveAsMenuItem.setOnAction(actionEvent -> onSave(true));
		SeparatorMenuItem dashes = new SeparatorMenuItem();
		MenuItem exitMenuItem = new MenuItem("E_xit");
		exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X,
				KeyCombination.CONTROL_DOWN));
		exitMenuItem.setOnAction(actionEvent -> onExit());
		fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, dashes, exitMenuItem);

		Menu widthMenu = new Menu("_Width");
		RadioMenuItem onePixel = new RadioMenuItem("_1 Pixel");
		onePixel.setOnAction(actionEvent -> onWidth(1));
		onePixel.setToggleGroup(widthGroup);
		onePixel.setSelected(true);
		RadioMenuItem fourPixel = new RadioMenuItem("_4 Pixels");
		fourPixel.setOnAction(actionEvent -> onWidth(4));
		fourPixel.setToggleGroup(widthGroup);
		RadioMenuItem eightPixel = new RadioMenuItem("_8 Pixels");
		eightPixel.setOnAction(actionEvent -> onWidth(8));
		eightPixel.setToggleGroup(widthGroup);
		widthMenu.getItems().addAll(onePixel, fourPixel, eightPixel);
		widthMenu.setOnShowing(event -> onWidthShowing());

		Menu colorMenu = new Menu("_Color");
		RadioMenuItem red = new RadioMenuItem("_Red");
		red.setOnAction(actionEvent -> onColor("red"));
		red.setToggleGroup(colorGroup);
		red.setSelected(true);
		RadioMenuItem green = new RadioMenuItem("_Green");
		green.setOnAction(actionEvent -> onColor("green"));
		green.setToggleGroup(colorGroup);
		RadioMenuItem blue = new RadioMenuItem("B_lue");
		blue.setOnAction(actionEvent -> onColor("blue"));
		blue.setToggleGroup(colorGroup);
		RadioMenuItem black = new RadioMenuItem("_Black");
		black.setOnAction(actionEvent -> onColor("black"));
		black.setToggleGroup(colorGroup);
		colorMenu.getItems().addAll(red, green, blue, black);
		colorMenu.setOnShowing(event -> onColorShowing());
		// Help menu with just an about item for now
		Menu helpMenu = new Menu("_Help");
		MenuItem aboutMenuItem = new MenuItem("_About");
		aboutMenuItem.setOnAction(actionEvent -> onAbout());
		helpMenu.getItems().add(aboutMenuItem);
		menuBar.getMenus().addAll(fileMenu, widthMenu, colorMenu, helpMenu);
		return menuBar;

	}

	private static void onExit() {

		if (mStage.getTitle().contains("*")) {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			showConfirmation(alert);
			if (alert.getResult() == ButtonType.OK) {
				Platform.exit();
			}

		} else {
			Platform.exit();
		}

	}

	private static void onWidthShowing() {

		ObservableList<Toggle> list = widthGroup.getToggles();
		switch (mWidth) {

			case 1:
				widthGroup.selectToggle(list.get(0));
				break;
			case 4:
				widthGroup.selectToggle(list.get(1));
				break;
			case 8:
				widthGroup.selectToggle(list.get(2));
				break;

		}

	}

	private static void onColorShowing() {

		ObservableList<Toggle> list = colorGroup.getToggles();
		switch (mColorString) {

			case "red":
				colorGroup.selectToggle(list.get(0));
				break;
			case "green":
				colorGroup.selectToggle(list.get(1));
				break;
			case "blue":
				colorGroup.selectToggle(list.get(2));
				break;
			case "black":
				colorGroup.selectToggle(list.get(3));
				break;

		}

	}

	private static ToolBar buildToolbar() {

		mToolbar.setOrientation(Orientation.VERTICAL);
		Button[] buttons = new Button[6];
		for (int i = 0; i < 6; i++) {

			buttons[i] = new Button();
			buttons[i].setGraphic(new ImageView(new Image("ButtonImages/" + i + ".png")));
			mToolbar.getItems().add(buttons[i]);

		}
		buttons[0].setOnAction(actionEvent -> onNew());
		buttons[1].setOnAction(actionEvent -> onOpen());
		buttons[2].setOnAction(actionEvent -> onSave(false));
		buttons[3].setOnAction(actionEvent -> onWidth());
		buttons[4].setOnAction(actionEvent -> onColor());
		buttons[5].setOnAction(actionEvent -> onToolbarMove());

		buttons[0].setTooltip(new Tooltip("Create new Canvas"));
		buttons[1].setTooltip(new Tooltip("Open file"));
		buttons[2].setTooltip(new Tooltip("Save file"));
		buttons[3].setTooltip(new Tooltip("Change line width"));
		buttons[4].setTooltip(new Tooltip("Change line color"));
		buttons[5].setTooltip(new Tooltip("Move the toolbar"));

		mToolbar.getItems().add(3, new Separator(Orientation.HORIZONTAL));
		mToolbar.getItems().add(6, new Separator(Orientation.HORIZONTAL));
		return mToolbar;

	}

	private static void onToolbarMove() {

		ToolBar temp = mToolbar;
		switch (mToolbarPos) {

			case "left":
				mRoot.setLeft(null);
				mVBox.getChildren().add(temp);
				temp.setOrientation(Orientation.HORIZONTAL);
				mToolbarPos = "top";
				break;

			case "top":
				mVBox.getChildren().removeAll();
				VBox rightBox = new VBox(temp);
				mRoot.setRight(rightBox);
				temp.setOrientation(Orientation.VERTICAL);
				mToolbarPos = "right";
				break;

			case "right":
				mRoot.setRight(null);
				mRoot.setLeft(temp);
				mToolbarPos = "left";
				break;

		}

	}

	private static void onSave(boolean forcePrompt) {

		File selectedFile = mThisFile;
		if (mThisFile == null || forcePrompt) {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Line File");
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Line Files", "*.line"),
					new FileChooser.ExtensionFilter("All Files", "*.*"));
			if (mThisFile != null)
				fileChooser.setInitialFileName(mThisFile.getName());
			selectedFile = fileChooser.showSaveDialog(mStage);

		}
		if (selectedFile != null) {

			try {
				// TODO: open a stream, write the stuff, close the stream
				ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(selectedFile));
				stream.writeObject(mLines);
				stream.close();

			} catch (Exception ex) {
				// TODO: report the error somehow
				ex.printStackTrace();
			}
			mThisFile = selectedFile;
			mStage.setTitle(mThisFile.getName());
			mNeedSave = false;

		}

	}

	private static void openIO() {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open a Line File");
		chooser.setInitialDirectory(new File("."));
		chooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Line Files", "*.line"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
		File selectedFile = chooser.showOpenDialog(mStage);
		if (selectedFile == null) return;
		try {
			// TODO: open a stream, read the stuff, close the stream

			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(selectedFile));
			mLines = (ArrayList<Line>) stream.readObject();
			stream.close();
			for (Line line : mLines) {
				line.draw(mPermCanvas);
			}
			mNeedSave = false;

		} catch (IOException | ClassNotFoundException ex) {
			// TODO: report the problem somehow
			ex.printStackTrace();
		}
		mThisFile = selectedFile;
		mStage.setTitle(mThisFile.getName());
		// TODO: deal with the stuff read

	}

	private static void onOpen() {

		// TODO: if the work needs saving, call onSave() first
		if (mNeedSave) {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			showConfirmation(alert);
			if (alert.getResult() == ButtonType.OK) {

				clear();
				openIO();

			}

		} else {

			clear();
			openIO();

		}

	}

	private static void showConfirmation(Alert alert) {

		alert.setTitle("Current Work unsaved");
		alert.setHeaderText("Work hasn't been saved, do you want to discard it?");
		alert.showAndWait();

	}

	private static void clear() {

		mPermCanvas.getGraphicsContext2D().clearRect(0, 0, 400, 400);
		mNeedSave = false;
		mThisFile = null;
		mLines.clear();

	}

	private static void onNew() {

		if (mNeedSave) {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			showConfirmation(alert);
			if (alert.getResult() == ButtonType.OK) {

				clear();
				mStage.setTitle("(untitled)");

			}

		} else {

			clear();
			mStage.setTitle("(untitled)");

		}

	}

	private static void onColor(String clr) {

		switch (clr) {

			case "black":
				mColor = Color.BLACK;
				break;
			case "red":
				mColor = Color.RED;
				break;
			case "green":
				mColor = Color.GREEN;
				break;
			case "blue":
				mColor = Color.BLUE;
				break;
			default:
				mColor = Color.RED;

		}
		mColorString = clr;

	}

	private static void onColor() {

		switch (mColorString) {

			case "red":
				onColor("green");
				break;
			case "green":
				onColor("blue");
				break;
			case "blue":
				onColor("black");
				break;
			case "black":
				onColor("red");
				break;

		}

	}

	private static void onWidth(int i) {

		mTempCanvas.getGraphicsContext2D().setLineWidth((double) i);
		mPermCanvas.getGraphicsContext2D().setLineWidth((double) i);
		mWidth = i;

	}

	private static void onWidth() {

		switch (mWidth) {

			case 1:
				onWidth(4);
				break;
			case 4:
				onWidth(8);
				break;
			case 8:
				onWidth(1);
				break;

		}

	}

	private static void setStatus(String s) {

		mStatus.setText(s);

	}

	private static void onAbout() {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("George Polyak, CSCD 370 Lab 5, Fall 2017");
		alert.showAndWait();

	}

	public static void main(String[] args) {
		launch(args);
	}

}