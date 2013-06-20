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
package main.java.bg.fmi.command;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.java.bg.fmi.hanoi.Game;

public class ExitGameCommand extends JButton implements ICommand {

    private static final long serialVersionUID = 1L;

    public ExitGameCommand(String caption) {
        super(caption);
    }

    @Override
    public void execute() {
        try {
            Game.storeCurrentGame();
            System.exit(0);
        } catch (NullPointerException exception) {
            JOptionPane.showMessageDialog(null, "To save game first you should start one.");
        }
    }
}
