package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Main extends Application {

    private static Label mStatus;
    private static Text mCenterText;
    private static BorderPane mRoot = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {

        mCenterText = new Text("File -> Options");
        mRoot.setCenter(mCenterText);
        // add the menus
        mRoot.setTop(buildMenus());
        mStatus = new Label("Everything is Copacetic");
        ToolBar toolBar = new ToolBar(mStatus);
        mRoot.setBottom(toolBar);
        Scene scene = new Scene(mRoot, 300, 250);
        primaryStage.setTitle("FXML Layout");
        primaryStage.setScene(scene);
        primaryStage.show();

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
        MenuItem options = new MenuItem("_Options");
        options.setAccelerator(new KeyCodeCombination(KeyCode.O,
                KeyCombination.CONTROL_DOWN));
        options.setOnAction(aciontEvent -> onOptions());
        fileMenu.getItems().addAll(options, quitMenuItem);
        // Help menu with just an about item for now
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;

    }

    private static void onOptions() {

        try {

            Controller controller = new Controller();
            Optional<ButtonType> op = controller.showAndWait();
            if(op.isPresent()) {

                if(op.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                    setStatus("It worked");
                    if (!AppSettings.mDateOrString) {
                        mCenterText = new Text(AppSettings.mUserString);
                    } else {

                        SimpleDateFormat date = new SimpleDateFormat();
                        mCenterText = new Text(date.format(new Date()));

                    }
                    if(AppSettings.mBold && AppSettings.mItalics) {
                        mCenterText.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, AppSettings.mFontSize));
                    } else if(AppSettings.mBold && !AppSettings.mItalics) {
                        mCenterText.setFont(Font.font(null, FontWeight.BOLD, AppSettings.mFontSize));
                    } else if(!AppSettings.mBold && AppSettings.mItalics) {
                        mCenterText.setFont(Font.font(null, FontPosture.ITALIC, AppSettings.mFontSize));
                    } else {
                        mCenterText.setFont(new Font(AppSettings.mFontSize));
                    }
                    mRoot.setCenter(mCenterText);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            setStatus("Bad things just happened idk...");
        }

    }

    private static void setStatus(String s) {
        mStatus.setText(s);
    }

    private static void onAbout() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("George Polyak, CSCD 370 Lab 6, Fall 2017");
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
