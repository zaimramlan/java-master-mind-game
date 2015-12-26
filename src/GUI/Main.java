package GUI;

import java.awt.*;
import javax.swing.*;

/**
 * This is the main GUI class.
 */

public class Main extends JFrame {
	public static Main frame = new Main(); //create 1 instance of Main that can be used throughout the program

    public String text() {
        return "SOMETHING";
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(590,915);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public void run(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setTitle("Master Mind");
                frame.setContentPane(GUI.Menu.gui.showUI());
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    /* Set frame's content pane to Menu screen */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setTitle("Master Mind");
                frame.setContentPane(GUI.Menu.gui.showUI());
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
}