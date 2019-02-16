package com.mygdx.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CheckCollision;

public class EnemyShip {
	
	Texture img;
	
	public float x, dx;
	public static final float WIDTH = 104;
	private static final float HEIGHT = 84;
	public static final float DEFAULT_Y = Gdx.graphics.getHeight() - HEIGHT;
	private static final float SPEED = 200;
	
	
	
	public EnemyShip(float x) {
		this.x = x;
		dx = 5;
		img = new Texture("enemyBlack2.png");
	}
	
	public void updateAndRender(SpriteBatch batch, float deltaTime) {
		// update
		if(x<0) dx = SPEED * deltaTime;
		if(x> Gdx.graphics.getWidth()-WIDTH) dx = -SPEED * deltaTime;
		x += dx;
		
		// render
		batch.draw(img, x, DEFAULT_Y, WIDTH, HEIGHT);
		
	}
	
	public CheckCollision getEnemyShip() {
		return new CheckCollision(x, DEFAULT_Y, WIDTH, HEIGHT);
	}
	
	
	
}
