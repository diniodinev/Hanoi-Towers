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
import java.awt.Graphics;

public class Stick extends Graphic {
    private final int currStick;

    public Stick(int stick) {
        currStick = stick;
    }

    public void draw(Graphics stickGraphic) {
        // Draw Stick
        stickGraphic.setColor(Color.black);
        // outline
        stickGraphic.drawRect((currStick) * 100 + 95, 100, 10, 200);
        stickGraphic.fillRect((currStick) * 100 + 96, 101, 9, 199);

        super.draw(stickGraphic);
    }
}
