package GUI.Custom;

import java.awt.*;
import javax.swing.*;
import GUI.Assets.*;

public class GuessButton extends JButton {
	private boolean hasGuessed = false;
	private Color activeColour, guessedColour;

	public GuessButton(String s) {
		super(s);
		setBorder(null);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(50,50);
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	public void reset() {
		hasGuessed = false;
	}

	public void changeColourTo(Color guessedColour) {
		if(!hasGuessed) hasGuessed = true;
		this.guessedColour = guessedColour;
	}

	private void checkEnabled() {
		if(isEnabled()) activeColour = Colours.Active;
		else activeColour = Colours.Asphalt;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		checkEnabled();
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
		);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(activeColour);
		g2d.setRenderingHints(rh);
		g2d.drawOval(0, 0, getSize().width-4, getSize().height-4);
		if(!hasGuessed) {
			g2d.setRenderingHints(rh);
			g2d.setColor(activeColour);
			g2d.fillOval(getSize().width/3+3, getSize().height/3+2, getSize().width-40, getSize().height-40);
		} else {
			g2d.setRenderingHints(rh);
			g2d.setColor(guessedColour);
			g2d.fillOval(getSize().width/8, getSize().height/8, getSize().width-15, getSize().height-15);
		}
	}
} 