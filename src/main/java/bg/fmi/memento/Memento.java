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
package main.java.bg.fmi.memento;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import main.java.bg.fmi.builder.GraphicDirector;
import main.java.bg.fmi.builder.ResumeBuilder;
import main.java.bg.fmi.composite.Ring;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Memento {
    private GameState memState;

    private static final String filename = "hanoigame.xml";

    public Memento() {
        memState = null;
    }

    public void setState(GameState newState) {
        if (newState == null) {
            return;
        }
        memState = newState;

        // create xml file
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("game");
            doc.appendChild(rootElement);

            // staff elements
            Element nameEl = doc.createElement("playerName");
            rootElement.appendChild(nameEl);
            nameEl.setTextContent(newState.getPlayerName());

            Element movesEl = doc.createElement("moves");
            rootElement.appendChild(movesEl);
            movesEl.setTextContent(Integer.toString(newState.getMoves()));

            // Add elemets for the Hanoi Sticks
            for (int stId = 0; stId < 3; stId++) {
                if (newState.getGameDrawing().getChild(stId) != null) {
                    Element stickEl = doc.createElement("stick");
                    rootElement.appendChild(stickEl);

                    for (int i = 0; i <= newState.getGameDrawing().getChild(stId).topChildId(); i++) {
                        Element ringEl = doc.createElement("ring");
                        stickEl.appendChild(ringEl);
                        Ring ring = (Ring) newState.getGameDrawing().getChild(stId).getChild(i);
                        ringEl.setTextContent(Integer.toString(ring.getRingSize()));
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transfFactory = TransformerFactory.newInstance();
            Transformer transformer = transfFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public GameState getState() {
        if (memState == null) {
            ResumeBuilder gBuilder = new ResumeBuilder(filename);
            GraphicDirector gDirector = new GraphicDirector(gBuilder);
            gDirector.constructGraphic();
            memState = new GameState();
            memState.setGameDrawing(gBuilder.getGraphic());
            memState.setPlayerName(gBuilder.getPlayerName());
            memState.setMoves(gBuilder.getMoves());
            return memState;
        } else {
            return memState;
        }
    }
}
