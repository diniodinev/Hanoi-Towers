package main.java.bg.fmi.hanoi;

import java.awt.Color;
import java.awt.Graphics;

public class Stick extends Graphic {
	private int currStick;

	public Stick(int stick) {
		currStick = stick;
	}

	public void draw(Graphics g) {
		// Draw Stick
		g.setColor(Color.black);
		// outline
		g.drawRect((currStick) * 100 + 95, 100, 10, 200);
		g.fillRect((currStick) * 100 + 96, 101, 9, 199);

		super.draw(g);
	}
}
