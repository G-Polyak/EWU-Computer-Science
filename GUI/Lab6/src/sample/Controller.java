package sample;

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
	@FXML CheckBox boldBox;
	@FXML CheckBox italicsBox;
	@FXML RadioButton showDateAndTime;
	@FXML RadioButton showString;
	@FXML TextField textField;
	@FXML TextField heightField;
	@FXML ToggleGroup showGroup;

	private ButtonType mOK;



	@FXML
	void onShowDateAndTime(ActionEvent event) {

		textField.setDisable(true);

	}

	@FXML
	void onShowString(ActionEvent event) {

		textField.setDisable(false);

	}

	public Controller() throws IOException{

		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("OptionsLayout.fxml"));
		loader.setController(this);
		getDialogPane().setContent(loader.load());
		mOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		getDialogPane().getButtonTypes().addAll(mOK, cancel);
		getDialogPane().lookupButton(mOK).addEventFilter(ActionEvent.ACTION, this::onOK);
		initialize();

	}

	private void initialize() {

		boldBox.setSelected(AppSettings.mBold);
		italicsBox.setSelected(AppSettings.mItalics);
		showDateAndTime.setSelected(AppSettings.mDateOrString);
		showString.setSelected(!AppSettings.mDateOrString);
		textField.setText(AppSettings.mUserString);
		heightField.setText(Integer.toString(AppSettings.mFontSize));

	}

	private void onOK(ActionEvent event) {

		int height = 0;
		try {
			height = Integer.parseInt(heightField.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (height < 8 || height > 40) {

			heightField.selectAll();
			heightField.requestFocus();
			event.consume();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Enter a valid font size");
			alert.showAndWait();
		} else {

			AppSettings.mDateOrString = showDateAndTime.isSelected();
			AppSettings.mBold = boldBox.isSelected();
			AppSettings.mItalics = italicsBox.isSelected();
			AppSettings.mUserString = textField.getText();
			AppSettings.mFontSize = Integer.parseInt(heightField.getText());

		}

	}

}
