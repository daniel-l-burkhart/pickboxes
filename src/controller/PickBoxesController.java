package controller;

import inputOutput.FileInput;
import model.PickBoxes;

/**
 * Controller class that acts as a bridge between model and view
 * 
 * @author Daniel Burkhart
 * @version Fall 2016
 */
public class PickBoxesController {

	private PickBoxes picker;
	private FileInput fileInput;
	private boolean success;
	private int result;

	/**
	 * Controller constructor that initializes all variables
	 */
	public PickBoxesController() {
		this.picker = new PickBoxes();
		this.fileInput = new FileInput();
		this.success = false;
		this.result = 0;
	}

	/**
	 * Reads the file into the program
	 * 
	 * @param inputFile
	 *            The string name of the file
	 */
	public void readFile(String inputFile) {

		if (inputFile == null) {
			throw new IllegalArgumentException("File cannot be null");
		}

		this.success = this.fileInput.readInFile(inputFile);
	}

	/**
	 * Finds the largest prize from the boxes
	 * 
	 * @return An integer of the biggest possible prize
	 */
	public int findLargestPrize() {

		if (this.success) {
			this.result = this.picker.findBiggestPrize(this.fileInput.getBoxes());
		}

		return this.result;
	}

	/**
	 * Gets the matrix from the model
	 * 
	 * @return A 2D array representing the matrix.
	 */
	public int[][] getMatrix() {
		return this.picker.getMatrix();
	}

}
