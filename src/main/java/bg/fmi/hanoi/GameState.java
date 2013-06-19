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

public class GameState {
    public Graphic gameDrawing;

    public int moves;

    public String playerName;

    public GameState() {
        gameDrawing = null;
        moves = 0;
        playerName = "Player 1";
    }
}
