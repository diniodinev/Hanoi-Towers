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
package bg.fmi.composite;

import java.awt.Graphics;
import java.util.Vector;
//Structoral pattern
public class Graphic {
    private final Vector<Graphic> children;

    public Graphic() {
        children = new Vector<Graphic>();
    }

    public void draw(Graphics graphics) {
        for (int i = 0; i < children.size(); i++) {
            children.elementAt(i).draw(graphics);
        }
    }

    public void addChild(Graphic newChild) {
        children.add(newChild);
    }

    public Graphic getChild(int childNumber) {
        return children.get(childNumber);
    }

    public int topChildId() {
        return children.size() - 1;
    }

    public Graphic removeTopChild() {
        return children.remove(children.size() - 1);
    }
}
