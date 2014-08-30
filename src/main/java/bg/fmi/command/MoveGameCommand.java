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
package bg.fmi.command;

import javax.swing.JButton;

import bg.fmi.hanoi.Game;

public class MoveGameCommand extends JButton implements ICommand {

    private static final long serialVersionUID = 1L;

    public int mvFrom;

    public int mvTo;

    public MoveGameCommand(String caption) {
        super(caption);
    }

    @Override
    public void execute() {
        Game.move(mvFrom, mvTo);
    }

}
