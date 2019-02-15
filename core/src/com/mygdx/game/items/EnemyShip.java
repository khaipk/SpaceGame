package com.mygdx.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyShip {
	
	Texture img;
	
	private float x, dx;
	private static final float WIDTH = 104;
	private static final float HEIGHT = 84;
	private static final float DEFAULT_Y = Gdx.graphics.getHeight() - HEIGHT;
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
	
}
