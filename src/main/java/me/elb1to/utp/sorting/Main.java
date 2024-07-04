package me.elb1to.utp.sorting;

import me.elb1to.utp.sorting.gui.ApplicationGUI;
import me.elb1to.utp.sorting.listener.GuiListener;

/**
 * @author Elb1to
 * @since 7/3/2024
 */
public class Main {

	public static void main(String[] args) {
		ApplicationGUI frame = new ApplicationGUI();
		frame.setSize(1000, 200);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		GuiListener guiListener = new GuiListener(frame);
		guiListener.registerListeners();
	}
}
