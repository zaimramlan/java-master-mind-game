package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GUI.Custom.*;
import GUI.Assets.*;

public class Instructions extends JPanel implements ActionListener {
	public static Instructions gui = new Instructions();
	private NavButton backbtn;
	private final String[] INSTRUCTIONS = {
			"There are 2 sides in this game, one is the codemaker and the other is the codebreaker. The codemaker (computer in Singleplayer mode or another player in Multiplayer mode) would choose a pattern of four colours like the one above.\n\nThe codebreaker would then guess the pattern by placing a colour in each of the four holes within 10 attempts. After each attempt, a feedback will be given that indicates whether or not the guessed pattern is correct.",
			"A red feedback colour is placed for each guessed colour that is correct in both colour and position.\n\nThe placement of a white feedback colour indicates that a guessed colour exists but placed incorrectly.\n\nA small gray circle resembles a no feedback, which means a guessed colour is not in the correct pattern."
	};

	public JPanel showUI() {
		removeAll(); //removes previous components stored in the panel
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		setLayout(gb);
		setBackground(Colours.Midnight);

		/* Title Section */
		JPanel textSection = new JPanel();
		Labels titleText = new Labels("Mastermind", Fonts.Library.OpenSans("Light", 65), Colours.Clouds);
		textSection.setBackground(Colours.Midnight);
		textSection.add(titleText);
		addComponent(textSection, gb, c);

		/* Spacing Section */
		addComponent(Box.createRigidArea(new Dimension(0,25)), gb, c);

		/* Mockup Guess Buttons Section */
		JPanel guessButtonSection = new JPanel();
		GuessButton[] guess = new GuessButton[4];
		for (int i=0; i<4; i++) {
			guess[i] = new GuessButton("0");
			guess[i].changeColourTo(Colours.collections[i]);
			guessButtonSection.add(guess[i]);
		}
		guessButtonSection.setBackground(Colours.Midnight);
		addComponent(guessButtonSection, gb, c);

		/* Spacing Section */
		addComponent(Box.createRigidArea(new Dimension(0,25)), gb, c);

		/* Instruction Section */
		JPanel taPanel = new JPanel();
		JTextArea ta = new JTextArea();
		ta.setPreferredSize(new Dimension(500,210));
		ta.setFont(Fonts.Library.OpenSans("Light", 16, 0));
		ta.setBackground(Colours.Midnight);
		ta.setForeground(Colours.Clouds);
		ta.append(INSTRUCTIONS[0]);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		taPanel.setBackground(Colours.Midnight);
		taPanel.add(ta);
		addComponent(taPanel, gb, c);

		/* Spacing Section */
		addComponent(Box.createRigidArea(new Dimension(0,25)), gb, c);

		/* Mockup Feedback Labels Section */
		JPanel feedbackButtonSection = new JPanel();
		feedbackButtonSection.setLayout(new GridLayout(2,2));
		FeedbackLabel[] feedback = new FeedbackLabel[4];
		for (int i=0; i<4; i++) {
			feedback[i] = new FeedbackLabel("0");
			if(i!=3) feedback[i].setFeedbackColour(Colours.Alizarin);
			feedbackButtonSection.add(feedback[i]);
		}
		feedback[1].setFeedbackColour(Color.WHITE);
		feedbackButtonSection.setBackground(Colours.Midnight);
		addComponent(feedbackButtonSection, gb, c);

		/* Spacing Section */
		addComponent(Box.createRigidArea(new Dimension(0,25)), gb, c);

		/* Instruction Section */
		JPanel jtaPanel = new JPanel();
		JTextArea jta = new JTextArea();
		jta.setPreferredSize(new Dimension(500,200));
		jta.setFont(Fonts.Library.OpenSans("Light", 16, 0));
		jta.setBackground(Colours.Midnight);
		jta.setForeground(Colours.Clouds);
		jta.append(INSTRUCTIONS[1]);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jtaPanel.setBackground(Colours.Midnight);
		jtaPanel.add(jta);
		addComponent(jtaPanel, gb, c);

		addComponent(Box.createRigidArea(new Dimension(0,25)), gb, c);

		/* Buttons Section */
		backbtn = new NavButton("Back");
		backbtn.setButtonColour(Colours.Asbestos);
		backbtn.addActionListener(this);
		addComponent(backbtn, gb, c);

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

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backbtn) {
			Main.frame.setContentPane(Menu.gui.showUI());
			Main.frame.revalidate();			
		}
	}
}