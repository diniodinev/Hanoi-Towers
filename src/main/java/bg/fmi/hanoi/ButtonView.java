package main.java.bg.fmi.hanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ButtonView extends JPanel {
    private static final long serialVersionUID = 3534646345L;

    public ButtonView() {
        super();
        setPreferredSize(new Dimension(400, 80));
        setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        setBackground(Color.white);
    }

    public void paint(Graphics view) {
        super.paint(view);
        Game.updateDrawView(view, this);
    }
}
