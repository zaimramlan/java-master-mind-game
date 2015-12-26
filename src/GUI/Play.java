package GUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GUI.Custom.*;
import GUI.Assets.*;
import Game.*;

/*
 * This is the Game screen design
 */

public class Play extends JPanel implements ActionListener {
	public static Play gui = new Play(); //create 1 instance of Play that can be used throughout the program
	public int difficulty = 4; //by default, difficulty is set to 4 == 'easy'
	public String currentColour = "0"; //refers to the current selected colour
	private final String TITLE = "Mastermind";
	private final int SINGLEPLAYER = 1; // refers to game mode
	private final int MULTIPLAYER = 2;	
	private final int TRIALS = 10; //refers to number of attempts a codebreaker can guess
	private final int PATTERN_LENGTH = 4;
	private Codebreaker cb = Singleplayer.codebreaker;
	private JPanel titleSection;
	private NavButton quitbtn, checkbtn;
	private SingleGuessingRow[] row;
	private int currentRowIndex;


	/* Checks whether the codebreaker's guess is the correct answer */
	private boolean isCorrect() {
		SingleGuessingRow currentRow = row[currentRowIndex+1];
		String feedbacks = "";
		boolean result = false;
		cb.setCurrentGuess(currentRow.getGuesses());		
		if(Menu.mode == SINGLEPLAYER) {
			Computer c = Singleplayer.computer;
	        cb.setCurrentPoint(c.checkPattern(cb.getCurrentGuess()));
	        System.out.println("com" + Arrays.toString(c.getPattern()));
		} else if(Menu.mode == MULTIPLAYER) {
			Codemaker c = Multiplayer.codemaker;
	        cb.setCurrentPoint(c.checkPattern(cb.getCurrentGuess()));
	        System.out.println(Arrays.toString(c.getPattern()));
		}
        for (int j = 0; j < cb.getCurrentPoint()[0]; j++) feedbacks += 'G';
        for (int j = 0; j < cb.getCurrentPoint()[1]; j++) feedbacks += 'O';
        for (int j = 0; j < cb.getCurrentPoint()[2]; j++) feedbacks += 'R';
        currentRow.updateFeedback(feedbacks);
        if(cb.getCurrentPoint()[0] == 4) result = true;
        return result;        
	}

	/* 
	 * If the codebreaker fails to guess correctly within the trials/attempts given,
	 * the correct answer will be shown.
	 */
	private void showCorrectAnswer() {
		titleSection.removeAll();
		int[] answers = new int[4];
		if(Menu.mode == SINGLEPLAYER) {
			Computer c = Singleplayer.computer;
			answers = c.getPattern();
		} else if(Menu.mode == MULTIPLAYER) {
			Codemaker c = Multiplayer.codemaker;
			answers = c.getPattern();
		}		
		JPanel correctAnswerSection = new JPanel();
		GuessButton[] correctAnswer = new GuessButton[4];
		correctAnswerSection.setBackground(Colours.Midnight);
		for (int i=0; i<4; i++) {
			correctAnswer[i] = new GuessButton("0");
			correctAnswer[i].changeColourTo(Colours.collections[answers[i]-1]);
			correctAnswerSection.add(correctAnswer[i]);
		}
		titleSection.add(correctAnswerSection);
		titleSection.revalidate();
	}

	public JPanel showUI() {
		removeAll(); //removes previous components stored in the panel
		setLayout(new GridLayout(TRIALS+2,1));
		setBackground(Colours.Midnight);

		/* Title Section */
		titleSection = new JPanel();
		titleSection.setBackground(Colours.Midnight);
		Labels titleText = new Labels(TITLE, Fonts.Library.OpenSans("Light", 45), Colours.Clouds);
		titleSection.add(titleText);
		add(titleSection);

		/* Guessing Rows Section */
		row = new SingleGuessingRow[TRIALS];
		currentRowIndex = TRIALS-1;
		for(int i=TRIALS-1; i>=0; i--) {
			int index = TRIALS-1-i;
			row[index] = new SingleGuessingRow(i, difficulty);
			add(row[index]);
		}
		row[currentRowIndex--].toggleRow();

		/* Buttons Section */
		JPanel buttonSection = new JPanel();
		JPanel innerButtonSection = new JPanel();
		innerButtonSection.setLayout(new BoxLayout(innerButtonSection, BoxLayout.X_AXIS));
		quitbtn = new NavButton("Quit");
		quitbtn.setButtonColour(Colours.Alizarin);
		quitbtn.addActionListener(this);
		innerButtonSection.add(quitbtn);
		buttonSection.add(innerButtonSection);		
		innerButtonSection.add(Box.createRigidArea(new Dimension(25,0)));		
		checkbtn = new NavButton("Check");
		checkbtn.setButtonColour(Colours.Emerald);
		checkbtn.addActionListener(this);
		innerButtonSection.add(checkbtn);		
		add(buttonSection);

		return this;
	}

	/* Actions to be taken triggered by events done on the corresponding components */
	public void actionPerformed(ActionEvent e) {
		SingleGuessingRow currentRow = row[currentRowIndex+1];
		boolean hasInput = !(Arrays.toString(currentRow.getGuesses()).contains("0"));
		if(e.getSource() == checkbtn && hasInput) {
			/* 
			 * checks if codebreaker's pattern is correct
			 * if correct, game stops, shows score and returns to menu screen
			 * otherwise proceed with the game, if there are still available attempts (trials)
			 */
			if(!isCorrect()) {
				if(currentRowIndex >= 0) {
					/* toggles row usability */
					row[currentRowIndex].toggleRow();
					row[currentRowIndex+1].toggleRow();
					currentRowIndex--;
				} else {
					/* shows final score and reset guesser's score for upcoming games */
					showCorrectAnswer();
					JOptionPane.showMessageDialog(new JFrame(), "SCORE: " + cb.getScore());
					cb.reset();
					Main.frame.setContentPane(Menu.gui.showUI());
					Main.frame.revalidate();					
				}
			} else {
				Main.frame.setContentPane(Highscore.gui.showUI());
				Main.frame.revalidate();					
			}
		} else if(e.getSource() == quitbtn) {
			cb.reset();
			Main.frame.setContentPane(Menu.gui.showUI());
			Main.frame.revalidate();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Please guess the pattern first!");
		}
	}
}