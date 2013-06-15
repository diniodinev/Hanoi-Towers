package main.java.bg.fmi.hanoi;

import javax.swing.JButton;

public class ExitGameCommand extends JButton implements ICommand {

	private static final long serialVersionUID = 1L;

	public ExitGameCommand(String caption) {
		super(caption);
	}

	@Override
	public void Execute() {
		Game.StoreCurrentGame();
		System.exit(0);
	}

}
