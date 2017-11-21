package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class CustomPanel extends Region {

    private Rectangle rectangle;
    private Label text;
    private ContextMenu contextMenu;
    private TextWithRGB data;
    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private ClipboardContent content = new ClipboardContent();
    private static DataFormat key = new DataFormat("TextWithRGB");
    private StringProperty status;

    public CustomPanel(String text, double rPercent, double gPercent, double bPercent) {

        buildContextMenu();
        this.text = new Label(text);
        Color p = new Color(rPercent, gPercent, bPercent, 1);
        rectangle = new Rectangle(this.getWidth(), this.getHeight(), p);
        rectangle.widthProperty().bind(this.widthProperty());
        rectangle.heightProperty().bind(this.heightProperty());
        rectangle.setOnMouseClicked(this::showContextMenu);
        this.text.setMinWidth(150);
        this.text.setAlignment(Pos.CENTER);
        this.getChildren().addAll(rectangle, this.text);
        this.setPrefSize(300, 150);
        data = new TextWithRGB(text, rPercent * 255, gPercent * 255, bPercent * 255);
        status = new SimpleStringProperty("");

    }

    public StringProperty statusProperty() {
        return status;
    }

    private void buildContextMenu() {

        MenuItem changeString = new MenuItem("Change String");
        changeString.setOnAction(actionEvent -> onChangeString());
        MenuItem changeColor = new MenuItem("Change Color");
        changeColor.setOnAction(actionEvent -> onChangeColor());
        MenuItem copySettings = new MenuItem("Copy Settings");
        copySettings.setOnAction(actionEvent -> onCopySettings());
        MenuItem pasteSettings = new MenuItem("Paste Settings");
        pasteSettings.setOnAction(actionEvent -> onPasteSettings());
        contextMenu = new ContextMenu(changeString, changeColor, copySettings, pasteSettings);

    }

    private void showContextMenu(MouseEvent mouseEvent) {

        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            contextMenu.show(this, mouseEvent.getScreenX(), mouseEvent.getScreenY());
        }

    }

    private void onPasteSettings() {

        if(clipboard.hasContent(key)) {

            data = (TextWithRGB) clipboard.getContent(key);
            text.setText(data.text);
            rectangle.setFill(Color.rgb((int) data.r, (int) data.g, (int) data.b));
            status.setValue("Paste success");

        } else if(clipboard.hasString()) {

            String s = clipboard.getString().trim();
            if (!s.equals("")) {

                text.setText(s);
                data.text = s;
                status.setValue("Paste success");

            } else {
                status.setValue("Paste failure");
            }

        } else {
            status.setValue("Paste failure");
        }

    }

    private void onCopySettings() {

        content.put(key, data);
        content.putString(data.toString());
        clipboard.setContent(content);

    }

    private void onChangeColor() {

        int r = ThreadLocalRandom.current().nextInt(1, 255 + 1);
        int g = ThreadLocalRandom.current().nextInt(1, 255 + 1);
        int b = ThreadLocalRandom.current().nextInt(1, 255 + 1);
        rectangle.setFill(Color.rgb(r, g, b));
        data.r = r;
        data.g = g;
        data.b = b;

    }

    private void onChangeString() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Please input the desired text to be shown.");
        dialog.showAndWait();
        String s = dialog.getResult();
        if (s != null) {

            text.setText(dialog.getResult());
            data.text = dialog.getResult();

        }

    }

    @Override
    public void layoutChildren() {

        double pWidth = rectangle.getWidth();
        double pHeight = rectangle.getHeight();
        double tWidth = text.getWidth();
        double tHeight = text.getHeight();
        double x = (pWidth / 2) - (tWidth / 2);
        double y = (pHeight / 2) - (tHeight / 2);
        text.relocate(x, y);
        this.layoutInArea(text, x, y, text.getWidth(), text.getHeight(),
                0, HPos.CENTER, VPos.CENTER);

    }

}
