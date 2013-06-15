package main.java.bg.fmi.hanoi;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class ControlView extends JPanel implements ActionListener {

    private static final long serialVersionUID = 234523452345245L;

    private final JComboBox<String> moveComboBox;

    JLabel lblPlayer;

    JLabel lblMoves;

    public ControlView() {
        super(new GridLayout(7, 1));
        setPreferredSize(new Dimension(200, 400));
        lblPlayer = new JLabel("Player: ");
        add(lblPlayer);
        lblMoves = new JLabel("Moves: ");
        add(lblMoves);
        final String movesList[] = { "1->2", "2->1", "1->3", "3->1", "2->3", "3->2" };
        moveComboBox = new JComboBox<String>(movesList);
        add(moveComboBox);
        MoveGameCommand btnMove = new MoveGameCommand("Move");
        btnMove.addActionListener(this);
        add(btnMove);
        NewGameCommand btnNew = new NewGameCommand("New");
        btnNew.addActionListener(this);
        add(btnNew);
        LastGameCommand btnLast = new LastGameCommand("Last");
        btnLast.addActionListener(this);
        add(btnLast);
        ExitGameCommand btnSave = new ExitGameCommand("Save and Exit");
        btnSave.addActionListener(this);
        add(btnSave);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ICommand cmd = (ICommand) arg0.getSource();
        if (cmd instanceof MoveGameCommand) {
            MoveGameCommand mvCmd = (MoveGameCommand) cmd;
            int moveIndex = moveComboBox.getSelectedIndex();
            switch (moveIndex) {
                case 0:
                    mvCmd.mvFrom = 0;
                    mvCmd.mvTo = 1;
                    break;
                case 1:
                    mvCmd.mvFrom = 1;
                    mvCmd.mvTo = 0;
                    break;
                case 2:
                    mvCmd.mvFrom = 0;
                    mvCmd.mvTo = 2;
                    break;
                case 3:
                    mvCmd.mvFrom = 2;
                    mvCmd.mvTo = 0;
                    break;
                case 4:
                    mvCmd.mvFrom = 1;
                    mvCmd.mvTo = 2;
                    break;
                case 5:
                    mvCmd.mvFrom = 2;
                    mvCmd.mvTo = 1;
                    break;
            }
        }
        cmd.execute();
    }

    public void paint(Graphics view) {
        super.paint(view);
        lblPlayer.setText("Player: " + Game.getPlayerName());
        lblMoves.setText("Moves: " + Game.getMoves());
        Game.updateControlView(view, this);
    }
}
