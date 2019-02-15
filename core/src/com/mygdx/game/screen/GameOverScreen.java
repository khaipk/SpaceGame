package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SpaceGame;

public class GameOverScreen implements Screen {
	
	private SpaceGame game;
	
	private Texture img1, img2;
	private int x, y;
	private static final int WIDTH = 200;
	private static final int HEIGHT = 100;
	
	BitmapFont font;
	int score, highscore;
	
	public GameOverScreen(SpaceGame game, int score) {
		this.score = score;
		this.game = game;
		
		font = new BitmapFont();
		
		Preferences pref = Gdx.app.getPreferences("spacegame");
		this.highscore = pref.getInteger("highscore", 0);
		
		//check score vs highscore
		if(score > highscore) {
			pref.putInteger("highscore", score);
			pref.flush();
		}
	}
	
	@Override
	public void show() {
		img1 = new Texture("game_over.png");
		img2 = new Texture("blue.png");
	}

	@Override
	public void render(float delta) {
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			this.dispose();
			game.setScreen(new MenuScreen(game));
		}
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.draw(img2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		GlyphLayout fontLayout = new GlyphLayout(font, "Score: "+ score, Color.RED, Align.center, 0, false);
		font.draw(game.batch, fontLayout, Gdx.graphics.getWidth()/2 + fontLayout.width/2, Gdx.graphics.getHeight()/2 - HEIGHT/2);
		
		GlyphLayout highscoreLayout = new GlyphLayout(font, "Highscore: "+highscore, Color.RED, 0, Align.center, false);
		font.draw(game.batch, highscoreLayout, Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/2 - HEIGHT);
		
		x = Gdx.graphics.getWidth()/2 - WIDTH/2;
		y = Gdx.graphics.getHeight()/2 + HEIGHT/2;
		game.batch.draw(img1, x, y, WIDTH, HEIGHT);
		
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
