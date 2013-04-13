package com.haichao.protogame.level;

import com.haichao.protogame.graphics.Screen;
import com.haichao.protogame.level.tile.Tile;

/**
 * Level
 *
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level (String path) {
		loadLevel(path);
		generateLevel();
	}
	
	protected void generateLevel() {
			
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void update() {
		
	}
	
	private void time() {
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		
		//they define the render region of our screen
		int x0 = xScroll >> 4; //jump from pixel precision to tile precision
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x+y*width] == 0xff00ff00) return Tile.grass;
		if (tiles[x+y*width] == 0xffffff00) return Tile.flower;
		if (tiles[x+y*width] == 0xff7f7f00) return Tile.rock;
		return Tile.voidTile;
	}
	
	
	
	
	
	
}
