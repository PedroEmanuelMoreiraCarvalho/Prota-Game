package ca.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.entities.Estrada;
import ca.entities.Prota;

@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener{
	
	private static final int WIDHT = 400, HEIGHT = 700;
	private boolean r = false, l = false, start = false;
	private static Estrada estrada;
	private static Prota prota;
	private int record = 0;
	
	public static JFrame frame = new JFrame("Prota");

	public void restart() {
		start = false;
		estrada = new Estrada(7);
		prota = new Prota(200,550);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		frame.setSize(WIDHT, HEIGHT); // 8 = disconsidering the opperation bar

		estrada = new Estrada(7);
		prota = new Prota(200,550);
		
		frame.add(game);
		frame.addKeyListener(game);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameLoop(game);
	}
	
	public static void GameLoop(Game game) {
		try {
			synchronized (game) {					
				while(true) {
					game.tick();
					game.repaint();
					game.wait(10);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		if(!start) return;
		if(prota.isOver()) {
			if(prota.getPoints() > record) record = prota.getPoints();
			this.restart();
		}
		
		estrada.tick();
		
		if(l) {
			prota.left();
		}else if(r) {
			prota.right();
		}

		prota.detectColisionTo(estrada.getObstaculos());
		prota.detectColisionTo(estrada.getSujeiras());
	}
	
	@Override
	public void paint(Graphics g) {
	    super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if(!start) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDHT, HEIGHT);
			g2d.setFont(new Font("Courier", Font.BOLD,25));
			g.setColor(Color.yellow);
			g2d.drawString("recorde: "+record, 140, 320);
			g2d.setFont(new Font("Courier", Font.BOLD,15));
			g2d.drawString("Precione espaço para continuar", 80, 600);
			return;
		}
		
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDHT, HEIGHT);

		estrada.render(g2d);
		prota.render(g2d);
		
		g2d.setFont(new Font("Courier", Font.BOLD,25));
		g.setColor(Color.yellow);
		g2d.drawString(String.valueOf(prota.getPoints()), WIDHT-70, 40);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			l = true;
		}else if (keyCode == KeyEvent.VK_RIGHT) {
			r = true;
		}
		if(keyCode == KeyEvent.VK_SPACE && !start) {
			start = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			l = false;
		}else if (keyCode == KeyEvent.VK_RIGHT) {
			r = false;
		}
	}
}
