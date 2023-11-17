package ca.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Prota {
	
	private int x,y,h = 40,w = 40, speed = 6;
	private int offset = 16;
	private int vidas = 3, points;
	private boolean game_over;
	
	public Prota(int x, int y) {
		this.x = x;
		this.y = y;
		this.points = 0;
		this.game_over = false;
	}
	
	public void render(Graphics2D g) {
		
		g.setColor(Color.red);
		for(int i = 0; i < vidas; i++) {
			g.fillRect(30*(i+1), 20, 20, 20);
		}
		
		g.setColor(Color.yellow);
		g.fillRect(x,y,h,w);
	}

	public void right() {
		if(x + speed < 400 - w - offset) x += speed;
		else x = 400 - w - offset;
	}

	public void left() {
		if(x - speed > 0) x -= speed;
		else x = 0;
	}

	public void tick() {
		
	}

	public void detectColisionTo(ArrayList<Obstaculo> obstaculos) {
		Rectangle prota = new Rectangle(x,y,w,h);
		for(Obstaculo obs: obstaculos) {
			Rectangle obs_r = new Rectangle(obs.getX(),obs.getY(),obs.getSize(),obs.getSize());
			if(obs_r.intersects(prota) && !obs.isCollided()) {
				obs.setCollided(true);
				if(obs.isSuj()) {
					point();
					obstaculos.remove(obs);
					return;
				}
				else takeDamage();
			}
		}
	}
	
	public boolean isColiding(Sujeira suj) {
		Rectangle prota = new Rectangle(x,y,w,h);
		Rectangle obs_r = new Rectangle(suj.getX(),suj.getY(),suj.getSize(),suj.getSize());
		return obs_r.intersects(prota) && !suj.isCollided();
	}
	
	public void point() {
		points++;
	}
	
	public void takeDamage() {
		this.vidas--;
		if(vidas == 0) {
			game_over = true;
		}
	}

	public int getPoints() {
		return points;
	}

	public boolean isOver() {
		return game_over;
	}
}
