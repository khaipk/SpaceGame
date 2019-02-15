package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.SpaceGame;

public class Collision {
	
	private Animation<TextureRegion> anim = null;
	
	private int x, y;
	private static final int SIZE = 32;
	private static final float TIME = 0.2f;
	private static final int OFFSET = 8;
	private float time;
	public boolean remove = false;
	
	public Collision(int x, int y) {
		this.x = x - OFFSET;
		this.y = y - OFFSET;
		time = 0;
		
		if(anim == null)
		anim = new Animation<TextureRegion>(TIME, TextureRegion.split(new Texture("explosion.png"), SIZE, SIZE)[0]);
	}
	
	public void update(float deltatime) {
		time += deltatime;
		if(anim.isAnimationFinished(time))
			remove = true;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(anim.getKeyFrame(time), x, y);
	}
}
