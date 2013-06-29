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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DrawingView extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage image;

    public DrawingView() {
        super();
        setPreferredSize(new Dimension(400, 360));
        try {
            image = ImageIO.read(new File(System.getProperty("user.dir")+"\\images\\background.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
     //   setBackground(new Backg);
    }

    public void paint(Graphics view) {
       super.paint(view);
       view.drawImage(image, 0,0, null); 
       
       
       Game.updateDrawView(view, this);
    }
}
