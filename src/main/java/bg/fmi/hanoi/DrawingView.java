/*******************************************************************************
 * Copyright (c) 2013 Dinio Dinev.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Dinio Dinev - initial API and implementation
 ******************************************************************************/
package main.java.bg.fmi.hanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingView extends JPanel {

    private static final long serialVersionUID = 1L;

    public DrawingView() {
        super();
        setPreferredSize(new Dimension(400, 400));
        setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        setBackground(Color.white);
    }

    public void paint(Graphics view) {
        super.paint(view);
        Game.updateDrawView(view, this);
    }
}
