package main.java.bg.fmi.hanoi;

import java.awt.Graphics;
import java.util.Vector;

public class Graphic {
	private Vector<Graphic> children;

	public Graphic() {
		children = new Vector<Graphic>();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < children.size(); i++) {
			children.elementAt(i).draw(g);
		}
	}

	public void AddChild(Graphic newChild) {
		children.add(newChild);
	}

	public Graphic GetChild(int i) {
		return children.get(i);
	}

	public int TopChildId() {
		return children.size() - 1;
	}

	public Graphic RemoveTopChild() {
		return children.remove(children.size() - 1);
	}
}
