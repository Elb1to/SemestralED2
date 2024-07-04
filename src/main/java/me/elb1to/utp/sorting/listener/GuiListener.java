package me.elb1to.utp.sorting.listener;

import me.elb1to.utp.sorting.gui.ApplicationGUI;
import me.elb1to.utp.sorting.type.SortingType;
import me.elb1to.utp.sorting.type.impl.BubbleSort;
import me.elb1to.utp.sorting.type.impl.HeapSort;
import me.elb1to.utp.sorting.type.impl.InsertionSort;
import me.elb1to.utp.sorting.type.impl.MergeSort;
import me.elb1to.utp.sorting.type.impl.QuickSort;
import me.elb1to.utp.sorting.type.impl.RadixSort;
import me.elb1to.utp.sorting.type.impl.SelectionSort;
import me.elb1to.utp.sorting.type.impl.ShellSort;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Elb1to
 * @version Java 17
 * @since 7/3/2024
 */
public class GuiListener {

	private static final String SORTED_ARRAY_FILE = "sortedArray.txt";
	private final ApplicationGUI gui;
	private int[] array;

	public GuiListener(ApplicationGUI gui) {
		this.gui = gui;
	}

	public void registerListeners() {
		gui.getOrdenarButton().addActionListener(event -> {
			SortingType sortingType = getSortingType();
			if (sortingType == null) {
				showError("Necesitas escoger un metodo de ordenamiento!");
				return;
			}

			array = parseInput(gui.getValuesField().getText());
			array = sortingType.sort(array);
			showInformation(sortingType);

			gui.getFixedValuesOutput().setText(Arrays.toString(array));
			writeToFile(Objects.requireNonNull(gui.getSortingTypesComboBox().getSelectedItem()) + " = " + Arrays.toString(array));
		});

		gui.getArchivoButton().addActionListener(event -> {
			File selectedFile = selectFile();
			if (selectedFile == null || !selectedFile.getName().endsWith(".txt")) {
				showError("El archivo seleccionado no es un archivo de texto (.txt)");
				return;
			}

			readFromFile(selectedFile);
		});
	}

	private SortingType getSortingType() {
		return switch (Objects.requireNonNull(gui.getSortingTypesComboBox().getSelectedItem()).toString()) {
			case "Bubble Sort" -> new BubbleSort();
			case "Selection Sort" -> new SelectionSort();
			case "Insertion Sort" -> new InsertionSort();
			case "Merge Sort" -> new MergeSort();
			case "Quick Sort" -> new QuickSort();
			case "Heap Sort" -> new HeapSort();
			case "Radix Sort" -> new RadixSort();
			case "Shell Sort" -> new ShellSort();
			default -> null;
		};
	}

	private int[] parseInput(String input) {
		String[] values = input.split(",");
		int[] array = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			array[i] = Integer.parseInt(values[i]);
		}

		return array;
	}

	private void writeToFile(String content) {
		try {
			Path path = Paths.get(GuiListener.SORTED_ARRAY_FILE);
			Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File selectFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(gui.getMainPanel());
		if (result == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}

		return null;
	}

	private void readFromFile(File file) {
		try {
			gui.getValuesField().setText(Files.readString(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(gui.getMainPanel(), message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void showInformation(SortingType sortingType) {
		System.out.println("SortingType Class = " + sortingType.getSortingType());

		try {
			String className = sortingType.getSortingType().getName().replace('.', '/');
			Path path = Paths.get("src/main/java/" + className + ".java");
			String code = Files.readString(path);

			String methodSignature = "public int[] sort(int[] array) {";
			int methodStartIndex = code.indexOf(methodSignature);
			if (methodStartIndex == -1) {
				System.out.println("Method not found in the source code.");
				return;
			}

			int methodEndIndex = code.indexOf("public ", methodStartIndex + methodSignature.length());
			if (methodEndIndex == -1) { // This is the last method in the class
				methodEndIndex = code.lastIndexOf("}");
			}

			String methodCode = code.substring(methodStartIndex, methodEndIndex);
			System.out.println(methodCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
