package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class Main extends Application implements ChangeListener<String>{

    private static Label mStatus;
    private static ImageView mImage = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception {

        mImage.setImage(new Image("images/logo.png"));
	    mStatus = new Label("Everything is Copacetic");
        BorderPane root = new BorderPane();
        ListView<String> list = new ListView<>();
        ObservableList<String> list1 = FXCollections.observableArrayList("First Album", "Cindy", "Fred", "Kate",
		        "Keith", "Matt", "Ricky");
        list.setItems(list1);
	    list.getSelectionModel().selectedItemProperty().addListener(this);
	    list.setPrefWidth(computeStringWidth("First Album") + 30);
        root.setCenter(mImage);
        // add the menus
        root.setTop(buildMenus());
        root.setLeft(list);
        root.setBottom(new ToolBar(mStatus));
        Scene scene = new Scene(root);
        primaryStage.setTitle("The B-52s");
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
        alert.setHeaderText("George Polyak, CSCD 370 Lab 1, Fall 2017");
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

    	if(newValue.toLowerCase().contains("album")) {
		    mImage.setImage(new Image("images/logo.png"));
		    mStatus.setText("First Album, 1979");
	    } else if(newValue.toLowerCase().contains("cindy")) {
		    mImage.setImage(new Image("images/cindy.png"));
		    mStatus.setText("Cindy Wilson (Percussion since 1976)");
	    } else if(newValue.toLowerCase().contains("fred")) {
		    mImage.setImage(new Image("images/fred.png"));
    		mStatus.setText("Fred Schneider (Vocals, Cowbell, since 1976)");
	    } else if(newValue.toLowerCase().contains("kate")) {
		    mImage.setImage(new Image("images/kate.png"));
		    mStatus.setText("Kate Pierson (Organ since 1976)");
	    } else if(newValue.toLowerCase().contains("keith")) {
		    mImage.setImage(new Image("images/keith.png"));
		    mStatus.setText("Keith Strickland (Drums, Guitar, since 1976)");
	    } else if(newValue.toLowerCase().contains("matt")) {
    		mImage.setImage(new Image("images/matt.png"));
		    mStatus.setText("Matt Flynn (Touring, Drums, prior to 2004)");
	    } else if(newValue.toLowerCase().contains("rick")) {
    		mImage.setImage(new Image("images/rickey.png"));
		    mStatus.setText("Ricky Wilson, deceased (Bass, 1976-1985)");
	    }

	}

	private double computeStringWidth(String s) {
		// put it in a Text object
		final Text text = new Text(s) ;
		// now get the width
		return text.getLayoutBounds().getWidth() ;
	}

}
