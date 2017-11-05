package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class BouncyMass extends Region {

    private Canvas mCanvas;
    private final int mWidth = 30;
    private final int mHeight = 40;
    private final double mAspRatio = 30.0 / 40.0;
    private DoubleProperty currY;
    private DoubleProperty minY;
    private DoubleProperty maxY;

    public BouncyMass() {

        mCanvas = new Canvas();
        getChildren().add(mCanvas);
        setPrefSize(mWidth * 100, mHeight * 100);
        currY = new SimpleDoubleProperty(150);
        minY = new SimpleDoubleProperty(150);
        maxY = new SimpleDoubleProperty(150);

    }

    public DoubleProperty currYProperty() {
        return currY;
    }

    public DoubleProperty minYProperty() {
        return minY;
    }

    public DoubleProperty maxYProperty() {
        return maxY;
    }

    public void draw() {

        GraphicsContext gc = mCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        //gc.save();
        double scaleX = mCanvas.getWidth() / mWidth;
        double scaleY = mCanvas.getHeight() / mHeight;
        //gc.scale(scaleX, scaleY); //this erased everything on-screen, unsure why
        gc.setStroke(Color.BLACK);
        gc.strokeRect(87, 150, 100, 50);
        drawLinks(150);
        //gc.restore();

    }

    private void drawLinks(double y) {

        GraphicsContext gc = mCanvas.getGraphicsContext2D();
        double radii = y / 11;
        double y2 = 0.0;
        for (int i = 0; i < 10; i++) {
            gc.strokeOval(mCanvas.getWidth() / 2, y2, 25, radii * 2);
            y2 += radii;
        }

    }

    public void redraw(double y) {

        GraphicsContext gc = mCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        //gc.save();
        double scaleX = mCanvas.getWidth() / mWidth;
        double scaleY = mCanvas.getHeight() / mHeight;
        //gc.scale(scaleX, scaleY); //this erased everything on-screen, unsure why
        gc.setStroke(Color.BLACK);
        double yVal = 7.75 * y;
        double bottom = yVal + 50;
        gc.strokeRect(87, 150 + yVal, 100, 50);
        drawLinks(150 + yVal);
        //gc.restore();
        currY.set(bottom);
        if (bottom < minY.get()) {
            minY.set(bottom);
        }
        if (bottom > maxY.get()) {
            maxY.set(bottom);
        }

    }

    @Override
    protected void layoutChildren() {

        double accWidth = this.getWidth();
        double accHeight = this.getHeight();
        double width = accHeight * mAspRatio;
        double height = accWidth / mAspRatio;
        if (height > accHeight) {
            mCanvas.setHeight(accHeight);
            mCanvas.setWidth(width);
        } else {
            mCanvas.setHeight(height);
            mCanvas.setWidth(accWidth);
        }

        this.layoutInArea(mCanvas, 0, 0, accWidth, accHeight,
                0, HPos.CENTER, VPos.CENTER);
        draw();

    }

}
