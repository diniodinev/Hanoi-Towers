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
package bg.fmi.memento;

import bg.fmi.composite.Graphic;

public class GameState {
    // The Graphic object for the drawing the game
    private Graphic gameDrawing;

    // The number of passed moves
    private int moves;

    private String playerName;

    public GameState() {
        gameDrawing = null;
        moves = 0;
        playerName = "";
    }

    /**
     * Set method for the playerName
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return the graphics Object
     */
    public Graphic getGameDrawing() {
        return gameDrawing;
    }

    public void setGameDrawing(Graphic gameDrawing) {
        this.gameDrawing = gameDrawing;
    }

    /**
     * @return moves for the game
     */
    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

}
