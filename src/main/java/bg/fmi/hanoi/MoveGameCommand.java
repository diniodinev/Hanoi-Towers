package main.java.bg.fmi.hanoi;

import javax.swing.JButton;

public class MoveGameCommand extends JButton implements ICommand {

    private static final long serialVersionUID = 1L;

    public int mvFrom;

    public int mvTo;

    public MoveGameCommand(String caption) {
        super(caption);
    }

    @Override
    public void execute() {
        Game.move(mvFrom, mvTo);
    }

}
