package com.mygdx.game.tools;

public class CheckCollision {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public CheckCollision(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
