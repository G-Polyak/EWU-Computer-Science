package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;

import javax.swing.undo.AbstractUndoableEdit;

public class UndoableNew extends AbstractUndoableEdit {

	private Canvas mLiveCanvas;
	private Canvas mCopyCanvas;

	public UndoableNew(Canvas canvas) {

		mLiveCanvas = canvas;
		mCopyCanvas = duplicateCanvas(canvas);

	}

	@Override
	public void undo() {
		copyCanvas(mCopyCanvas, mLiveCanvas);
	}

	@Override
	public String getPresentationName() {
		return "New";
	}

	public static void copyCanvas(Canvas src, Canvas dest) {

		WritableImage snapshot = src.snapshot(null, null);
		dest.getGraphicsContext2D().drawImage(snapshot, 0, 0);

	}

	public static Canvas duplicateCanvas(Canvas aCanvas) {

		WritableImage snapshot = aCanvas.snapshot(null, null);
		Canvas newCanvas = new Canvas(aCanvas.getWidth(), aCanvas.getHeight());
		newCanvas.getGraphicsContext2D().drawImage(snapshot, 0, 0);
		return newCanvas;

	}

}
