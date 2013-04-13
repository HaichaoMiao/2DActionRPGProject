package com.haichao.protogame.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.haichao.protogame.level.tile.Tile;

/**
 * SpawmLevel
 *
 * @author hmiao87@gmail.com (Haichao Miao)
 */
public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load Level file!");
		}
	}

	// Grass = 0xff00ff00
	// flower = 0xffffff00
	// rock = 0xff7f7f00
	protected void generateLevel() {
	}
	
	public void update() {
		
	}
}
