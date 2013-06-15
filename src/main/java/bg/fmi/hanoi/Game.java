package main.java.bg.fmi.hanoi;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Game {
	private static GameState gameInfo = null;
	private static JPanel myDrawPanel = null;
	private static JPanel myControlPanel = null;

	public static String GetPlayerName() {
		if ((null != gameInfo) && (null != gameInfo.playerName)) {
			return gameInfo.playerName;
		} else {
			return "Player 1";
		}
	}

	public static int GetMoves() {
		if (null != gameInfo) {
			return gameInfo.moves;
		} else {
			return 0;
		}
	}

	public static void UpdateDrawView(Graphics g, JPanel myPanel) {
		myDrawPanel = myPanel;
		if ((null != gameInfo) && (null != gameInfo.gameDrawing)) {
			gameInfo.gameDrawing.draw(g);
		}
	}

	public static void UpdateControlView(Graphics g, JPanel myPanel) {
		myControlPanel = myPanel;
	}

	public static void StartNewGame() {
		gameInfo = new GameState();
		IGraphicBuilder gBuilder = new DefaultBuilder();
		GraphicDirector gDirector = new GraphicDirector(gBuilder);
		gDirector.ConstructGraphic();
		gameInfo.gameDrawing = gBuilder.GetGraphic();
		if (null != myDrawPanel) {
			myDrawPanel.repaint();
		}
		if (null != myControlPanel) {
			myControlPanel.repaint();
		}
	}

	public static boolean Move(int from, int to) {
		if ((null != gameInfo) && (null != gameInfo.gameDrawing)) {
			Stick fromStick = (Stick) gameInfo.gameDrawing.GetChild(from);
			Stick toStick = (Stick) gameInfo.gameDrawing.GetChild(to);

			int fromRingId = fromStick.TopChildId();
			if (0 <= fromRingId) {
				Ring fromRing = (Ring) fromStick.GetChild(fromRingId);
				int toRingId = toStick.TopChildId();
				if (0 <= toRingId) {
					Ring toRing = (Ring) toStick.GetChild(toRingId);
					int fromRingSize = fromRing.GetRingSize();
					int toRingSize = toRing.GetRingSize();

					if (fromRingSize < toRingSize) {
						Ring movingRing = (Ring) fromStick.RemoveTopChild();
						movingRing.updatePosition(to, toRingId + 1);
						toStick.AddChild(movingRing);

						gameInfo.moves++;

						if (null != myDrawPanel) {
							myDrawPanel.repaint();
						}
						if (null != myControlPanel) {
							myControlPanel.repaint();
						}
						return true;
					}
				} else {
					Ring movingRing = (Ring) fromStick.RemoveTopChild();
					movingRing.updatePosition(to, 0);
					toStick.AddChild(movingRing);

					gameInfo.moves++;

					if (null != myDrawPanel) {
						myDrawPanel.repaint();
					}
					if (null != myControlPanel) {
						myControlPanel.repaint();
					}
					return true;
				}
			}
		}

		return false;
	}

	public static void StartLastGame() {
		Memento newMemento = new Memento();
		gameInfo = newMemento.GetState();
		if (null != myDrawPanel) {
			myDrawPanel.repaint();
		}
		if (null != myControlPanel) {
			myControlPanel.repaint();
		}
	}

	public static void StoreCurrentGame() {
		Memento newMemento = new Memento();
		newMemento.SetState(gameInfo);
	}
}
