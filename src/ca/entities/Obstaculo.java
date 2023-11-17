package ca.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Obstaculo {
	
	protected int x, y, w = 40, h = 40;
	protected boolean collided = false;
	protected boolean isSuj;
	
	public Obstaculo(int x, int y) {
		this.x = x;
		this.y = y;
		this.isSuj = false;
	}
	
	public void fall(int s) {
		y += s;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return w;
	}
	
	public boolean isSuj() {
		return isSuj;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(x, y, w, h);
	}

	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}
}
