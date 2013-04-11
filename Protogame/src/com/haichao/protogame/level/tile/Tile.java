package com.haichao.protogame.level.tile;

import com.haichao.protogame.graphics.Screen;
import com.haichao.protogame.graphics.Sprite;

/**
 * Tile
 *
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public class Tile {
	//every tile renders itself
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
}