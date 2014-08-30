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
package bg.fmi.builder;

import bg.fmi.composite.Graphic;
import bg.fmi.composite.Ring;
import bg.fmi.composite.Stick;

public class DefaultBuilder implements IGraphicBuilder {

    private Graphic myGraphic = null;

    @Override
    public void addSticks() {
        myGraphic = new Graphic();
        for (int i = 0; i < 3; i++) {
            Stick newStick = new Stick(i);
            myGraphic.addChild(newStick);
        }
    }

    @Override
    public void addRings() {
        Graphic myFirstStick = myGraphic.getChild(0);
        for (int i = 0; i < 5; i++) {
            Ring newRing = new Ring(5 - i, 0, i);
            myFirstStick.addChild(newRing);
        }
    }

    @Override
    public Graphic getGraphic() {
        // TODO Auto-generated method stub
        return myGraphic;
    }

}
