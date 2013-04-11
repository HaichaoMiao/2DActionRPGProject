package com.haichao.protogame.entity;

import java.util.Random;

import com.haichao.protogame.graphics.Screen;
import com.haichao.protogame.level.Level;

/**
 * Entity
 *
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public abstract class Entity {

	protected int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		
		//remove from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
}
