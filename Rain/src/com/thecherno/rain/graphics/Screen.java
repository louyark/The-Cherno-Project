package com.thecherno.rain.graphics;

public class Screen {

	public int width, height;
	public int[] pixels;

	public Screen(int width, int height) {

		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x+y*height]= 0xff00ff;
			}
		}
	}
}
