package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import GUI.Custom.*;
import GUI.Assets.*;
import Game.*;
/*
 * This is the Multiplayer screen design
 */

public class Multiplayer extends JPanel implements ActionListener {
	public static Multiplayer gui = new Multiplayer(); //create 1 instance of Menu that can be used throughout the program
	public static Codemaker codemaker = new Codemaker();		
	private final String TITLE = "Multiplayer Mode";
	private final int DIFFICULTIES = 3;
	private final int CODES_LENGTH = 4;
	private final int EASY = 4;
	private final int MED = 6;
	private final int HARD = 8;
	private final int COLOURS_EASY = 0;
	private final int COLOURS_MED = 1;
	private final int COLOURS_HARD = 2;
	private JPanel titleSection, difficultySection, codesSection;
	private Labels titleText, logoText;
	private NavButton playbtn, backbtn;
	private NavButton[] diffbtn;
	private GuessButton[] codes;

	public JPanel showUI() {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		removeAll(); //removes previous components stored in the panel
		setLayout(gb);		
		setBackground(Colours.Midnight);

		/* Logo Section */
		JPanel logoSection = new JPanel();
		logoText = new Labels(TITLE, Fonts.Library.OpenSans("Light", 55), Colours.Clouds);
		titleText = new Labels("Make a pattern for the codebreaker to guess", Fonts.Library.OpenSans("Light", 25), Colours.Clouds);
		logoSection.setLayout(new BoxLayout(logoSection, BoxLayout.Y_AXIS));
		logoSection.setBackground(Colours.Midnight);
		logoSection.add(logoText);
		logoSection.add(titleText);
		logoSection.add(Box.createRigidArea(new Dimension(0,25))); //creates a spacing in between components
		c.gridwidth = GridBagConstraints.REMAINDER;
		addComponent(logoSection, gb, c);			

		/* Difficulty Section */
		difficultySection = new JPanel();
		diffbtn = new NavButton[DIFFICULTIES];
		String[] diffText = {"4 Colours", "6 Colours", "8 Colours"};
		for(int i=0; i<DIFFICULTIES; i++) {
			diffbtn[i] = new NavButton(diffText[i], new Dimension(150, 35));
			diffbtn[i].setButtonColour(Colours.River);
			diffbtn[i].addActionListener(this);
			difficultySection.add(diffbtn[i]);
			difficultySection.add(Box.createRigidArea(new Dimension(10,0)));		
		}
		difficultySection.setBackground(Colours.Midnight);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addComponent(difficultySection, gb, c);				

		/* Spacing Section */		
		add(Box.createRigidArea(new Dimension(0,100)));		

		/* Codes Section */
		codesSection = new JPanel();
		codesSection.setLayout(new BoxLayout(codesSection, BoxLayout.X_AXIS));
		codes = new GuessButton[CODES_LENGTH];
		for(int i=0; i<CODES_LENGTH; i++) {
			codes[i] = new GuessButton("0");
			codes[i].addActionListener(this);
			codesSection.add(codes[i]);
			codesSection.add(Box.createRigidArea(new Dimension(10,0)));		
		}
		codesSection.setBackground(Colours.Midnight);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addComponent(codesSection, gb, c);				

		/* Button Section */
		JPanel buttonsSection = new JPanel();
		buttonsSection.setBackground(Colours.Midnight);
		buttonsSection.setLayout(new BoxLayout(buttonsSection, BoxLayout.Y_AXIS));
		playbtn = new NavButton("Play");
		playbtn.setButtonColour(Colours.Clouds);
		playbtn.setFontColour(Colours.Asbestos);
		playbtn.addActionListener(this);
		buttonsSection.add(playbtn);
		buttonsSection.add(Box.createRigidArea(new Dimension(0,15)));
		backbtn = new NavButton("Back");
		backbtn.setButtonColour(Colours.Asbestos);
		backbtn.addActionListener(this);
		buttonsSection.add(backbtn);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addComponent(buttonsSection, gb, c);				

		return this;	
	}

	/* Resets the colours */
	private void resetCodes() {
		for (int i=0; i<CODES_LENGTH; i++) {
			codes[i].setText("0");
			codes[i].reset();
		}
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
		if(e.getSource() == playbtn) {
			/* 
			 * initializes pattern from the codemaker player and
			 * goes to play screen
			 */
			int[] theCodes = new int[CODES_LENGTH];
			for (int i=0; i<CODES_LENGTH; i++) theCodes[i] = Integer.parseInt(codes[i].getText());
			System.out.println(Arrays.toString(theCodes));
			if(Arrays.toString(theCodes).contains("0")) {
				JOptionPane.showMessageDialog(new JFrame(), "Please set the pattern first!");
			} else {
				codemaker.setPattern(theCodes);
				Main.frame.setContentPane(Play.gui.showUI());
				Main.frame.revalidate();
			}
		} else if(e.getSource() == backbtn) {
			/* returns to Menu screen */
			Main.frame.setContentPane(Menu.gui.showUI());
			Main.frame.revalidate();
			/*
			 * sets the difficulty level
			 * this is chosen by the codemaker player
			 */
		} else if(e.getSource() == diffbtn[COLOURS_EASY]) {
			Play.gui.difficulty = EASY;
			resetCodes();
		} else if(e.getSource() == diffbtn[COLOURS_MED]) {
			Play.gui.difficulty = MED;
			resetCodes();
		} else if(e.getSource() == diffbtn[COLOURS_HARD]) {
			Play.gui.difficulty = HARD;
			resetCodes();
		} else {
			for(int i=0; i<CODES_LENGTH; i++) {
				/*
				 * Cycles through the colours (limited according to difficulty) and
				 * assigns to corresponding code buttons
				 */
				if(e.getSource() == codes[i]) {
					int currentCode = Integer.parseInt(codes[i].getText()) + 1;
					if(currentCode <= Play.gui.difficulty) { 
						codes[i].setText(currentCode+"");
						codes[i].changeColourTo(Colours.collections[currentCode-1]);
					} else { 
						codes[i].setText("1");
						codes[i].changeColourTo(Colours.collections[0]);
					}
				}
			}
		}
	}
}