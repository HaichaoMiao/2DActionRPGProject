package com.haichao.protogame.level.tile;

import com.haichao.protogame.graphics.Screen;
import com.haichao.protogame.graphics.Sprite;

/**
 * GrassTile
 *
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public class GrassTile extends Tile{

	public GrassTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
