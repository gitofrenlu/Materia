
package com.example.material.utils;

public class ImagePosition {
	public static final int TOP = 32;
	public static final int MIDDLE = 16;
	public static final int BOTTOM = 8;
	public static final int LEFT = 4;
	public static final int CENTER = 2;
	public static final int RIGHT = 1;
	private static final int PADDING_HORI = 6;
	private static final int PADDING_VERT = 6;
	private int boxPosX;
	private int boxPosY;

	public ImagePosition(int width, int height, int boxWidth, int boxHeight, int style) {
		switch(style & 7) {
			case 1:
				this.boxPosX = width - boxWidth - 6;
				break;
			case 2:
			case 3:
			default:
				this.boxPosX = (width - boxWidth) / 2;
				break;
			case 4:
				this.boxPosX = 6;
		}

		switch(style >> 3 << 3) {
			case 8:
			default:
				this.boxPosY = height - boxHeight - 6;
				break;
			case 16:
				this.boxPosY = (height - boxHeight) / 2;
				break;
			case 32:
				this.boxPosY = 6;
		}

	}

	public int getX() {
		return this.getX(0);
	}

	public int getX(int x) {
		return this.boxPosX + x;
	}

	public int getY() {
		return this.getY(0);
	}

	public int getY(int y) {
		return this.boxPosY + y;
	}
}
