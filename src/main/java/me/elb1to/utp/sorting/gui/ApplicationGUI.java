package me.elb1to.utp.sorting.gui;

import javax.swing.*;

/**
 * @author Elb1to
 * @since 7/3/2024
 */
public class ApplicationGUI extends JFrame {

	private JPanel mainPanel;

	private JButton ordenarButton;
	private JButton archivoButton;

	private JTextField valuesField;
	private JLabel valuesLabel;

	private JLabel fixedValuesOutput;
	private JLabel fixedValuesLabel;

	private JComboBox<? extends String> sortingTypesComboBox;

	public ApplicationGUI() {
		this.setContentPane(mainPanel);
		this.setTitle("Metodos de Ordenamiento");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JButton getOrdenarButton() {
		return ordenarButton;
	}

	public JTextField getValuesField() {
		return valuesField;
	}

	public JLabel getFixedValuesOutput() {
		return fixedValuesOutput;
	}

	public JButton getArchivoButton() {
		return archivoButton;
	}

	public JComboBox<? extends String> getSortingTypesComboBox() {
		return sortingTypesComboBox;
	}
}
