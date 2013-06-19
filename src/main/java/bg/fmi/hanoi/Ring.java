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

public class Ring extends Graphic {
    private final int ringSize;

    private int currRing;

    private int currStick;

    public Ring(int size, int stick, int ring) {
        ringSize = size;
        currStick = stick;
        currRing = ring;
    }

    public void draw(Graphics ringGraphic) {
        // Draw Ring
        ringGraphic.setColor(Color.black);
        // outline
        ringGraphic.drawRect(((currStick) * 100) - (12 * (ringSize + 1) / 2) + 100, 280 - ((currRing) * 20),
            12 * (ringSize + 1), 20);
        ringGraphic.setColor(Color.blue);
        ringGraphic.fillRect(((currStick) * 100) - (12 * (ringSize + 1) / 2) + 101, 280 - ((currRing) * 20) + 1,
            12 * (ringSize + 1) - 1, 19);

        super.draw(ringGraphic);
    }

    public void updatePosition(int stick, int ring) {
        currStick = stick;
        currRing = ring;
    }

    public int getRingSize() {
        return ringSize;
    }
}
