package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static BouncyMass mMass = new BouncyMass();
    private static ProgressBar mProgress = new ProgressBar(0.0);
    private static long mPreviousTime = 0;
    private static int mWorkStep = 0;
    private static double velocity = 0.0;
    private static double displacement = 0.0;
    private static AnimationTimer mTimer = new AnimationTimer() {

        @Override
        public void handle(long now) {
            onTimer(now);
        }

    };

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        root.setCenter(mMass);
        root.setBottom(new ToolBar(mProgress));
        root.setTop(buildMenus());
        Scene scene = new Scene(root, 300, 400);
        scene.setOnKeyPressed(this::onKey);
        mProgress.progressProperty().bind((mMass.currYProperty().subtract(mMass.minYProperty()))
                .divide(mMass.maxYProperty().subtract(mMass.minYProperty()))); //bar finishes too fast on first bounce
        primaryStage.setTitle("Bouncy Mass Simulation");                       //only, couldn't figure out why
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void onKey(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {

            case DOWN:
                mTimer.stop();
                break;
            case UP:
                mTimer.start();
                break;

        }

    }

    private static void onTimer(long now) {

        now = System.currentTimeMillis();
        long elapsed = (now - mPreviousTime);
        if (elapsed > 25) { //TIMER_MSEC

            if (!doSomeWork()) {
                mTimer.stop();
                mWorkStep = 0;
            }
            mPreviousTime = now;

        }

    }

    private static boolean doSomeWork() {

        mWorkStep++;
        double acceleration = 9.80665 - (1.1 * displacement);
        velocity += acceleration * 25 / 1000f;
        displacement += velocity * 25 / 1000f;
        mMass.redraw(displacement);
        return (mWorkStep < Integer.MAX_VALUE); //so that it will run indefinitely

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

    private static void onAbout() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("George Polyak, CSCD 370 Homework 2, Fall 2017");
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
