package GUI.Custom;

import java.awt.*;
import javax.swing.*;
import GUI.Assets.*;

public class NumberingLabel extends JLabel {
	public NumberingLabel(String s) {
		super(s);
		setFont(Fonts.Library.OpenSans("Light", 15));
	}

	private void checkEnabled() {
		if(isEnabled()) setForeground(Colours.Active);
		else setForeground(Colours.Asphalt);
	}	

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		checkEnabled();
	}
}