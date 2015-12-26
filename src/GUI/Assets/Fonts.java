package GUI.Assets;

import java.awt.*;
import java.awt.font.*;
import java.util.*;
import java.io.*;

public class Fonts {
	public static Fonts Library = new Fonts();
	public Font OpenSans;

	public Font OpenSans(String style, int size) { return OpenSans(style, size, -0.05); }
	public Font OpenSans(String style, int size, double spacing) { OpenSans = makeFont("OpenSans", style, size, spacing); return OpenSans; }

	public static Font makeFont(String fontName, String style, int size, double letterSpacing) {
		Font font = new Font("serif", Font.PLAIN, 24);
		Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
		attributes.put(TextAttribute.TRACKING, letterSpacing);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, font.getClass().getResourceAsStream("/fonts/" + fontName + "-" + style + ".ttf"));
			font = font.deriveFont(Font.PLAIN, size);
			font = font.deriveFont(attributes);
		} 
		catch(IOException ex) { ex.printStackTrace(); } 
		catch(FontFormatException ffe) { ffe.printStackTrace(); }
		return font;
	}
}