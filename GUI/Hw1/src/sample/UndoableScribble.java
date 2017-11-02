package sample;

import javafx.scene.canvas.Canvas;

import javax.swing.undo.AbstractUndoableEdit;

public class UndoableScribble extends AbstractUndoableEdit {

	private Canvas mLiveCanvas;
	private Canvas mCopyCanvas;
	private Canvas mBackupCanvas;

	public UndoableScribble(Canvas canvas) {

		mLiveCanvas = canvas;
		mCopyCanvas = UndoableNew.duplicateCanvas(canvas);

	}

	@Override
	public void undo() {

		mBackupCanvas = UndoableNew.duplicateCanvas(mLiveCanvas);
		UndoableNew.copyCanvas(mCopyCanvas, mLiveCanvas);

	}

	@Override
	public void redo() {
		//Canvas temp = UndoableNew.duplicateCanvas(mLiveCanvas);
		UndoableNew.copyCanvas(mBackupCanvas, mLiveCanvas);
		//mBackupCanvas = temp;
	}

	@Override
	public String getPresentationName() {
		return "Scribble";
	}

	@Override
	public boolean canRedo() {
		return true;
	}

}
