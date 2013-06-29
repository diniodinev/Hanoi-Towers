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

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.bg.fmi.builder.DefaultBuilder;
import main.java.bg.fmi.builder.GraphicDirector;
import main.java.bg.fmi.builder.IGraphicBuilder;
import main.java.bg.fmi.composite.Graphic;
import main.java.bg.fmi.composite.Ring;
import main.java.bg.fmi.composite.Stick;
import main.java.bg.fmi.memento.GameState;
import main.java.bg.fmi.memento.Memento;

public class Game {
    private static GameState gameInfo = null;

    private static JPanel myDrawPanel = null;

    private static JPanel myControlPanel = null;

    public static String getPlayerName() {
        if ((gameInfo == null) || (gameInfo.getPlayerName() == null)) {
            return "Player 1";
        } else {
            return gameInfo.getPlayerName();

        }
    }

    public static void setPlayerName(String name) {
        gameInfo.setPlayerName(name);
    }

    public static int getMoves() {
        if (null == gameInfo) {
            return 0;
        } else {
            return gameInfo.getMoves();
        }

    }

    public static void updateDrawView(Graphics graphics, JPanel myPanel) {
        myDrawPanel = myPanel;
        if ((null != gameInfo) && (null != gameInfo.getGameDrawing())) {
            gameInfo.getGameDrawing().draw(graphics);
        }
    }

    public static void updateControlView(Graphics graphics, JPanel myPanel) {
        myControlPanel = myPanel;
    }

    public static void startNewGame() {
        gameInfo = new GameState();
        String inputName = Game.takeInputPlayerName();
        if (inputName == null) {
            return;
        } else {
            gameInfo.setPlayerName(inputName);
        }
        IGraphicBuilder gBuilder = new DefaultBuilder();
        GraphicDirector gDirector = new GraphicDirector(gBuilder);
        gDirector.constructGraphic();
        gameInfo.setGameDrawing(gBuilder.getGraphic());
        if (null != myDrawPanel) {
            myDrawPanel.repaint();
        }
        if (null != myControlPanel) {
            myControlPanel.repaint();
        }
    }

    public static boolean move(int from, int to) {
        if ((gameInfo != null) && (gameInfo.getGameDrawing() != null)) {
            Stick fromStick = (Stick) gameInfo.getGameDrawing().getChild(from);
            Stick toStick = (Stick) gameInfo.getGameDrawing().getChild(to);

            int fromRingId = fromStick.topChildId();
            if (fromRingId >= 0) {
                Ring fromRing = (Ring) fromStick.getChild(fromRingId);
                int toRingId = toStick.topChildId();
                // If want to move on a stick with 1 or more rings
                if (toRingId >= 0) {
                    Ring toRing = (Ring) toStick.getChild(toRingId);
                    int fromRingSize = fromRing.getRingSize();
                    int toRingSize = toRing.getRingSize();

                    if (fromRingSize < toRingSize) {
                        Ring movingRing = (Ring) fromStick.removeTopChild();
                        movingRing.updatePosition(to, toRingId + 1);
                        toStick.addChild(movingRing);

                        gameInfo.setMoves(gameInfo.getMoves() + 1);

                        if (null != myDrawPanel) {
                            myDrawPanel.repaint();
                        }
                        if (null != myControlPanel) {
                            myControlPanel.repaint();
                        }
                        //Check if the player wins - if moves are >=31 and all rings are int las stick
                        if (isGameCompleted()) {
                            JOptionPane.showMessageDialog(null, "You win");
                        }
                    }
                    // If want to move to empty stick
                } else {
                    Ring movingRing = (Ring) fromStick.removeTopChild();
                    movingRing.updatePosition(to, 0);
                    toStick.addChild(movingRing);

                    gameInfo.setMoves(gameInfo.getMoves() + 1);

                    if (myDrawPanel != null) {
                        myDrawPanel.repaint();
                    }
                    if (myControlPanel != null) {
                        myControlPanel.repaint();
                    }
                    return true;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "To start moves, please start new game or load saved one.");
        }

        return false;
    }

    public static void startLastGame() {
        Memento newMemento = new Memento();
        gameInfo = newMemento.getState();
        if (myDrawPanel != null) {
            myDrawPanel.repaint();
        }
        if (myControlPanel != null) {
            myControlPanel.repaint();
        }
    }

    public static void storeCurrentGame() {
        Memento newMemento = new Memento();
        newMemento.setState(gameInfo);
    }

    /*
     * Before start a game, want from user to write its name and check if the name is not empty. If user cancell or
     * close the input dialog, return null
     * 
     * @return the input from the user - which is the name of the player or null if the input dialog is beeing closed
     */
    public static String takeInputPlayerName() {
        String userInput = null;
        if (gameInfo == null || (gameInfo.getPlayerName() == null) || gameInfo.getPlayerName().trim().equals("")) {
            userInput = JOptionPane.showInputDialog(null, "Please enter player's name to start the game");
            if (userInput == null) {
                return null;
            }
            while (userInput.trim().equals("")) {
                userInput = JOptionPane.showInputDialog(null, "Please enter a valid player's name to start the game");
            }
        }
        return userInput;
    }

    private static boolean isGameCompleted() {
        if (gameInfo.getMoves() < 31 || gameInfo.getGameDrawing().getChild(2).topChildId() != 4) {
            return false;
        }
        return true;
    }

    public static GameState getGameInfo() {
        return gameInfo;
    }
    
}
