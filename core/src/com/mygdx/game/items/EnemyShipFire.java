package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CheckCollision;

public class EnemyShipFire {
	
	Texture image;
	private float x;
	private float y;
	private static final float WIDTH = 9;
	private static final float HEIGHT = 57;
	private static final float SPEED = 300;
	
	public EnemyShipFire(float x) {
		this.x = x;
		this.y = EnemyShip.DEFAULT_Y;
		image = new Texture("laserRed13.png");
	}
	
	public void updateAndRender(SpriteBatch batch, float deltaTime) {
		y -= SPEED * deltaTime;
		
		batch.draw(image, x, y, WIDTH, HEIGHT);
	}
	
	public CheckCollision getFire() {
		return new CheckCollision(x, y, WIDTH, HEIGHT);
	}
}
