package GUI.Custom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColourButton extends JButton implements MouseListener {
	private Color originalColour, hoverColour;
	private Boolean isHover = false;

	public ColourButton(String s) {
		super(s);
		setBorder(null);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		addMouseListener(this);
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

	public void setButtonColour(Color originalColour) {
		this.originalColour = originalColour;
		hoverColour = originalColour;
	}

	private void toggleHoverColour() {
		if(!isHover) { 
			hoverColour = originalColour.brighter();
			isHover = true;
		} else { 
			hoverColour = originalColour;
			isHover = false;
		}
		revalidate();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		Dimension arc = new Dimension(10,10);
		g.setColor(Color.WHITE);
		g.fillRoundRect(0, 0, getSize().width, getSize().height, arc.width, arc.height);
		g.setColor(hoverColour);
		g.fillOval(getSize().width / 9, getSize().height / 9, getSize().width - 10, getSize().height - 10);
	}

    public void mouseEntered (MouseEvent event) { toggleHoverColour(); }
    public void mouseExited (MouseEvent event) { toggleHoverColour(); }
    public void mousePressed (MouseEvent event) {}
    public void mouseClicked (MouseEvent event) {}
    public void mouseReleased (MouseEvent event) {}	
}