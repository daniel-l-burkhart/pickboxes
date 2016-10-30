
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Finds the largest prize based on which boxes are picked first.
 * 
 * @author Daniel Burkhart
 * @version Fall 2016
 */
public class PickBoxes {

	private ArrayList<Integer> boxes;
	private int[][] matrix;
	private Scanner scanner;
	private List<String> lines;

	/**
	 * Constructor that initializes variables needed in class.
	 */
	public PickBoxes() {
		this.boxes = new ArrayList<Integer>();
		this.scanner = new Scanner(System.in);
		this.lines = new ArrayList<String>();
	}

	/**
	 * Runs the program.
	 */
	public void runProgram() {
		boolean success = this.readInFile();

		if (success) {
			int result = this.findBiggestPrize();
			System.out.println("Largest prize value is: " + result);

		}

	}

	/**
	 * Reads in the file from the user and parses it properly for this class.
	 * 
	 * @return True if file read was successful, false otherwise.
	 */
	public boolean readInFile() {

		boolean success = false;

		System.out.println("Input name of input file");
		String inputFile = this.scanner.next();
		this.scanner.close();

		if (inputFile == null) {
			throw new IllegalArgumentException("File cannot be null");
		}

		try {

			this.lines = Files.readAllLines(Paths.get(inputFile), StandardCharsets.UTF_8);
			this.splitUpLines();
			success = true;

		} catch (IOException exception) {
			System.out.println("There was a problem reading your file");

		}

		return success;

	}

	/**
	 * Splits the line from the file based on the comma delimiter
	 */
	private void splitUpLines() {

		String[] singleLine = null;

		for (String line : this.lines) {
			singleLine = line.split(",");
			this.parseLineToInt(singleLine);

		}
	}

	/**
	 * Parse each String representation of the number and puts it into the
	 * arrayList.
	 * 
	 * @param singleLine
	 *            The string array that contains each number
	 */
	private void parseLineToInt(String[] singleLine) {

		if (singleLine == null) {
			throw new IllegalArgumentException("Line cannot be null");
		}

		for (String number : singleLine) {

			this.tryParseInteger(number);
		}
	}

	/**
	 * Tries to parse the integer from the string but handles the exception if
	 * thrown.
	 * 
	 * @param number
	 *            The String representation of the number.
	 */
	private void tryParseInteger(String number) {
		try {

			this.boxes.add(Integer.parseInt(number));
		} catch (NumberFormatException exception) {
			exception.printStackTrace();
			System.out.println("Invalid number format. " + exception.getMessage());
		}
	}

	/**
	 * Finds the biggest possible prize based on the current matrix
	 * 
	 * @return An integer representing the maximum prize value.
	 */
	public int findBiggestPrize() {

		this.boxes.add(0, 1);
		this.boxes.add(1);
		this.matrix = new int[this.boxes.size()][this.boxes.size()];

		for (int length = 2; length < this.boxes.size(); length++) {
			this.initializeIJCell(length);
		}

		this.printMatrix();

		return this.matrix[0][this.boxes.size() - 1];
	}

	/**
	 * Initializes all cells of row I and column J with 0.
	 * 
	 * @param length
	 *            The length of the chain
	 */
	private void initializeIJCell(int length) {
		for (int row = 0; row < this.boxes.size() - length; row++) {
			int col = row + length;
			this.matrix[row][col] = 0;
			this.calculateKValue(row, col);
		}
	}

	/**
	 * Calculates the kValue for the current box
	 * 
	 * @param row
	 *            The row in the matrix
	 * @param column
	 *            The column in the matrix
	 */
	private void calculateKValue(int row, int column) {

		for (int kIndex = row + 1; kIndex < column; kIndex++) {

			int kValue = this.matrix[row][kIndex] + this.matrix[kIndex][column]
					+ this.boxes.get(row) * this.boxes.get(kIndex) * this.boxes.get(column);

			if (kValue > this.matrix[row][column]) {
				this.matrix[row][column] = kValue;
			}
		}
	}

	/**
	 * Prints matrix to the screen.
	 */
	private void printMatrix() {
		System.out.println("Matrix = ");
		for (int row = 0; row < this.matrix.length; row++) {
			for (int column = 0; column < this.matrix.length; column++) {
				System.out.print(this.matrix[row][column] + "\t");
			}
			System.out.println();
		}

		System.out.println();
	}

	/**
	 * Main method that serves as the entry point to the program.
	 * 
	 * @param args
	 *            The args passed in
	 */
	public static void main(String[] args) {

		PickBoxes picker = new PickBoxes();
		picker.runProgram();

	}

}
