package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {

    private static Label mStatus;
    private static Canvas mTempCanvas = new Canvas(400, 400);
    private static Canvas mPermCanvas = new Canvas(400, 400);
    private static Point2D mFrom, mTo;
    private static Paint color = Color.RED;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        StackPane stackPane = new StackPane(mTempCanvas, mPermCanvas);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(stackPane);
        makeCanvasWhite();
        root.setCenter(scrollPane);
        mPermCanvas.setOnMousePressed(this::onMousePressed);
        mPermCanvas.setOnMouseDragged(this::onMouseDragged);
        mPermCanvas.setOnMouseReleased(this::onMouseReleased);
        // add the menus
        root.setTop(buildMenus());
        mStatus = new Label("Everything is Copacetic");
        ToolBar toolBar = new ToolBar(mStatus);
        root.setBottom(toolBar);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Draw Lines");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void onMouseReleased(MouseEvent mouseEvent) {

        setStatus("Mouse has been released");
        mTo = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        mTempCanvas.getGraphicsContext2D().clearRect(0,0,400,400);
        makeCanvasWhite();
        if (mTempCanvas.contains(mTo)) {

            mPermCanvas.getGraphicsContext2D().setStroke(color);
            mPermCanvas.getGraphicsContext2D().strokeLine(mFrom.getX(), mFrom.getY(), mTo.getX(), mTo.getY());
            mPermCanvas.getGraphicsContext2D().setStroke(Color.BLACK);

        }

    }

    private void onMouseDragged(MouseEvent mouseEvent) {

        if (mTempCanvas.contains(mouseEvent.getX(), mouseEvent.getY())) {

            mTempCanvas.getGraphicsContext2D().clearRect(0,0,400,400);
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
        openMenuItem.setOnAction(actionEvent -> onNotYet());
        MenuItem saveMenuItem = new MenuItem("_Save");
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S,
                KeyCombination.CONTROL_DOWN));
        saveMenuItem.setOnAction(actionEvent -> onNotYet());
        MenuItem saveAsMenuItem = new MenuItem("Save _As");
        saveAsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.A,
                KeyCombination.CONTROL_DOWN));
        saveAsMenuItem.setOnAction(actionEvent -> onNotYet());
        SeparatorMenuItem dashes = new SeparatorMenuItem();
        MenuItem exitMenuItem = new MenuItem("E_xit");
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X,
                KeyCombination.CONTROL_DOWN));
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, dashes, exitMenuItem);

        Menu widthMenu = new Menu("_Width");
        ToggleGroup widthGroup = new ToggleGroup();
        RadioMenuItem onePixel = new RadioMenuItem("_1 Pixel");
        onePixel.setOnAction(actionEvent -> onWidth(1));
        onePixel.setToggleGroup(widthGroup);
        RadioMenuItem fourPixel = new RadioMenuItem("_4 Pixels");
        fourPixel.setOnAction(actionEvent -> onWidth(4));
        fourPixel.setToggleGroup(widthGroup);
        RadioMenuItem eightPixel = new RadioMenuItem("_8 Pixels");
        eightPixel.setOnAction(actionEvent -> onWidth(8));
        eightPixel.setToggleGroup(widthGroup);
        widthMenu.getItems().addAll(onePixel, fourPixel, eightPixel);

        Menu colorMenu = new Menu("_Color");
        ToggleGroup colorGroup = new ToggleGroup();
        RadioMenuItem black = new RadioMenuItem("_Black");
        black.setOnAction(actionEvent -> onColor("black"));
        black.setToggleGroup(colorGroup);
        RadioMenuItem red = new RadioMenuItem("_Red");
        red.setOnAction(actionEvent -> onColor("red"));
        red.setToggleGroup(colorGroup);
        RadioMenuItem green = new RadioMenuItem("_Green");
        green.setOnAction(actionEvent -> onColor("green"));
        green.setToggleGroup(colorGroup);
        RadioMenuItem blue = new RadioMenuItem("B_lue");
        blue.setOnAction(actionEvent -> onColor("blue"));
        blue.setToggleGroup(colorGroup);
        colorMenu.getItems().addAll(black, red, green, blue);
        // Help menu with just an about item for now
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, widthMenu, colorMenu, helpMenu);
        return menuBar;

    }

    private static void onNotYet() {
        setStatus("Feature not yet available");
    }

    private static void onNew() {

        mPermCanvas.getGraphicsContext2D().clearRect(0,0,400,400);

    }

    private static void onColor(String clr) {

        switch (clr) {

            case "black":
                color = Color.BLACK;
                break;
            case "red":
                color = Color.RED;
                break;
            case "green":
                color = Color.GREEN;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            default: color = Color.RED;

        }

    }

    private static void onWidth(int i) {

        mTempCanvas.getGraphicsContext2D().setLineWidth((double) i);
        mPermCanvas.getGraphicsContext2D().setLineWidth((double) i);

    }

    private static void setStatus(String s) {

        mStatus.setText(s);

    }

    private static void onAbout() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("George Polyak, CSCD 370 Lab 2, Fall 2017");
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
