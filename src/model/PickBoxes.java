package model;

import java.util.ArrayList;

/**
 * Finds the largest prize based on which boxes are picked first.
 * 
 * @author Daniel Burkhart
 * @version Fall 2016
 */
public class PickBoxes {

	private ArrayList<Integer> boxes;
	private int[][] matrix;
	private int length;
	private int row;
	private int col;
	private int kValue;
	private int leftAdjNeighbor;
	private int rightAdjNeighbor;
	private int currBoxPrize;

	/**
	 * Constructor that initializes variables needed in class.
	 */
	public PickBoxes() {
		this.boxes = new ArrayList<Integer>();
	}

	/**
	 * Finds the biggest possible prize based on the current matrix
	 * 
	 * @param boxes
	 *            The list of numbers of the sequence.
	 * 
	 * @return An integer representing the maximum prize value.
	 */
	public int findBiggestPrize(ArrayList<Integer> boxes) {

		if (boxes == null) {
			throw new IllegalArgumentException("Boxes cannot be null");
		}

		this.boxes = boxes;

		this.boxes.add(0, 1);
		this.boxes.add(1);

		this.matrix = new int[this.boxes.size()][this.boxes.size()];

		for (this.length = 2; this.length < this.boxes.size(); this.length++) {
			this.initializeIJCell();
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
	private void initializeIJCell() {
		for (this.row = 0; this.row < this.boxes.size() - this.length; row++) {
			this.col = this.row + this.length;
			this.matrix[this.row][this.col] = 0;
			this.calculateKValue();
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
	private void calculateKValue() {

		for (int kIndex = this.row + 1; kIndex < this.col; kIndex++) {

			this.leftAdjNeighbor = this.matrix[this.row][kIndex];
			this.rightAdjNeighbor = this.matrix[kIndex][this.col];
			this.currBoxPrize = this.boxes.get(this.row) * this.boxes.get(kIndex) * this.boxes.get(this.col);

			this.kValue = this.leftAdjNeighbor + this.rightAdjNeighbor + this.currBoxPrize;

			if (this.kValue > this.matrix[this.row][this.col]) {
				this.matrix[this.row][this.col] = this.kValue;
			}
		}
	}

	/**
	 * Prints matrix to the screen.
	 */
	private void printMatrix() {

		System.out.println("Matrix:");

		for (int row = 0; row < this.matrix.length; row++) {
			for (int column = 0; column < this.matrix.length; column++) {
				System.out.print(this.matrix[row][column] + "\t");
			}
			System.out.println();
		}

		System.out.println();
	}

}
