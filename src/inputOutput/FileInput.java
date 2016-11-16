package inputOutput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class that handles file input
 * 
 * @author Daniel Burkhart
 * @version Fall 2016
 */
public class FileInput {

	private List<String> lines;
	private ArrayList<Integer> boxes;
	private boolean success;
	private String[] singleLine;

	/**
	 * Constructor that initializes necessary variables.
	 */
	public FileInput() {
		this.boxes = new ArrayList<Integer>();
		this.lines = new ArrayList<String>();
		this.success = false;
		this.singleLine = null;
	}

	/**
	 * Reads in the file from the user and parses it properly for this class.
	 * 
	 * @param inputFile
	 *            The string name of the file.
	 * 
	 * @return True if file read was successful, false otherwise.
	 */
	public boolean readInFile(String inputFile) {

		if (inputFile == null) {
			throw new IllegalArgumentException("File cannot be null");
		}

		try {
			this.lines = Files.readAllLines(Paths.get(inputFile), StandardCharsets.UTF_8);
			this.splitUpLines();
			this.success = true;

		} catch (IOException exception) {
			System.out.println("There was a problem reading your file");
		}

		return this.success;

	}

	/**
	 * Splits the line from the file based on the comma delimiter
	 */
	private void splitUpLines() {

		for (String line : this.lines) {
			this.singleLine = line.split(",");
			this.parseLineToInt();

		}
	}

	/**
	 * Parse each String representation of the number and puts it into the
	 * arrayList.
	 * 
	 * @param singleLine
	 *            The string array that contains each number
	 */
	private void parseLineToInt() {

		if (this.singleLine == null) {
			throw new IllegalArgumentException("Line cannot be null");
		}

		for (String number : this.singleLine) {

			try {
				this.boxes.add(Integer.parseInt(number));

			} catch (NumberFormatException exception) {
				System.out.println("Invalid number format. " + exception.getMessage());
			}

		}
	}

	/**
	 * Gets the list of boxes that has been parsed from the file.
	 * 
	 * @return An arrayList containing numbers.
	 */
	public ArrayList<Integer> getBoxes() {
		return this.boxes;
	}

}
