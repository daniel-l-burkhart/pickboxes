
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

	/**
	 * Reads in the file from the user and parses it properly for this class.
	 */
	public void readInFile() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Input name of input file");
		String inputFile = scanner.next();
		scanner.close();

		List<String> lines = null;
		String[] singleLine = null;
		this.boxes = new ArrayList<Integer>();

		if (inputFile == null) {
			throw new IllegalArgumentException("File cannot be null");
		}

		try {
			lines = Files.readAllLines(Paths.get(inputFile), StandardCharsets.UTF_8);
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (lines != null) {
			for (String line : lines) {
				singleLine = line.split(",");
			}

			for (String number : singleLine) {
				this.boxes.add(Integer.parseInt(number));
			}

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

	private void initializeIJCell(int length) {
		for (int row = 0; row < this.boxes.size() - length; row++) {
			int col = row + length;
			this.matrix[row][col] = 0;
			this.calculateKValue(row, col);
		}
	}

	private void calculateKValue(int row, int column) {

		for (int kIndex = row + 1; kIndex < column; kIndex++) {

			int kValue = this.matrix[row][kIndex] + this.matrix[kIndex][column]
					+ this.boxes.get(row) * this.boxes.get(kIndex) * this.boxes.get(column);

			if (kValue > this.matrix[row][column]) {
				this.matrix[row][column] = kValue;
			}
		}
	}

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

		picker.readInFile();

		int result = picker.findBiggestPrize();

		System.out.println("Largest prize value is: " + result);

	}

}
