package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GUI.Custom.*;
import GUI.Assets.*;
import Game.*;


/*
 * This is the Highscore screen design
 */

public class Highscore extends JPanel implements ActionListener {
	public static Highscore gui = new Highscore(); //create 1 instance of Highscore that can be used throughout the program
	private Codebreaker cb = Singleplayer.codebreaker;
	private NavButton newGamebtn;

	public JPanel showUI() {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();		
		removeAll(); //removes previous components stored in the panel
		setLayout(gb);	

		/* Sets up the inner panel */
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));		
		panel.setPreferredSize(new Dimension(Main.frame.getWidth(), Main.frame.getHeight()));

		/* Game Status Section */
		Labels gameStatusText = new Labels("You are the", Fonts.Library.OpenSans("Light", 50), Colours.Asbestos);
		Labels titleText = new Labels("Mastermind!", Fonts.Library.OpenSans("Light", 65), Colours.Asbestos);
		panel.add(gameStatusText);
		panel.add(titleText);

		/* Mockup Guess Buttons Section */
		JPanel mockupSection = new JPanel();
		GuessButton[] guess = new GuessButton[4];
		for (int i=0; i<4; i++) {
			guess[i] = new GuessButton("0");
			guess[i].changeColourTo(Colours.collections[i]);
			mockupSection.add(guess[i]);
		}
		panel.add(mockupSection);

		/* Score Section */
		Labels finalScoreTitle = new Labels("Your Score:", Fonts.Library.OpenSans("Light", 20), Colours.Asbestos);
		Labels scoreText = new Labels(cb.getScore()+"", Fonts.Library.OpenSans("Light", 65), Colours.River);
		Labels highScoreTitle = new Labels("High Score:", Fonts.Library.OpenSans("Light", 20), Colours.Asbestos);
		Labels highScoreText = new Labels(cb.getHighscore()+"", Fonts.Library.OpenSans("Light", 30), Colours.Asbestos);
		panel.add(finalScoreTitle);
		panel.add(scoreText);
		panel.add(highScoreTitle);
		panel.add(highScoreText);

		panel.add(Box.createRigidArea(new Dimension(0,50)));

		/* Button Section */
		newGamebtn = new NavButton("New Game");
		newGamebtn.setButtonColour(Colours.Asbestos);
		newGamebtn.setFontColour(Colours.Clouds);
		newGamebtn.addActionListener(this);
		panel.add(newGamebtn);

		/* Adds the inner panel into the Highscore Panel */		
		c.gridwidth = GridBagConstraints.REMAINDER;
		gb.setConstraints(panel, c);
		add(panel);

		return this;				
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newGamebtn) {
			cb.endGame();
			Main.frame.setContentPane(Menu.gui.showUI());
			Main.frame.revalidate();			
		}
	}
}