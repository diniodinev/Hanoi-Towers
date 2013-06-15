package main.java.bg.fmi.hanoi;

import javax.swing.JButton;

public class LastGameCommand extends JButton implements ICommand {

	private static final long serialVersionUID = 1L;

	public LastGameCommand(String caption) {
		super(caption);
	}

	@Override
	public void Execute() {
		Game.StartLastGame();
	}

}
