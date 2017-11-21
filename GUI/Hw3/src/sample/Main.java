package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private static Label mStatus;
    private static BorderPane root = new BorderPane();
    private static ToggleGroup views = new ToggleGroup();

    @Override
    public void start(Stage primaryStage) throws Exception {

        CustomPanel top = new CustomPanel("(example)", 0.678, 0.847, 0.902);
        CustomPanel bottom = new CustomPanel("(example)", 0.565, 0.933, 0.565);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(top, bottom);
        root.setCenter(vBox);
        // add the menus
        root.setTop(buildMenus());
        mStatus = new Label("Everything is Copacetic");
        mStatus.textProperty().bind(top.statusProperty());
        mStatus.textProperty().bind(bottom.statusProperty());
        ToolBar toolBar = new ToolBar(mStatus);
        root.setBottom(toolBar);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Clipboard & CSS Tester");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("clipboard.png"));
        primaryStage.show();

    }

    private MenuBar buildMenus() {

        // build a menu bar
        MenuBar menuBar = new MenuBar();
        // File menu with just a quit item for now
        Menu fileMenu = new Menu("_File");
        MenuItem quitMenuItem = new MenuItem("_Quit");
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q,
                KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().add(quitMenuItem);
        Menu viewMenu = new Menu("_View");
        RadioMenuItem defaultView = new RadioMenuItem("_Default");
        defaultView.setOnAction(actionEvent -> onView(1));
        defaultView.setToggleGroup(views);
        defaultView.setSelected(true);
        RadioMenuItem darkView = new RadioMenuItem("Dar_k");
        darkView.setOnAction(actionEvent -> onView(2));
        darkView.setToggleGroup(views);
        viewMenu.getItems().addAll(defaultView, darkView);
        // Help menu with just an about item for now
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu);
        return menuBar;

    }

    private void onView(int i) {

        ObservableList<Toggle> list = views.getToggles();
        if(i == 1) {

            views.selectToggle(list.get(0));
            root.getStylesheets().remove(root.getStylesheets().size()-1);

        } else {

            views.selectToggle(list.get(1));
            root.getStylesheets().add(getClass().getResource("DarkView.css").toExternalForm());

        }

    }

    private static void setStatus(String s) {

        mStatus.setText(s);

    }

    private static void onAbout() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("George Polyak, CSCD 370 Homework 3, Fall 2017");
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
