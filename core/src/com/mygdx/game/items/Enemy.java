package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.tools.CheckCollision;

public class Enemy {
	
	public int x;
	public int y;
	public static final int DEFAULT_Y = SpaceGame.HEIGHT;
	public static final int WIDTH = 43;
	public static final int HEIGHT = 43;
	public static final int SPEED = 4;
	
	Texture img;
	
	public Enemy(int x) {
		this.x = x;
		this.y = DEFAULT_Y;
		
		img = new Texture("meteorBrown_med1.png");
	}
	
	public void move() {
		y -= SPEED;
	}
	
	public void render(SpriteBatch batch) {
		move();
		batch.draw(img, x, y, WIDTH, HEIGHT);
	}
	
	public CheckCollision getCheckCollision() {
		return new CheckCollision(x, y, WIDTH, HEIGHT);
	}
	
}
