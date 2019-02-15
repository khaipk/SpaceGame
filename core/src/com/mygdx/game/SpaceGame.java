package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.MenuScreen;
import com.mygdx.game.screen.ScrollingBackground;

public class SpaceGame extends Game {
	public SpriteBatch batch;
	
	public ScrollingBackground scrollingBackground;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.scrollingBackground = new ScrollingBackground();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		
	}
}
