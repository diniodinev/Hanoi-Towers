package main.java.bg.fmi.hanoi;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Memento {
	private GameState memState;
	private static String filename = "hanoigame.xml";

	public Memento() {
		memState = null;
	}

	public void SetState(GameState newState) {
		memState = newState;

		// create xml file
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("game");
			doc.appendChild(rootElement);

			// staff elements
			Element nameEl = doc.createElement("playerName");
			rootElement.appendChild(nameEl);
			nameEl.setTextContent(newState.playerName);

			Element movesEl = doc.createElement("moves");
			rootElement.appendChild(movesEl);
			movesEl.setTextContent(Integer.toString(newState.moves));

			for (int stId = 0; stId < 3; stId++) {
				if (null != newState.gameDrawing.GetChild(stId)) {
					Element stickEl = doc.createElement("stick");
					rootElement.appendChild(stickEl);

					for (int i = 0; i <= newState.gameDrawing.GetChild(stId)
							.TopChildId(); i++) {
						Element ringEl = doc.createElement("ring");
						stickEl.appendChild(ringEl);
						Ring ring = (Ring) newState.gameDrawing.GetChild(stId)
								.GetChild(i);
						ringEl.setTextContent(Integer.toString(ring
								.GetRingSize()));
					}
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public GameState GetState() {
		if (null != memState) {
			return memState;
		} else {
			ResumeBuilder gBuilder = new ResumeBuilder(filename);
			GraphicDirector gDirector = new GraphicDirector(gBuilder);
			gDirector.ConstructGraphic();
			memState = new GameState();
			memState.gameDrawing = gBuilder.GetGraphic();
			memState.playerName = gBuilder.GetPlayerName();
			memState.moves = gBuilder.GetMoves();
			return memState;
		}
	}
}
