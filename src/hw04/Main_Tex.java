package hw04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * The Main Class will be able to read the file and store it data, then it will
 * execute solving the puzzle using designed method.
 * 
 * @author dtnng, 402005276, 2013-07
 */

public class Main_Tex {
	/** this {@code integer} is to store the solution number */
	private static int solutionNumber;
	/** this {@code integer} is to store the total solution */
	private static int solution;

	public static void main(String[] args)
	{
		int n;
		File input = null;
		do {
			/** Minor option prompt for quick check*/
			System.out.println("Choose option tile set # (1,2,3): ");
			Scanner sc = new Scanner(System.in);
			n = sc.nextInt();
			
			/** Read a file*/
			if(n == 1)
				input = new File("src\\files\\tileset1-1.txt");
			else if(n == 2)
				input = new File("src\\files\\tileset2-1.txt");
			else if(n == 3)
				input = new File("src\\files\\tileset3-1.txt");
			else
				n = -1;
			//sc.close();
			if(n!=-1)
			{
				/** Create an array of shapes to represent 7 tiles */
				HexagonShape[] tiles = new HexagonShape[7]; 
				int index = 0;
				/** Reading the file and store its data into each tiles */
				try {
					Scanner scan = new Scanner(input);
					while(scan.hasNextLine())
					{
						String word = scan.next();
						char ch = word.charAt(0);
						if(ch == 'R' || ch == 'B' || ch == 'Y' || ch == 'G' || ch == 'O' || ch == 'P' )
						{
							String[] tokens = word.split(",");
							tiles[index] = new HexagonShape(tokens);
							index++;
						}								
					}	
					scan.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/** Create a empty 2D array character board to represent tiles 
				 * and its position */
				char[][] board = new char[7][7];
				/** Initially fill all the board with * to represent empty space 
				 * */
				initBoard(board);
				/** Perform a Solving method, excluding print for option file 2*/
				if(n == 2)
					rotateTile(tiles ,0,0, board,false);
				else
					rotateTile(tiles ,0,0, board,true);
				/** Print out a total solution*/
				if(solution == 0)
					System.out.println("No Solution Found!");
				else
					System.out.println("Total Solution: " + solution);	
				solution = 0;
				solutionNumber = 0;
			}
						
		}while(n != -1);
		System.out.println("Exit Program");			
	}
	/**
	 * This method will fill an 2D array of char with * to represent empty space
	 * @param board						Take in a 2D array character
	 */
	public static void initBoard(char[][] board) {
		for (int i = 0 ; i < board.length ; ++i) {
			for (int j = 0 ; j < board[i].length ; ++j) {
				board[i][j] = '*';
			}
		}
	}
	
	/** 
	 * This method will check the color of adjacent tiles and return boolean
	 * @param a							All array HexagonShape of tiles
	 * @param position					The current tile position that need 
	 * 									compare
	 * @param tileNumber				The current tile number
	 * @param board						the 2D array board represent tiles number 
	 * 									and position
	 * @return							Return True if match color, false if not
	 */
	public static boolean checkColor(HexagonShape[] a, int position,int tileNumber, char[][] board)
	{
		/** Create an array of integer to get a tile number of position need to 
		 * be compare, it will require to call another method */
		int[] number = checkColorQ(board,position); 
		/** At position 1, it will always since we don't rotate or check color 
		 * for any tile at position 1*/
		if(position == 0) {
			return true;
		}
		/** For other positions, we will compare color with its adjacent tiles 
		 * in a specific order, and return true or false*/
		else if(position == 1)
		{
			if(a[tileNumber].getColor(3).equals(a[number[0]].getColor(0)))
			{
				return true;
			}
			else 
				return false;
		}
		else if(position == 2)
		{
			if(a[tileNumber].getColor(5).equals(a[number[0]].getColor(2)) && a[tileNumber].getColor(4).equals(a[number[1]].getColor(1)))
			{				
				return true;
			}
			else 
				return false;
		}
		else if(position == 3)
		{
			if(a[tileNumber].getColor(0).equals(a[number[0]].getColor(3)) && a[tileNumber].getColor(5).equals(a[number[1]].getColor(2)))
			{
				return true;
			}
			else 
				return false;
		}
		else if(position == 4)
		{
			if(a[tileNumber].getColor(1).equals(a[number[0]].getColor(4) )&& a[tileNumber].getColor(0).equals( a[number[1]].getColor(3)))
			{
				return true;
			}
			else 
				return false;
		}
		else if(position == 5)
		{
			if(a[tileNumber].getColor(2).equals(a[number[0]].getColor(5)) && a[tileNumber].getColor(1).equals(a[number[1]].getColor(4)))
			{
				return true;
			}
			else 
				return false;
		}
		else if(position == 6)
		{
			if(a[tileNumber].getColor(1).equals(a[number[1]].getColor(4)) && a[tileNumber].getColor(2).equals(a[number[2]].getColor(5)) &&  a[tileNumber].getColor(3).equals(a[number[0]].getColor(0)))
			{
				return true;
			}
			else 
				return false;
		}
		return false;
	}
	/**
	 * This method will help @method checkColor() to get a tile number of 
	 * require position and return its number to an array of integer
	 * @param board						the 2D array board represent tiles number 
	 * 									and position 
	 * @param position					The current position of tiles that need
	 * 									to compare
	 * @return							Return an array of tiles number of 
	 * 									require position
	 */
	public static int[] checkColorQ(char[][] board, int position) // The number of 
	{
		/** Create an empty integer array to store data*/
		int[] number = new int[3];
		int index = 0;
		/** We will find the require data by finding the Q in a 2D board rows 
		 * which represent its position and return the column index number which 
		 * represent the tiles number*/
		if(position == 1)
		{
			for (int col = 0; col < board[0].length; col++) {			
				if(board[0][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
		}
		else if(position == 2)
		{
			for (int col = 0; col < board[1].length; col++) {
				if(board[1][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
			for (int col = 0; col < board[0].length; col++) {
				if(board[0][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
		}
		else if(position == 3)
		{
			for (int col = 0; col < board[2].length; col++) {
				if(board[2][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
			for (int col = 0; col < board[0].length; col++) {
				if(board[0][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
		}
		else if(position == 4)
		{
			for (int col = 0; col < board[3].length; col++) {
				if(board[3][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
			for (int col = 0; col < board[0].length; col++) {
				if(board[0][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
		}
		else if(position == 5)
		{
			
			for (int col = 0; col < board[4].length; col++) {
				if(board[4][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
			for (int col = 0; col < board[0].length; col++) {
				if(board[0][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			}
		}
		else if(position == 6)
		{
			for (int col = 0; col < board[5].length; col++) {
				if(board[5][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			} 
			for (int col = 0; col < board[1].length; col++) {
				if(board[1][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			}
			for (int col = 0; col < board[0].length; col++) {
				if(board[0][col] == 'Q')
				{
					number[index] = col;
					index++;
				}
			}
		}
		return number;
	}
	
	/** 
	 * This method will print each solution when found
	 * @param solutionNumber				The solution number
	 * @param s								All array HexagonShape of tiles
	 * @param board							the 2D array board represent tiles 
	 * 										number and position 
	 */	
	public static void printSolution(int solutionNumber, HexagonShape[] s,char[][] board)
	{
		System.out.print("Solution #" + solutionNumber);
		for (int n = 0; n < 58; n++)
			System.out.print("-");
		System.out.println("\n\t\t\tSA\tSB\tSC\tSD\tSE\tSF");
		/** This method will rely on the 2D array board to find its tile number 
		 * and corresponding position */
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < board[i].length;j++)
			{
				if(board[i][j] == 'Q')
				{
					System.out.print("Postion " + (i+1) +  ": Tile #" + (j+1));
					for(int m = 0; m < 6; m++)
					{
						System.out.print("\t" + s[j].getColor(m));
					}
					System.out.print("\n");
				}
			}
		}
		for (int n = 0; n < 70; n++)
			System.out.print("-");
		System.out.print("\n");
		
	}
	/**
	 * This method to check the position of Q is valid by compare all position 
	 * in a column if there is a existing Q, it will represent if the tiles 
	 * number if already place on other position or not
	 * @param currentCol				The current tiles number
	 * @param board						the 2D array board represent tiles 
	 * 									number and position 
	 * @return							Return false if find other Q in the same 
	 * 									column, else return true
	 */
	
	public static boolean checkTiles(int currentCol,char[][] board)
	{
		for (int i = 0 ; i < board.length ; i++) {
			if (board[i][currentCol] == 'Q') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This recursion method will perform the main solving 
	 * @param s						All array HexagonShape of tiles			
	 * @param currentRow			Current position
	 * @param posSol				the number of tiles already placed 
	 * @print						boolean to print or not print
	 * @param board					the 2D array board represent tiles 
 * 									number and position 
	 */
	public static void rotateTile(HexagonShape[] s, int currentRow, int posSol,char[][] board,boolean print)
	{
		/** Base case, when has place all Q in 7 positions*/
		if(posSol == board.length) 
		{				
			solution++;
			solutionNumber++;
			/** Print Solution every time find solution */
			if(print)
				printSolution(solutionNumber,s,board);			
		}
		/** Recursive */
		else
		{		
			/** For loop to check every column which prereset current tile 
			 * number
			 * */
			for(int currentColumn  = 0; currentColumn < board[currentRow].length; currentColumn++)
			{	
				/** Check if the this tiles number is already placed*/
				if(checkTiles(currentColumn,board)) 
				{	
					/** The number of time able to rotate color in a tile*/
					for(int n = 0; n < 6; n++)
					{
						/** Check if the color match other adjacent tiles*/
						if(checkColor(s,currentRow,currentColumn,board))
						{	
							/** Place Q in 2D array board represent found match 
							 * for current position */
							board[currentRow][currentColumn] = 'Q';
							/** Add one Q being placed*/
							posSol++;
							/** Call recursive, and add current row to 1 
							 * represent proceed to the next position*/
							rotateTile(s ,currentRow + 1, posSol, board,print);
							/** Backtracking part, replace it will * represent 
							 * empty space*/
							board[currentRow][currentColumn] = '*';
							/** Remove one Q being placed */
							posSol--;
						}	
						/** This condition make sure whatever tiles in the 
						 * center don't rotate*/
						if(currentRow > 0)
							s[currentColumn].rotateColor();
						/** This condition make sure tile number at the center 
						 * being called on time only, then replace it with a 
						 * next tile number*/
						if(currentRow == 0)
							break;
					}
				}
			}
		}
	}
}
