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
package bg.fmi.hanoi;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bg.fmi.command.ExitGameCommand;
import bg.fmi.command.ICommand;
import bg.fmi.command.LastGameCommand;
import bg.fmi.command.MoveGameCommand;
import bg.fmi.command.NewGameCommand;

public class ControlView extends JPanel implements ActionListener {

    private static final long serialVersionUID = 234523452345245L;

   // private final JComboBox<String> moveComboBox;

    JLabel lblPlayer;

    JLabel lblMoves;
    
    public ControlView() {
        super(new GridLayout(5, 1));
        setPreferredSize(new Dimension(200, 400));
        lblPlayer = new JLabel("Player's name: ");
        add(lblPlayer);
        lblMoves = new JLabel("Moves: ");
        add(lblMoves);
     
        NewGameCommand btnNew = new NewGameCommand("New");
        btnNew.addActionListener(this);
        add(btnNew);
        LastGameCommand btnLast = new LastGameCommand("Last");
        btnLast.addActionListener(this);
        add(btnLast);
        ExitGameCommand btnSave = new ExitGameCommand("Save and Exit");
        btnSave.addActionListener(this);
        add(btnSave);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ICommand cmd = (ICommand) event.getSource();
     
        cmd.execute();
    }

    public void paint(Graphics view) {
        super.paint(view);
        lblPlayer.setText("Player's name: " + Game.getPlayerName());
        lblMoves.setText("Moves: " + Game.getMoves());
        Game.updateControlView(view, this);
    }
}
