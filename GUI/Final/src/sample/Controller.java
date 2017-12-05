package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Controller extends Dialog<ButtonType>{

    @FXML GridPane gridPane;
    @FXML TitledPane titledPane;
    @FXML ChoiceBox<String> initGhosts;
    @FXML ChoiceBox<String> addGhosts;

    private ButtonType mOK;

    public Controller() throws IOException{

        super();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Prefs.fxml"));
        loader.setController(this);
        getDialogPane().setContent(loader.load());
        mOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(mOK, cancel);
        getDialogPane().lookupButton(mOK).addEventFilter(ActionEvent.ACTION, this::onOK);
        initialize();

    }

    private void initialize() {

        AppSettings.readPrefs(Main.class);
        initGhosts.setValue(AppSettings.initGhosts + "");
        addGhosts.setValue(AppSettings.addGhosts + "");

    }

    private void onOK(ActionEvent event) {

        AppSettings.initGhosts = Integer.parseInt(initGhosts.getValue());
        AppSettings.addGhosts = Integer.parseInt(addGhosts.getValue());
        AppSettings.storePrefs(Main.class);
        event.consume();

    }

}