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

import java.awt.Graphics;

import javax.swing.JPanel;

public class Game {
    private static GameState gameInfo = null;

    private static JPanel myDrawPanel = null;

    private static JPanel myControlPanel = null;

    public static String getPlayerName() {
        if ((gameInfo == null) || (gameInfo.playerName == null)) {
            return "Player 1";
        } else {
            return gameInfo.playerName;

        }
    }

    public static int getMoves() {
        if (null == gameInfo) {
            return 0;
        } else {
            return gameInfo.moves;
        }

    }

    public static void updateDrawView(Graphics g, JPanel myPanel) {
        myDrawPanel = myPanel;
        if ((null != gameInfo) && (null != gameInfo.gameDrawing)) {
            gameInfo.gameDrawing.draw(g);
        }
    }

    public static void updateControlView(Graphics g, JPanel myPanel) {
        myControlPanel = myPanel;
    }

    public static void startNewGame() {
        gameInfo = new GameState();
        IGraphicBuilder gBuilder = new DefaultBuilder();
        GraphicDirector gDirector = new GraphicDirector(gBuilder);
        gDirector.constructGraphic();
        gameInfo.gameDrawing = gBuilder.getGraphic();
        if (null != myDrawPanel) {
            myDrawPanel.repaint();
        }
        if (null != myControlPanel) {
            myControlPanel.repaint();
        }
    }

    public static boolean move(int from, int to) {
        if ((null != gameInfo) && (null != gameInfo.gameDrawing)) {
            Stick fromStick = (Stick) gameInfo.gameDrawing.getChild(from);
            Stick toStick = (Stick) gameInfo.gameDrawing.getChild(to);

            int fromRingId = fromStick.topChildId();
            if (fromRingId >= 0) {
                Ring fromRing = (Ring) fromStick.getChild(fromRingId);
                int toRingId = toStick.topChildId();
                if (toRingId >= 0) {
                    Ring toRing = (Ring) toStick.getChild(toRingId);
                    int fromRingSize = fromRing.getRingSize();
                    int toRingSize = toRing.getRingSize();

                    if (fromRingSize < toRingSize) {
                        Ring movingRing = (Ring) fromStick.removeTopChild();
                        movingRing.updatePosition(to, toRingId + 1);
                        toStick.addChild(movingRing);

                        gameInfo.moves++;

                        if (null != myDrawPanel) {
                            myDrawPanel.repaint();
                        }
                        if (null != myControlPanel) {
                            myControlPanel.repaint();
                        }
                        return true;
                    }
                } else {
                    Ring movingRing = (Ring) fromStick.removeTopChild();
                    movingRing.updatePosition(to, 0);
                    toStick.addChild(movingRing);

                    gameInfo.moves++;

                    if (null != myDrawPanel) {
                        myDrawPanel.repaint();
                    }
                    if (null != myControlPanel) {
                        myControlPanel.repaint();
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public static void startLastGame() {
        Memento newMemento = new Memento();
        gameInfo = newMemento.getState();
        if (null != myDrawPanel) {
            myDrawPanel.repaint();
        }
        if (null != myControlPanel) {
            myControlPanel.repaint();
        }
    }

    public static void storeCurrentGame() {
        Memento newMemento = new Memento();
        newMemento.setState(gameInfo);
    }
}
