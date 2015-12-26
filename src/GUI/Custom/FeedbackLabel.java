package GUI.Custom;

import java.awt.*;
import javax.swing.*;
import GUI.Assets.*;

public class FeedbackLabel extends JLabel {
	private Color colour = Colours.Asphalt;
	private boolean hasGivenFeedback = false;

	public FeedbackLabel(String s) {
		super(s);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(20,20);
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}			

	public void setFeedbackColour(Color colour) {
		this.colour = colour;
		hasGivenFeedback = true;
		repaint();
	}

	public void paintComponent(Graphics g) {
		g.setColor(colour);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if(hasGivenFeedback) {
			g.fillOval(getSize().width/5, getSize().height/5, getSize().width-5, getSize().height-5);		
		} else {
			g.fillOval(getSize().width/4, getSize().height/4, getSize().width-10, getSize().height-10);		
		}
	}
}