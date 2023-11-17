package ca.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sujeira extends Obstaculo{

	private Image sprite;
	
	public Sujeira(int x, int y) {
		super(x, y);
		try {
			switch((int)(Math.random()*3)) {
				case 0:
					sprite = ImageIO.read(this.getClass().getResource("/ca/images/cd.png"));
					break;
				case 1:
					sprite = ImageIO.read(this.getClass().getResource("/ca/images/pilha.png"));
					break;
				case 2:
					sprite = ImageIO.read(this.getClass().getResource("/ca/images/telefone.png"));
					break;
				default:
					sprite = ImageIO.read(this.getClass().getResource("/ca/images/telefone.png"));
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.isSuj = true;
	}

	public void render(Graphics2D g) {
		g.drawImage(sprite,x,y,w,h,null);
	}
}
