package com.haichao.protogame.level.tile.spawn_level;

import com.haichao.protogame.graphics.Screen;
import com.haichao.protogame.graphics.Sprite;
import com.haichao.protogame.level.tile.Tile;

public class SpawnWaterTile extends Tile {

	public SpawnWaterTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
