package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GUI.Custom.*;
import GUI.Assets.*;

public class SingleGuessingRow extends JPanel implements ActionListener {
	private final int CODES_LENGTH = 4; //refers to length of pattern to be guessed
	private final int MAX_COLOURS = 8; //refers to maximum colours available for guessing
	private final int MIN_TRIAL = 0; //sets the minimum number of guessing attempts
	private NumberingLabel numbering;
	private GuessButton[] guess = new GuessButton[CODES_LENGTH]; //refers to the codebreaker's pattern
	private int[] guessValue = new int[CODES_LENGTH]; //each colour in a pattern has a value of 1-8 and is stored in this array depending on the colour that the codebreaker guesses
	private FeedbackLabel[] feedback = new FeedbackLabel[CODES_LENGTH]; //refers to the codemaker's feedback
	private ColourButton[] colourButtons = {
			new ColourButton("turqoise"),
			new ColourButton("emerald"),
			new ColourButton("river"),
			new ColourButton("amethyst"),
			new ColourButton("asphalt"),
			new ColourButton("sun"),
			new ColourButton("carrot"),
			new ColourButton("alizarin"),
		};
	private boolean isEnabled = false; //refers to whether the row is enabled/disabled for guessing

	public SingleGuessingRow(int trial, int difficulty) {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gb);

		/* Numbering Section */
		numbering = new NumberingLabel((trial+1) + "");
		numbering.setEnabled(isEnabled);
		numbering.setForeground(Colours.Asphalt);
		if(trial==9) c.weightx = 0.95; //2 numbering digits would alter the layout, hence this is required for alignment purposes
		else c.weightx = 1.0;
		addComponent(numbering, gb, c);

		/* Guesser's Pattern Section */
		c.weightx = 1.0;
		for (int i=0; i<CODES_LENGTH; i++) {
			guess[i] = new GuessButton("");
			guess[i].setEnabled(isEnabled);
			guess[i].addActionListener(this);
			addComponent(guess[i], gb, c);
		}

		/* Codemaker's Feedback Section */
		JPanel b = new JPanel();
		b.setLayout(new GridLayout(2,2));
		for (int i=0; i<CODES_LENGTH; i++) {
			feedback[i] = new FeedbackLabel("X");
			b.add(feedback[i]);
		}
		addComponent(b, gb, c);			

		/* Colours Available Section */
		if(trial>=MIN_TRIAL && trial<difficulty) {
			colourButtons[trial].addActionListener(this);
			colourButtons[trial].setButtonColour(Colours.collections[trial]);
			addComponent(colourButtons[trial], gb, c);
		} else {
			JPanel filler = new JPanel();
			filler.setPreferredSize(new Dimension(50,50));
			addComponent(filler, gb, c);			
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

	/* Toggle row's usability */
	public void toggleRow()	 {
		for (int i=0; i<CODES_LENGTH; i++) {
			numbering.setEnabled(!isEnabled);
			guess[i].setEnabled(!isEnabled);
		}
		isEnabled = !isEnabled;
	}

	/* Gives the feedback based on guesser's pattern */
	public void updateFeedback(String feedbacks) {
		for(int i=0; i<feedbacks.length(); i++) {
			switch(feedbacks.charAt(i)) {
				case 'G':
					feedback[i].setFeedbackColour(Colours.Alizarin);
					break;				
				case 'O':
					feedback[i].setFeedbackColour(Color.WHITE);
					break;
			}
		}
	}

	/* Get guesser's pattern values */
	public int[] getGuesses() {
		return guessValue;
	}

	/* Actions triggered by events done on the corresponding components */
	public void actionPerformed(ActionEvent e) {
		int currentColour = Integer.parseInt(Play.gui.currentColour);
		for(int i=0; i<CODES_LENGTH; i++)
			if(e.getSource() == guess[i]) {
				guess[i].changeColourTo(Colours.collections[currentColour-1]);
				guessValue[i] = currentColour;
			}
		for(int i=0; i<MAX_COLOURS; i++)
			if(e.getSource() == colourButtons[i])
				Play.gui.currentColour = i+1+"";
	}
}