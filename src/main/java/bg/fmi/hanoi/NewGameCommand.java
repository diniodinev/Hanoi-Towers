package main.java.bg.fmi.hanoi;

import javax.swing.JButton;

public class NewGameCommand extends JButton implements ICommand {

	private static final long serialVersionUID = 1L;

	public NewGameCommand(String caption) {
		super(caption);
	}

	@Override
	public void Execute() {
		Game.StartNewGame();
	}

}
