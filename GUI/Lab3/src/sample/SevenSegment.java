package sample;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class SevenSegment extends Region {

	private Canvas mCanvas;
	private int mDisplayVal;
	private boolean[] mOnOff = new boolean[7];
	private static final int mWidth = 22;
	private static final int mHeight = 38;
	private static final double mAspRatio = 22.0 / 38.0;
	private static final int mMarginX = 1;
	private static final int mMarginY = 1;
	private static final Color mOnColor = Color.RED;
	private static final Color mOffColor = new Color(1, 0, 0, 0.1);
	private static final double[] mXVerts = {2, 3, 17, 18, 17, 3};
	private static final double[] mYVerts = {2, 1, 1, 2, 3, 3};
	private static final int[][] mLookupTable = new int[11][7];

	private void init() {

		mCanvas = new Canvas();
		this.getChildren().add(mCanvas);
		this.setPrefSize(mWidth * 100, mHeight * 100);
		Arrays.fill(mOnOff, false);

	}

	public SevenSegment(int displayVal) {

		if (displayVal > -1 && displayVal < 11) {
			init();
			mDisplayVal = displayVal;
		}
		makeTable();
		setDisplayVal(displayVal);

	}

	public SevenSegment() {
		init();
		mDisplayVal = 10;
		makeTable();
		setDisplayVal(10);
	}

	private void makeTable() {

		Arrays.fill(mLookupTable[0], 1);
		mLookupTable[0][2] = 0;
		Arrays.fill(mLookupTable[1], 0);
		mLookupTable[1][1] = 1;
		mLookupTable[1][6] = 1;
		Arrays.fill(mLookupTable[2], 1);
		mLookupTable[2][3] = 0;
		mLookupTable[2][6] = 0;
		Arrays.fill(mLookupTable[3], 1);
		mLookupTable[3][3] = 0;
		mLookupTable[3][4] = 0;
		Arrays.fill(mLookupTable[4], 1);
		mLookupTable[4][0] = 0;
		mLookupTable[4][4] = 0;
		mLookupTable[4][5] = 0;
		Arrays.fill(mLookupTable[5], 1);
		mLookupTable[5][1] = 0;
		mLookupTable[5][4] = 0;
		Arrays.fill(mLookupTable[6], 1);
		mLookupTable[6][1] = 0;
		Arrays.fill(mLookupTable[7], 0);
		mLookupTable[7][0] = 1;
		mLookupTable[7][1] = 1;
		mLookupTable[7][6] = 1;
		Arrays.fill(mLookupTable[8], 1);
		Arrays.fill(mLookupTable[9], 1);
		mLookupTable[9][4] = 0;
		Arrays.fill(mLookupTable[10], 0);

	}

	public int getDisplayVal() {
		return mDisplayVal;
	}

	public void setDisplayVal(int displayVal) {

		if (!(displayVal > -1 && displayVal < 11)) {
			displayVal = 0;
		}
		for (int i = 0; i < 7; i++) {
			mOnOff[i] = mLookupTable[displayVal][i] == 1;
		}
		mDisplayVal = displayVal;

	}

	public boolean isDim() {

		return mDisplayVal == 10;

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

	public void draw() {

		GraphicsContext gc = mCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
		gc.save();
		double scaleX = mCanvas.getWidth() / mWidth;
		double scaleY = mCanvas.getHeight() / mHeight;
		gc.scale(scaleX, scaleY);
		gc.translate(mMarginX, mMarginY);
		gc.setFill((mOnOff[0]) ? mOnColor : mOffColor);
		gc.fillPolygon(mXVerts, mYVerts, 6);
		gc.restore();

		makeSegment(gc, 20, 0, 90, 1);
		makeSegment(gc, 0, 16, 0, 2);
		makeSegment(gc, 0, 20, 270, 3);
		makeSegment(gc, 0, 36, 270, 4);
		makeSegment(gc, 0, 32, 0, 5);
		makeSegment(gc, 16, 36, 270, 6);

	}

	private void makeSegment(GraphicsContext gc, int t1, int t2, int rot, int num) {

		gc.save();
		double scaleX = mCanvas.getWidth() / mWidth;
		double scaleY = mCanvas.getHeight() / mHeight;
		gc.scale(scaleX, scaleY);
		gc.translate(mMarginX, mMarginY);
		gc.translate(t1, t2);
		gc.rotate(rot);
		gc.setFill((mOnOff[num]) ? mOnColor : mOffColor);
		gc.fillPolygon(mXVerts, mYVerts, 6);
		gc.restore();

	}

}
