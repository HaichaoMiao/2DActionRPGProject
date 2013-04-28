package com.haichao.protogame.entity.mob;

import com.haichao.protogame.entity.Entity;
import com.haichao.protogame.graphics.Sprite;

/**
 * Mob
 *
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public abstract class Mob extends Entity{
	
	protected Sprite sprite;
	protected int dir = -1; // 0 = north, 1 = east, 2 = south, 3 = west
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
		
		
	}

	public void update() {
		
		
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		if(level.getTile((x+xa) >> 4, (y+ya) >> 4).solid()) solid = true; 
		return solid;
	}
	
	public void render() {
	}
	
}
