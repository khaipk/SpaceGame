package com.mygdx.game.tools;

public class CheckCollision {
	
	private float x;
	private float y;
	private float width;
	private float height;
	
	public CheckCollision(float x2, float defaultY, float width2, float height2) {
		this.x = x2;
		this.y = defaultY;
		this.width = width2;
		this.height = height2;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean checkCollision(CheckCollision rect) {
		return this.x < rect.x + rect.width && this.x + this.width > rect.x &&
				this.y < rect.y + rect.height && this.y + this.height > rect.y ;
	}
	
	
}
