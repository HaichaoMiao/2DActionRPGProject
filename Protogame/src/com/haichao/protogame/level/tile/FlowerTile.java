package com.haichao.protogame.level.tile;

import com.haichao.protogame.graphics.Screen;
import com.haichao.protogame.graphics.Sprite;

/**
 * FlowerTile
 *
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public class FlowerTile extends Tile {

	public FlowerTile(Sprite sprite) {
		super(sprite);
	}
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
