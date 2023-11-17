package ca.entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Estrada {
	
	private int x = 0,y = 0, speed, offset_y;
	private int offset = 16;
	private ArrayList<Obstaculo> obstaculos;
	private ArrayList<Obstaculo> sujeira;
	private int diff = 100, c_dif = 0, count_obstacles = 0;
	private int diff_suj = 100, c_suj = 0;
	private Image map;
	
	public Estrada(int speed) {
		try {
			map = ImageIO.read(this.getClass().getResource("/ca/images/map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.speed = speed;
		this.obstaculos = new ArrayList<Obstaculo>();
		this.sujeira = new ArrayList<Obstaculo>();
		this.offset_y = 0;
	}
	
	public void addObstaculo(int obs) {
		if(obs == 1) obstaculos.add(new Obstaculo((int) (Math.random()*(360 - offset)), y - 20));
	}
	
	public void addSujeira() {
		sujeira.add(new Sujeira((int) (Math.random()*(360 - offset)), y - 20));
	}
	
	public void tick() {
		
		c_dif++;
		c_suj++;
		
		if(c_dif >= diff) {
			addObstaculo(1);
			c_dif = 0;
			diff = (int) (Math.random() * 200 );
			count_obstacles++;
			if(count_obstacles > 10) {
				speed ++;
				count_obstacles = 0;
			}
		}
		
		if(c_suj >= diff_suj) {
			addSujeira();
			c_suj = 0;
		}
		
		for(Obstaculo obs: obstaculos) {
			obs.fall(speed);
			if(obs.getY() > 700) {
				obstaculos.remove(obs);
				break;
			}
		}
		
		for(Obstaculo suj: sujeira) {
			suj.fall(speed);
			if(suj.getY() > 700) {
				sujeira.remove(suj);
				break;
			}
		}
	}
	
	public void render(Graphics2D g) {
		offset_y += speed;
		
		g.drawImage(map,x,y+offset_y,400,700,null);
		g.drawImage(map,x,y-700+offset_y,400,700,null);
		
		if(offset_y > 700) offset_y = 0;
		
		
		for(Obstaculo obs: obstaculos) obs.render(g);
		for(Obstaculo suj: sujeira) suj.render(g);
	}
	
	public ArrayList<Obstaculo> getObstaculos(){
		return obstaculos;
	}
	
	public ArrayList<Obstaculo> getSujeiras() {
		return sujeira;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

}
