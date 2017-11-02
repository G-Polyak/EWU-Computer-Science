package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by lars on 10/24/17.
 */
public class Line implements Serializable {

	private double x1, y1, x2, y2;
	private int width;
	private transient Color color;

	public Line(double x1, double y1, double x2, double y2, int width, Color color) {

		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.width = width;
		this.color = color;

	}

	public void draw(Canvas aCanvas) {

		aCanvas.getGraphicsContext2D().setLineWidth((double) width);
		aCanvas.getGraphicsContext2D().setStroke(color);
		aCanvas.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);

	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {

		out.defaultWriteObject();
		out.writeDouble(color.getRed());
		out.writeDouble(color.getGreen());
		out.writeDouble(color.getBlue());

	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {

		in.defaultReadObject();
		color = new Color(in.readDouble(), in.readDouble(), in.readDouble(), 1);

	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
