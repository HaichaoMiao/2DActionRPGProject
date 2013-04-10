package com.haichao.protogame.level;

/**
 * Level
 *
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public class Level {

	private int width, height;
	private int[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		
		tiles = new int[width * height];
		generateLevel();
	}
	
	private void generateLevel() {
			
	}
}
