package GUI.Custom;

import java.awt.*;

import javax.swing.*;

import GUI.Assets.*;
import GUI.Main;

public class Labels extends JLabel {
	private String label;

	public Labels(String s, Font f, Color c) {
		label = s;
		setAlignmentX(CENTER_ALIGNMENT);
        setFont(f);
        setForeground(c);
        setBackground(Colours.Clouds);
	}

	@Override
	public void paintComponent(Graphics g) {
        FontMetrics fm = getFontMetrics(getFont());
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.drawString(label, (getWidth()/2)-(fm.stringWidth(label)/2), getHeight()/2 + 20);
	}

	@Override
    public Dimension getPreferredSize() {
        return new Dimension(Main.frame.getWidth(),65);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }	
}