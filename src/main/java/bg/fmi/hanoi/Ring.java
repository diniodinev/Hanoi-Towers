package main.java.bg.fmi.hanoi;

import java.awt.Color;
import java.awt.Graphics;

public class Ring extends Graphic {
	private int ringSize;
	private int currRing;
	private int currStick;

	public Ring(int size, int stick, int ring) {
		ringSize = size;
		currStick = stick;
		currRing = ring;
	}

	public void draw(Graphics g) {
		// Draw Ring
		g.setColor(Color.black);
		// outline
		g.drawRect(((currStick) * 100) - (12 * (ringSize + 1) / 2) + 100,
				280 - ((currRing) * 20), 12 * (ringSize + 1), 20);
		g.setColor(Color.blue);
		g.fillRect(((currStick) * 100) - (12 * (ringSize + 1) / 2) + 101,
				280 - ((currRing) * 20) + 1, 12 * (ringSize + 1) - 1, 19);

		super.draw(g);
	}

	public void updatePosition(int stick, int ring) {
		currStick = stick;
		currRing = ring;
	}

	public int GetRingSize() {
		return ringSize;
	}
}
