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
package main.java.bg.fmi.builder;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import main.java.bg.fmi.composite.Graphic;
import main.java.bg.fmi.composite.Ring;
import main.java.bg.fmi.composite.Stick;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResumeBuilder implements IGraphicBuilder {

    private Graphic myGraphic = null;

    private Element ePlayerName;

    private Element eMoves;

    private Element eStick1;

    private Element eStick2;

    private Element eStick3;

    private String getTagValue(Element eElement) {
        return ((Node) eElement.getChildNodes().item(0)).getNodeValue();
    }

    public ResumeBuilder(String filename) {
        String xmlName;
        xmlName = filename;

        try {

            File fXmlFile = new File(xmlName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("playerName");
            ePlayerName = (Element) nList.item(0);
            nList = doc.getElementsByTagName("moves");
            eMoves = (Element) nList.item(0);
            nList = doc.getElementsByTagName("stick");
            eStick1 = (Element) nList.item(0);
            eStick2 = (Element) nList.item(1);
            eStick3 = (Element) nList.item(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

        Graphic myStick1 = myGraphic.getChild(0);
        NodeList nList = eStick1.getElementsByTagName("ring");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Ring newRing = new Ring(Integer.parseInt(getTagValue(eElement)), 0, temp);
                myStick1.addChild(newRing);
            }
        }

        Graphic myStick2 = myGraphic.getChild(1);
        nList = eStick2.getElementsByTagName("ring");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Ring newRing = new Ring(Integer.parseInt(getTagValue(eElement)), 1, temp);
                myStick2.addChild(newRing);
            }
        }

        Graphic myStick3 = myGraphic.getChild(2);
        nList = eStick3.getElementsByTagName("ring");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Ring newRing = new Ring(Integer.parseInt(getTagValue(eElement)), 2, temp);
                myStick3.addChild(newRing);
            }
        }
    }

    @Override
    public Graphic getGraphic() {
        return myGraphic;
    }

    public String getPlayerName() {
        return getTagValue(ePlayerName);
    }

    public int getMoves() {
        return Integer.parseInt(getTagValue(eMoves));
    }

}
