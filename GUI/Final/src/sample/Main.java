package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;

public class Main extends Application {

    public static Canvas board = new Canvas(500, 500);
    public static Canvas pieces = new Canvas(500, 500);
    private static int lvl = 1;
    private static Maze maze;
    private static Ghost[] ghosts;
    private static Player player;
    private static long mPreviousTime = 0;
    private static int mWorkStep = 0;
    private static Label label;
    private static SimpleBooleanProperty win;
    private static SimpleBooleanProperty lose;
    private static MenuItem goMenuItem;
    private static MenuItem pauseMenuItem;
    private static MenuItem openMenuItem;
    private static MenuItem saveMenuItem;
    private static MenuItem settingsMenuItem;
    private static int initGhosts;
    private static int addGhosts;
    private static Preferences mPrefs;

    private static AnimationTimer mTimer = new AnimationTimer() {

        @Override
        public void handle(long now) {
            onTimer(now);
        }

    };

    @Override
    public void start(Stage primaryStage) throws Exception {

        AppSettings.readPrefs(getClass());
        mPrefs = Preferences.userNodeForPackage(getClass());
        mPrefs.addPreferenceChangeListener(this::onPrefChanged);
        initGhosts = AppSettings.initGhosts;
        addGhosts = AppSettings.addGhosts;
        maze = new Maze(lvl);
        ghosts = new Ghost[initGhosts];
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i] = new Ghost(maze);
        }
        player = new Player(maze, ghosts);
        resetWinLose();
        populateBoard();
        StackPane stackPane = new StackPane(board, pieces);
        BorderPane root = new BorderPane();
        root.setCenter(stackPane);
        root.setTop(buildMenus());
        label = new Label();
        updateLabel();
        ToolBar toolBar = new ToolBar(label);
        root.setBottom(toolBar);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {

                    case UP:
                        onMove("up");
                        break;
                    case DOWN:
                        onMove("down");
                        break;
                    case LEFT:
                        onMove("left");
                        break;
                    case RIGHT:
                        onMove("right");
                        break;
                    case HOME:
                        onHome();
                        break;
                    case G:
                        onGo();
                        break;
                    case S:
                        onStop();
                        break;

                }

            }
        });
        primaryStage.setTitle("Not Pac-Man");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void onPrefChanged(PreferenceChangeEvent prefChangeEvent) {
        Platform.runLater(Main::updatePrefs);
    }

    private void resetWinLose() {

        lose = new SimpleBooleanProperty();
        win = new SimpleBooleanProperty();
        win.addListener(event -> {
            Platform.runLater(this::nextLevel);
            win.set(false);
        });
        lose.addListener(event -> {
            Platform.runLater(this::onLose);
            lose.set(false);
        });

    }

    private static void onMove(String direction) {
        player.setDirection(direction);
    }

    public static void onStop() {

        goMenuItem.setDisable(false);
        pauseMenuItem.setDisable(true);
        openMenuItem.setDisable(false);
        saveMenuItem.setDisable(false);
        settingsMenuItem.setDisable(false);
        mTimer.stop();

    }

    private void onGo() {

        goMenuItem.setDisable(true);
        pauseMenuItem.setDisable(false);
        openMenuItem.setDisable(true);
        saveMenuItem.setDisable(true);
        settingsMenuItem.setDisable(true);
        mTimer.start();

    }

    private static void onTimer(long now) {

        now = System.currentTimeMillis();
        long elapsed = (now - mPreviousTime);
        if (elapsed > 200) { //TIMER_MSEC

            if (doSomeWork()) {
                mTimer.stop();
                mWorkStep = 0;
                if (player.cakesEaten < maze.totalCakes) {
                    win.set(false);
                    lose.set(true);
                } else {
                    win.set(true);
                    lose.set(false);
                }
            }
            mPreviousTime = now;

        }

    }

    private static boolean doSomeWork() {

        mWorkStep++;
        for (Ghost ghost : ghosts) {
            ghost.jump(pieces, ghost.getDirection());
        }
        player.jump(pieces, player.getDirection());
        updateLabel();
        return isOnPlayer() || player.isDead || maze.cakesLeft == 0;

    }

    private static boolean isOnPlayer() {

        for (Ghost ghost : ghosts) {

            if (ghost.isOnPlayer) {
                return true;
            }

        }
        return false;

    }

    private void resetCurrentLevel() {

        GraphicsContext boardGc = board.getGraphicsContext2D();
        boardGc.setFill(Color.WHITE);
        boardGc.fillRect(0, 0,
                board.getWidth(), board.getHeight());
        pieces.getGraphicsContext2D().clearRect(0, 0,
                pieces.getWidth(), pieces.getHeight());
        maze = new Maze(lvl);
        resetGhosts();
        player = new Player(maze, ghosts);
        populateBoard();
        updateLabel();
        mPreviousTime = 0;
        goMenuItem.setDisable(false);
        pauseMenuItem.setDisable(true);
        settingsMenuItem.setDisable(false);

    }

    private static void resetGhosts() {

        if (lvl == 1) {

            ghosts = new Ghost[initGhosts];
            for (int i = 0; i < initGhosts; i++) {
                ghosts[i] = new Ghost(maze);
            }

        } else {

            ghosts = new Ghost[initGhosts + addGhosts];
            for (int i = 0; i < initGhosts + addGhosts; i++) {
                ghosts[i] = new Ghost(maze);
            }

        }

    }

    private void onLose() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Play again?");
        alert.setHeaderText("You lost! Try again?");
        Optional<ButtonType> op = alert.showAndWait();
        if (op.isPresent() && op.get().equals(ButtonType.OK)) {
            resetCurrentLevel();
        }
        resetWinLose();

    }

    private void nextLevel() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Play next Level?");
        alert.setHeaderText("You won! Play next level?");
        Optional<ButtonType> op = alert.showAndWait();
        if (op.isPresent() && op.get().equals(ButtonType.OK)) {

            lvl++;
            resetCurrentLevel();

        } else {

            //prompt save

        }
        resetWinLose();

    }

    private static void updateLabel() {

        label.setText("Level: " + lvl + "   Cakes eaten: " + player.cakesEaten
                + "   Cakes left: " + maze.cakesLeft);

    }

    private void onHome() {

        board.setUserData("board");
        pieces.setUserData("pieces");
        maze.removeCake(board, ghosts[0].getX(), ghosts[0].getY());
        maze.removeCake(pieces, ghosts[0].getX(), ghosts[0].getY());
        board.setUserData(null);
        pieces.setUserData(null);
        for (int i = 0; i < 20; i++) {

            for (int j = 0; j < 20; j++) {

                if (maze.selectedMaze[i][j] == 1) {

                    board.setUserData("board");
                    pieces.setUserData("pieces");
                    maze.removeCake(board, j, i);
                    maze.removeCake(pieces, j, i);
                    board.setUserData(null);
                    pieces.setUserData(null);

                }

            }

        }
        player.cakesEaten = maze.totalCakes;

    }

    private static void populateBoard() {

        maze.draw(board);
        for (Ghost ghost : ghosts) {
            ghost.drawGhost(pieces);
        }
        player.drawPlayer(pieces);

    }

    private MenuBar buildMenus() {

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("_File");
        openMenuItem = new MenuItem("_Open");
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O,
                KeyCombination.CONTROL_DOWN));
        openMenuItem.setOnAction(actionEvent -> onOpen());
        saveMenuItem = new MenuItem("_Save");
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S,
                KeyCombination.CONTROL_DOWN));
        saveMenuItem.setOnAction(actionEvent -> onSave());
        SeparatorMenuItem dashes = new SeparatorMenuItem();
        MenuItem exitMenuItem = new MenuItem("E_xit");
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X,
                KeyCombination.CONTROL_DOWN));
        exitMenuItem.setOnAction(actionEvent -> onExit());
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem, dashes, exitMenuItem);

        Menu gameMenu = new Menu("_Game");
        MenuItem newGame = new MenuItem("_New");
        newGame.setAccelerator(new KeyCodeCombination(KeyCode.N,
                KeyCombination.CONTROL_DOWN));
        newGame.setOnAction(actionEvent -> onNew());
        SeparatorMenuItem dashes2 = new SeparatorMenuItem();
        goMenuItem = new MenuItem("_Go");
        goMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.G,
                KeyCombination.CONTROL_DOWN));
        goMenuItem.setOnAction(actionEvent -> onGo());
        pauseMenuItem = new MenuItem("_Pause");
        pauseMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.P,
                KeyCombination.CONTROL_DOWN));
        pauseMenuItem.setOnAction(actionEvent -> onStop());
        pauseMenuItem.setDisable(true);
        SeparatorMenuItem dashes3 = new SeparatorMenuItem();
        settingsMenuItem = new MenuItem("S_ettings");
        settingsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.E,
                KeyCombination.CONTROL_DOWN));
        settingsMenuItem.setOnAction(actionEvent -> onSettings());
        gameMenu.getItems().addAll(newGame, dashes2, goMenuItem, pauseMenuItem, dashes3, settingsMenuItem);

        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
        return menuBar;

    }

    private static void updatePrefs() {

        int temp = initGhosts;
        initGhosts = AppSettings.initGhosts;
        addGhosts = AppSettings.addGhosts;
        if (lvl == 1 && temp != initGhosts) {

            for (Ghost ghost : ghosts) {
                ghost.erase(pieces);
            }
            resetGhosts();
            for (Ghost ghost : ghosts) {
                ghost.drawGhost(pieces);
            }

        }

    }

    private static void onSettings() {

        try {

            Controller controller = new Controller();
            controller.show();
            settingsMenuItem.setDisable(true);
            controller.setOnCloseRequest(actionEvent -> settingsMenuItem.setDisable(false));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void onNew() {

        lvl = 1;
        resetCurrentLevel();
        onStop();

    }

    private static void onExit() {
        Platform.exit();
    }

    private static void onSave() {

    }

    private static void onOpen() {

    }

    private static void onAbout() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("George Polyak, CSCD 370 Final, Fall 2017");
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
