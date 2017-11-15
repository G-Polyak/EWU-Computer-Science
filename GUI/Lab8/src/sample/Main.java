package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private static Label mStatus;
    private static Image mX = new Image("pics/x.png");
    private static Image mO = new Image("pics/o.png");

    @Override
    public void start(Stage primaryStage) throws Exception {

        ImageView x = new ImageView(mX);
        ImageView o = new ImageView(mO);
        x.setUserData("X");
        o.setUserData("O");
        x.setOnDragDetected(this::onDragDetected);
        o.setOnDragDetected(this::onDragDetected);
        HBox top = new HBox(x, o);
        top.setAlignment(Pos.CENTER);
        ImageView[] blanks = new ImageView[9];
        for (int i = 0; i < 9; i++) {

            blanks[i] = new ImageView("pics/blank.png");
            blanks[i].setUserData("BLANK");
            blanks[i].setOnDragOver(this::onDragOver);
            blanks[i].setOnDragDropped(this::onDragDropped);

        }
        ImageView[] divs = new ImageView[12];
        for (int i = 0; i < 12; i++) {

            if(i == 0 || i == 1 || i == 5 || i == 6 || i == 10 || i == 11) {
                divs[i] = new ImageView("pics/vert.png");
            } else {
                divs[i] = new ImageView("pics/horiz.png");
            }

        }
        GridPane grid = new GridPane();
        grid.addRow(0, blanks[0], divs[0], blanks[1], divs[1], blanks[2]);
        grid.add(divs[2], 0, 1);
        grid.add(divs[3], 2, 1);
        grid.add(divs[4], 4, 1);
        grid.addRow(2, blanks[3], divs[5], blanks[4], divs[6], blanks[5]);
        grid.add(divs[7], 0, 3);
        grid.add(divs[8], 2, 3);
        grid.add(divs[9], 4, 3);
        grid.addRow(4, blanks[6], divs[10], blanks[7], divs[11], blanks[8]);
        VBox wrapper = new VBox(top, grid);
        BorderPane root = new BorderPane();
        root.setCenter(wrapper);
        // add the menus
        root.setTop(buildMenus());
        mStatus = new Label("Everything is Copacetic");
        ToolBar toolBar = new ToolBar(mStatus);
        root.setBottom(toolBar);
        Scene scene = new Scene(root);
        primaryStage.setTitle("TicTacToe Shell");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void onDragDetected(MouseEvent actionEvent) {

        ImageView image = (ImageView) actionEvent.getSource();
        Dragboard dragboard = image.startDragAndDrop(TransferMode.COPY);
        Image temp = image.getImage();
        dragboard.setDragView(temp, temp.getWidth()/2, temp.getHeight()/2);
        ClipboardContent content = new ClipboardContent();
        content.putString((String) image.getUserData());
        dragboard.setContent(content);

    }

    private void onDragDropped(DragEvent actionEvent) {

        ImageView image = (ImageView) actionEvent.getSource();
        Dragboard dragboard = actionEvent.getDragboard();
        if (dragboard.getString().equals("X")) {
            image.setImage(mX);
            image.setUserData("X");
        } else {
            image.setImage(mO);
            image.setUserData("O");
        }
        actionEvent.setDropCompleted(true);


    }

    private void onDragOver(DragEvent actionEvent) {

        ImageView image = (ImageView) actionEvent.getSource();
        if (!image.getUserData().equals("BLANK")) {
            return;
        } else {

            Dragboard dragboard = actionEvent.getDragboard();
            if(dragboard.hasString()) {

                if(dragboard.getString().equals("X") || dragboard.getString().equals("O")) {
                    actionEvent.acceptTransferModes(TransferMode.COPY);
                }

            }

        }

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
        alert.setHeaderText("George Polyak, CSCD 370 Lab 8, Fall 2017");
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
