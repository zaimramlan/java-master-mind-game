package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GUI.Custom.*;
import GUI.Assets.*;

/*
 * This is the Menu screen design
 */

public class Menu extends JPanel  implements ActionListener {
	public static Menu gui = new Menu();
	public static int mode = 0;
	private final int SINGLEPLAYER = 1;
	private final int MULTIPLAYER = 2;
	private final String TITLE = "Mastermind";
	private final int MODES = 2;
	private Labels titleText;
	private NavButton[] modes = new NavButton[MODES];
	private NavButton instructionsbtn = new NavButton("How To Play");
	private NavButton exitbtn = new NavButton("Exit Game");

	public JPanel showUI() {

		removeAll(); //removes previous components stored in the panel
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gb);
		setBackground(Colours.Midnight);

		/* Title Section */
		JPanel textSection = new JPanel();
		titleText = new Labels(TITLE, Fonts.Library.OpenSans("Light", 65), Colours.Clouds);
		textSection.setBackground(Colours.Midnight);
		textSection.add(titleText);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addComponent(textSection, gb, c);

		/* Mockup Guess Buttons Section */
		JPanel mockupSection = new JPanel();
		GuessButton[] guess = new GuessButton[4];
		for (int i=0; i<4; i++) {
			guess[i] = new GuessButton("0");
			guess[i].changeColourTo(Colours.collections[i]);
			mockupSection.add(guess[i]);
		}
		mockupSection.setBackground(Colours.Midnight);
		addComponent(mockupSection, gb, c);

		/* Buttons Section */
		JPanel buttonsSection = new JPanel();
		String[] modeLabels = {"Singleplayer", "Multiplayer"};
		buttonsSection.setLayout(new BoxLayout(buttonsSection, BoxLayout.Y_AXIS));
		buttonsSection.setBackground(Colours.Midnight);
		buttonsSection.add(Box.createRigidArea(new Dimension(0,25)));
		for (int i=0; i<MODES; i++) {
			modes[i] = new NavButton(modeLabels[i]);
			modes[i].setButtonColour(Colours.Asbestos);
			modes[i].addActionListener(this);
			buttonsSection.add(modes[i]);
			buttonsSection.add(Box.createRigidArea(new Dimension(0,15)));
		}
		instructionsbtn.setButtonColour(Colours.Asbestos);
		instructionsbtn.addActionListener(this);
		buttonsSection.add(instructionsbtn);
		buttonsSection.add(Box.createRigidArea(new Dimension(0,15)));
		exitbtn.setButtonColour(Colours.Clouds);
		exitbtn.setFontColour(Colours.Asbestos);
		exitbtn.addActionListener(this);		
		buttonsSection.add(exitbtn);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addComponent(buttonsSection, gb, c);	

		return this;
	}

	/*
	 * GridBag Layout repeats the same lines just to add a component into a panel. This
	 * method simplifies the process.
	 */
	public void addComponent(Component component, GridBagLayout gb, GridBagConstraints c) {
		gb.setConstraints(component, c);
		add(component);
	}	

	/* Actions triggered by events done on the corresponding components */
	public void actionPerformed(ActionEvent e) {
		/* sets current game mode and goes to corresponding mode screen */
		if(e.getSource() == modes[0]) {
			mode = SINGLEPLAYER;
			Main.frame.setContentPane(Singleplayer.gui.showUI());
		} else if(e.getSource() == modes[1]) {
			mode = MULTIPLAYER;
			Main.frame.setContentPane(Multiplayer.gui.showUI());
		} else if(e.getSource() == instructionsbtn){
			Main.frame.setContentPane(Instructions.gui.showUI());
			Instructions.gui.showUI().validate();
		} else {
			System.exit(1);
		}
		Main.frame.repaint();

		Main.frame.setVisible(true);
	}
}