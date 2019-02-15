package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpaceGame;

public class MenuScreen implements Screen {

	private SpaceGame game;

	private Texture img1, img2, img3;
	private int x, y;
	private static final int width = 200;
	private static final int height = 100;

	public MenuScreen(SpaceGame game) {
		this.game = game;

	}

	@Override
	public void show() {
		img1 = new Texture("purple.png");
		img2 = new Texture("2.bmp");
		img3 = new Texture("1.bmp");
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		//draw scrolling background
		game.scrollingBackground.updateAndRender(delta, game.batch);

		// draw start button
		x = Gdx.graphics.getWidth()/2 - width/2;
		y = Gdx.graphics.getHeight()/2 - height/2;
		game.batch.draw(img2, x, y, width, height);

		if(Gdx.input.getX() > x && Gdx.input.getX() < x+width &&
				Gdx.input.getY() < Gdx.graphics.getHeight() - y && 
				Gdx.input.getY() >Gdx.graphics.getHeight() - y - height ) {
			
				game.batch.draw(img3, x, y, width, height);
				if(Gdx.input.isTouched()) {
					this.dispose();
					game.setScreen(new GameScreen(game));
			}

		}

		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
