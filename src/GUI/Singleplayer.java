package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GUI.Custom.*;
import GUI.Assets.*;
import Game.*;

/*
 * This is the Singleplayer screen design
 */

public class Singleplayer extends JPanel implements ActionListener {
	public static Singleplayer gui = new Singleplayer(); //create 1 instance of Menu that can be used throughout the program
	public static Codebreaker codebreaker = new Codebreaker();
	public static Computer computer = new Computer();		
	private final String TITLE = "Singleplayer Mode";
	private final int DIFFICULTIES = 3;
	private final int EASY = 4;
	private final int MED = 6;
	private final int HARD = 8;	
	private Labels titleText, logoText;
	private NavButton backbtn;
	private NavButton[] difficulty;

	/* Generates the pattern based on difficulty level */
	private void initializeBackEnd() {
		codebreaker.setDifficulty(Play.gui.difficulty);
		computer.generatePattern(Play.gui.difficulty);
	}

	public JPanel showUI() {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		removeAll(); //removes previous components stored in the panel
		setLayout(gb);
		setBackground(Colours.Midnight);

		/* Logo Section */
		JPanel logoSection = new JPanel();
		logoText = new Labels(TITLE, Fonts.Library.OpenSans("Light", 55), Colours.Clouds);
		titleText = new Labels("Select difficulty", Fonts.Library.OpenSans("Light", 25), Colours.Clouds);
		logoSection.setLayout(new BoxLayout(logoSection, BoxLayout.Y_AXIS));
		logoSection.setBackground(Colours.Midnight);
		logoSection.add(logoText);
		logoSection.add(titleText);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addComponent(logoSection, gb, c);	

		/* Button Section */
		JPanel buttonsSection = new JPanel();
		buttonsSection.setBackground(Colours.Midnight);
		buttonsSection.setLayout(new BoxLayout(buttonsSection, BoxLayout.Y_AXIS)); //creates a panel with a vertical layout
		buttonsSection.add(Box.createRigidArea(new Dimension(0,15))); //creates a spacing in between components
		difficulty = new NavButton[DIFFICULTIES];
		String[] diffText = {"Easy", "Medium", "Hard"};
		for (int i=0; i<DIFFICULTIES; i++) {
			difficulty[i] = new NavButton(diffText[i]);
			difficulty[i].setButtonColour(Colours.River);
			difficulty[i].addActionListener(this);
			buttonsSection.add(difficulty[i]);
			buttonsSection.add(Box.createRigidArea(new Dimension(0,15))); //creates a spacing in between components
		}
		backbtn = new NavButton("Back");
		backbtn.setButtonColour(Colours.Asbestos);
		backbtn.addActionListener(this);
		buttonsSection.add(backbtn);
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
		if(e.getSource() == backbtn) {
			/* return to main screen */
			Main.frame.setContentPane(Menu.gui.showUI());
			Main.frame.revalidate();			
		} else {
			/* sets difficulty, initializes pattern and goes to play screen */
			if(e.getSource() == difficulty[0])
				Play.gui.difficulty = EASY;
			if(e.getSource() == difficulty[1])
				Play.gui.difficulty = MED;
			if(e.getSource() == difficulty[2])
				Play.gui.difficulty = HARD;
			initializeBackEnd();
			Main.frame.setContentPane(Play.gui.showUI());
			Main.frame.revalidate();		
		}
	}
}