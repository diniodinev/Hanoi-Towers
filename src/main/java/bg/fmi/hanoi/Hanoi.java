package main.java.bg.fmi.hanoi;

import java.awt.*;
import javax.swing.*;

public class Hanoi extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Hanoi();
	}

	public Hanoi() {
		super("Tower of Hanoi");

		Container content = getContentPane();
		content.setBackground(Color.lightGray);
		ControlView controlArea = new ControlView();
		content.add(controlArea, BorderLayout.WEST);
		DrawingView drawingArea = new DrawingView();
		content.add(drawingArea, BorderLayout.EAST);

		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
}
