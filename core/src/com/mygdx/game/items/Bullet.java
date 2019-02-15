package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CheckCollision;

public class Bullet {
	
	public static final int WIDTH = 3;
	public static final int HEIGHT = 12;
	public int x;
	public int y;
	private static final int DEFAULT_Y = 20;
	private static final int SPEED = 5;
	
	Texture img;
	
	public Bullet(int x) {
		this.x = x;
		this.y = DEFAULT_Y;
		img = new Texture("bullet.png");
	}
	
	public void move() {
		y += SPEED;
	}
	
	public void render(SpriteBatch batch) {
		move();
		batch.draw(img, x, y, WIDTH, HEIGHT);
	}
	
	public CheckCollision getCheckCollision() {
		return new CheckCollision(x, y, WIDTH, HEIGHT);
	}
	
}
