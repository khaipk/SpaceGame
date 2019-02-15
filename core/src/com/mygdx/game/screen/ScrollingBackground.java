package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;

public class ScrollingBackground {
	
	
	Texture image;
	
	int y1, y2;
	private static final int SPEED = 200;
	
	
	
	public ScrollingBackground() {
		image = new Texture("purple.png");
		
		y1 = 0;
		y2 = y1 + SpaceGame.HEIGHT;
	}
	
	public void updateAndRender(float deltaTime, SpriteBatch batch) {
		//update
		y1 -= SPEED * deltaTime;
		y2 -= SPEED * deltaTime;
		
		if(y1 < - SpaceGame.HEIGHT+150) {
			y1 = y2 + SpaceGame.HEIGHT;
		}
		if(y2 < - SpaceGame.HEIGHT+150) {
			y2 = y1 + SpaceGame.HEIGHT;
		}
		
		//render
		batch.draw(image, 0, y1, SpaceGame.WIDTH, SpaceGame.HEIGHT);
		batch.draw(image, 0, y2, SpaceGame.WIDTH, SpaceGame.HEIGHT);
	}
	
	
}
