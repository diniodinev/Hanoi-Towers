package main.java.bg.fmi.hanoi;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResumeBuilder implements IGraphicBuilder {

	private Graphic myGraphic = null;
	private String xmlName;
	private Element ePlayerName;
	private Element eMoves;
	private Element eStick1;
	private Element eStick2;
	private Element eStick3;

	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getChildNodes();
		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

	public ResumeBuilder(String filename) {
		xmlName = filename;

		try {

			File fXmlFile = new File(xmlName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
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
			myGraphic.AddChild(newStick);
		}

	}

	@Override
	public void addRings() {

		Graphic myStick1 = myGraphic.GetChild(0);
		NodeList nList = eStick1.getElementsByTagName("ring");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				Ring newRing = new Ring(Integer.parseInt(getTagValue("ring",
						eElement)), 0, temp);
				myStick1.AddChild(newRing);
			}
		}

		Graphic myStick2 = myGraphic.GetChild(1);
		nList = eStick2.getElementsByTagName("ring");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				Ring newRing = new Ring(Integer.parseInt(getTagValue("ring",
						eElement)), 1, temp);
				myStick2.AddChild(newRing);
			}
		}

		Graphic myStick3 = myGraphic.GetChild(2);
		nList = eStick3.getElementsByTagName("ring");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				Ring newRing = new Ring(Integer.parseInt(getTagValue("ring",
						eElement)), 2, temp);
				myStick3.AddChild(newRing);
			}
		}
	}

	@Override
	public Graphic GetGraphic() {
		return myGraphic;
	}

	public String GetPlayerName() {
		return getTagValue("firstname", ePlayerName);
	}

	public int GetMoves() {

		return Integer.parseInt(getTagValue("firstname", eMoves));
	}

}
