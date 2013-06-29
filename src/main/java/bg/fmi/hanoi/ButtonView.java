package main.java.bg.fmi.hanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.bg.fmi.command.ICommand;
import main.java.bg.fmi.command.MoveGameCommand;

public class ButtonView extends JPanel implements ActionListener{
    private static final long serialVersionUID = 3534646345L;

    private final MoveGameCommand buttonsArray[] = new MoveGameCommand[6];

    private final char buttonText[] = { '<', '>', '<', '>', '<', '>' };

    public ButtonView() {
        super(new GridLayout(1, 6));
        setPreferredSize(new Dimension(400, 40));
        setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        setBackground(Color.white);
        for (int i = 0; i < buttonsArray.length; i++) {
            buttonsArray[i] = new MoveGameCommand(String.valueOf(buttonText[i]));
            buttonsArray[i].setSize(60, 30);
            buttonsArray[i].addActionListener(this);
            buttonsArray[i].setName(String.valueOf(i));
            this.add(buttonsArray[i]);
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        int moveIndex = Integer.parseInt(button.getName());
          MoveGameCommand mvCmd = (MoveGameCommand) button;
            switch (moveIndex) {
                case 0:
                    mvCmd.mvFrom = 0;
                    mvCmd.mvTo = 2;
                    break;
                case 1:
                    mvCmd.mvFrom = 0;
                    mvCmd.mvTo = 1;
                    break;
                case 2:
                    mvCmd.mvFrom = 1;
                    mvCmd.mvTo = 0;
                    break;
                case 3:
                    mvCmd.mvFrom = 1;
                    mvCmd.mvTo = 2;
                    break;
                case 4:
                    mvCmd.mvFrom = 2;
                    mvCmd.mvTo = 1;
                    break;
                case 5:
                    mvCmd.mvFrom = 2;
                    mvCmd.mvTo = 0;
                    break;
            }
            mvCmd.execute();
            
            
        }

    public void paint(Graphics view) {
        super.paint(view);
        Game.updateDrawView(view, this);
    }
}
