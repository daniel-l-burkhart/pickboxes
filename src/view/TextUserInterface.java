package view;

import java.util.Scanner;

import controller.PickBoxesController;

/**
 * TUI Class for the view
 * 
 * @author Daniel Burkhart
 * @version Fall 2016
 */
public class TextUserInterface {

	private PickBoxesController controller;
	private Scanner scanner;
	private String inputFile;
	private int largestPrize;

	/**
	 * Constructor that initializes necessary variables.
	 */
	public TextUserInterface() {
		this.controller = new PickBoxesController();
		this.scanner = new Scanner(System.in);
		this.largestPrize = 0;
	}

	/**
	 * Runs the program
	 */
	public void runProgram() {

		this.getUserInput();

		this.controller.readFile(inputFile);
		this.largestPrize = controller.findLargestPrize();

		System.out.println("Largest prize value is: " + this.largestPrize);
	}

	/**
	 * Gets the necessary input from the user
	 */
	private void getUserInput() {
		System.out.println("Input name of input file");
		this.inputFile = this.scanner.next();
		this.scanner.close();
	}

	/**
	 * Main method that serves as the entry point to the program.
	 * 
	 * @param args
	 *            The args passed in
	 */
	public static void main(String[] args) {
		TextUserInterface tui = new TextUserInterface();
		tui.runProgram();
	}

}
