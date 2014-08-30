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

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public final class Hanoi extends JFrame {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        new Hanoi();
    }

    private Hanoi() {
        super("Tower of Hanoi");
        JPanel parentPanel = new JPanel();
        parentPanel.setPreferredSize(new Dimension(400, 400));

        // Put JPanels in the conteiner
        Container content = getContentPane();
        content.setBackground(Color.lightGray);

        ControlView controlArea = new ControlView();
        content.add(controlArea, BorderLayout.WEST);

        DrawingView drawingArea = new DrawingView();
        parentPanel.add(drawingArea, BorderLayout.SOUTH);
        ButtonView buttonsArea = new ButtonView();
        parentPanel.add(buttonsArea, BorderLayout.NORTH);

         content.add(parentPanel,BorderLayout.EAST);

        // Save the state of the game before close it with "Close button"
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if(Game.getGameInfo() != null){
                Game.storeCurrentGame();
                }
                System.exit(0);
            }
        });

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}
