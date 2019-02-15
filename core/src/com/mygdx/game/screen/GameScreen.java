package com.mygdx.game.screen;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.items.Bullet;
import com.mygdx.game.items.Collision;
import com.mygdx.game.items.Enemy;
import com.mygdx.game.items.EnemyShip;
import com.mygdx.game.tools.CheckCollision;

public class GameScreen implements Screen {

	private SpaceGame game;

	private CheckCollision player;

	private static ArrayList<Bullet> bullets;

	private static ArrayList<Collision> collisions;

	private static ArrayList<Enemy> enemies;
	private static ArrayList<EnemyShip> enemyShips;
	private int scoreToEnemyShip;

	private static final float MIN_ENEMY_TIME = 1.5f;
	private static final float MAX_ENEMY_TIME = 3.5f;
	private float enemyReloadTime ;
	private float enemyWaitTime ;
	private int enemyX;

	BitmapFont scoreFont;
	private int score;
	private float health = 1;

	Random random;

	private Texture img1, img2, img5 ;

	Animation<TextureRegion>[] rolls;
	int roll;
	private static final float ROLL_TIMER_SWITCH_TIME = 0.15f;
	float rollTimer;

	private int x;
	private static final int DEFAULT_Y = 10;

	private static final int ANIMATION_WIDTH = 17;
	private static final int ANIMATION_HEIGHT = 32;
	private static final int WIDTH = ANIMATION_WIDTH *3;
	private static final int HEIGHT = ANIMATION_HEIGHT *3;

	private static final int SPEED = 10;
	private static final float ANIMATION_SPEED = 0.5f;
	private float stateTime;
	
	float y1, y2;
	private static final int BACKGROUND_SPEED = 100;
	

	public GameScreen(SpaceGame game) {
		this.game = game;

		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		collisions = new ArrayList<Collision>();
		enemyShips = new ArrayList<EnemyShip>();

		x = SpaceGame.WIDTH/2 - WIDTH/2;
		player = new CheckCollision(x, DEFAULT_Y, WIDTH, HEIGHT);

		roll = 2;
		rolls = new Animation [5];
		rollTimer = 0;

		TextureRegion[][] region = TextureRegion.split(new Texture("ship.png"), ANIMATION_WIDTH, ANIMATION_HEIGHT);
		
		rolls[0] = new Animation(ANIMATION_SPEED, region[2]);
		rolls[1] = new Animation(ANIMATION_SPEED, region[1]);
		rolls[2] = new Animation(ANIMATION_SPEED, region[0]);
		rolls[3] = new Animation(ANIMATION_SPEED, region[3]);
		rolls[4] = new Animation(ANIMATION_SPEED, region[4]);
		
		scoreFont = new BitmapFont();
	}

	@Override
	public void show() {
		img1 = new Texture("playerShip1_green.png");
		img2 = new Texture("purple.png");

		img5 = new Texture("blank.png");
		y1 = 0;
		y2 = y1 + Gdx.graphics.getHeight() ;
	}

	@Override
	public void render(float delta) {
		// creat enemy
		random = new Random();
		enemyWaitTime = random.nextFloat() * (MAX_ENEMY_TIME - MIN_ENEMY_TIME) + MIN_ENEMY_TIME;
		enemyReloadTime += delta;

		if(enemyReloadTime > enemyWaitTime) {
			enemyX = (int) (Math.random() * SpaceGame.WIDTH) - Enemy.WIDTH;
			enemies.add(new Enemy(enemyX));

			enemyReloadTime = 0;
		}

		// quick quit
		if(Gdx.input.isKeyPressed(Keys.Q)) {
			this.dispose();
			game.setScreen(new GameOverScreen(game, score));
		}
		
		// creat bullet

		if(Gdx.input.isKeyJustPressed(Keys.A) && score <= 1000) {
			bullets.add(new Bullet(x+WIDTH/2-5));
			bullets.add(new Bullet(x+WIDTH/2+4));

		}
		//if score > 1000
		else if(Gdx.input.isKeyJustPressed(Keys.A) && score <= 10000) {
			bullets.add(new Bullet(x+WIDTH/2-5));
			bullets.add(new Bullet(x+WIDTH/2+4));
			bullets.add(new Bullet(x+WIDTH/2-9));
			bullets.add(new Bullet(x+WIDTH/2+8));

		}
		//if score > 10000
		else if(Gdx.input.isKeyJustPressed(Keys.A) && score > 10000) {
			bullets.add(new Bullet(x+WIDTH/2-5));
			bullets.add(new Bullet(x+WIDTH/2+4));
			bullets.add(new Bullet(x+WIDTH/2-9));
			bullets.add(new Bullet(x+WIDTH/2+8));
			bullets.add(new Bullet(x+WIDTH/2-13));
			bullets.add(new Bullet(x+WIDTH/2+12));
		}


		//update ship
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED;
			//update roll why just click
			if(Gdx.input.isKeyJustPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) && roll > 0) {
				rollTimer =0;
				roll--;
			}
			
			//update roll
			rollTimer -= delta;
			if(rollTimer < -ROLL_TIMER_SWITCH_TIME && roll >0) {
				rollTimer = 0;
				roll--;
				if(roll<0) 
					roll =0;
			}
		}
		else {
			if(roll < 2) {
				rollTimer += delta;
				if(rollTimer > ROLL_TIMER_SWITCH_TIME) {
					rollTimer = 0;
					roll++;
					if(roll>4) 
						roll =4;
				}
			}
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED;
			// update roll why just click
			if(Gdx.input.isKeyJustPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT) && roll<4) {
				rollTimer = 0;
				roll++;
			}
			
			// update roll
			rollTimer += delta;
			if(rollTimer > ROLL_TIMER_SWITCH_TIME && roll <4) {
				rollTimer = 0;
				roll++;
				if(roll>4) 
					roll =4;
			}
		}
		else {
			if(roll >2) {
				rollTimer -= delta;
				if(rollTimer < -ROLL_TIMER_SWITCH_TIME) {
					rollTimer = 0;
					roll--;
					if(roll<0) 
						roll =0;
				}
			}
		}

		if(x<0) x=0;
		if(x> SpaceGame.WIDTH - WIDTH) x = SpaceGame.WIDTH - WIDTH;

		// check collision player and enemy
		ArrayList<Enemy> removeEnemy = new ArrayList<Enemy>();
		player.move(x, DEFAULT_Y);

		for(Enemy enemy: enemies) {
			if(enemy.getCheckCollision().checkCollision(player)) {
				removeEnemy.add(enemy);
				health -= 0.1f;
			}
		}

		// check collision bullet and enemy
		ArrayList<Bullet> removeBullet = new ArrayList<Bullet>();

		for(Enemy enemy :enemies ) {
			for( Bullet bullet:bullets ) {

				if(bullet.getCheckCollision().checkCollision(enemy.getCheckCollision())) {
					removeBullet.add(bullet);
					removeEnemy.add(enemy);
					score += 100;
					scoreToEnemyShip += 1;
					collisions.add(new Collision(enemy.x, enemy.y));
				}
			}
		}
		bullets.removeAll(removeBullet);
		enemies.removeAll(removeEnemy);
		
		//creat enemy ship
		if(scoreToEnemyShip > 10) {
			scoreToEnemyShip = 0;
			enemyShips.add(new EnemyShip(x));
		}

		//update collision
		ArrayList<Collision> removeCollision = new ArrayList<Collision>();
		for (Collision collision: collisions) {
			collision.update(delta);
			if(collision.remove) {
				removeCollision.add(collision);
			}
		}
		collisions.removeAll(removeCollision);

		// game over
		if(score <0 ) {
			this.dispose();
			game.setScreen(new GameOverScreen(game, score));
		}

		stateTime += delta;
		
		//update y1 y2 background
		y1 -= delta * BACKGROUND_SPEED;
		y2 -= delta * BACKGROUND_SPEED;
		
		if(y1 + Gdx.graphics.getHeight() < 0) y1 = y2 + Gdx.graphics.getHeight();
		if(y2 + Gdx.graphics.getHeight() < 0) y2 = y1 + Gdx.graphics.getHeight() ;

		//start batch
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		// draw cover
		game.batch.draw(img2, 0, y1, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(img2, 0, y2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//scrollingBackground.updateAndRender(delta, game.batch);
		
		// draw score
		GlyphLayout scoreLayout = new GlyphLayout(scoreFont, ""+score, Color.WHITE, 0, Align.center, false);
		scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-15);
		
		//draw enemy ship
		for(EnemyShip enemyShip: enemyShips) {
			enemyShip.updateAndRender(game.batch, delta);
		}

		//draw enemies
		for(Enemy enemy: enemies) {
			enemy.render(game.batch);
		}

		//draw ship
		game.batch.draw(rolls[roll].getKeyFrame(stateTime, true), x, DEFAULT_Y, WIDTH, HEIGHT);

		//update and draw bullet
		for(Bullet bullet: bullets) {
			bullet.render(game.batch);
		}

		//draw collision
		for(Collision collision: collisions) {
			collision.render(game.batch);
		}

		//draw health
		if(health > 0.6f)
			game.batch.setColor(Color.GREEN);
		else if(health >0.3f)
			game.batch.setColor(Color.ORANGE);
		else if(health >0)
			game.batch.setColor(Color.RED);
		else {
			//gameover
			this.dispose();
			game.setScreen(new GameOverScreen(game, score));
		}
		game.batch.draw(img5, 0, 5, Gdx.graphics.getWidth() * health, 5);

		game.batch.setColor(Color.WHITE);
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
