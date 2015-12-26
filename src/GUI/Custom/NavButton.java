package GUI.Custom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GUI.Assets.*;

public class NavButton extends JButton implements MouseListener {
	private Color originalColour, hoverColour, fontColour = Colours.Clouds;
	private Boolean isHover = false;
	private Dimension preferredSize;

	public NavButton (String s) {
		super(s);
		setBorder(null);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setAlignmentX(Component.CENTER_ALIGNMENT);
		addMouseListener(this);
		preferredSize = new Dimension(250,50);
	}

	public NavButton (String s, Dimension d) {
		super(s);
		setBorder(null);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		addMouseListener(this);
		preferredSize = d;
	}

	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
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

	public void setFontColour(Color c) {
		fontColour = c;
		revalidate();
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
		Dimension arc = new Dimension(15,15);
		g.setColor(hoverColour);
		g.fillRoundRect(0, 0, getSize().width, getSize().height, arc.width, arc.height);

        Font f = getFont();
        String label = getText();
        if (f != null) {
            FontMetrics fm = getFontMetrics(getFont());
            g2d.setFont(Fonts.Library.OpenSans("Regular", 15));
            g2d.setColor(fontColour);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g2d.drawString(label, (getWidth()/2)-(fm.stringWidth(label)/2), getHeight()/2 + 5);
        }		
	}

    public void mouseEntered (MouseEvent event) { toggleHoverColour(); }
    public void mouseExited (MouseEvent event) { toggleHoverColour(); }
    public void mouseClicked (MouseEvent event) {}
    public void mousePressed (MouseEvent event) {}
    public void mouseReleased (MouseEvent event) {}	
}