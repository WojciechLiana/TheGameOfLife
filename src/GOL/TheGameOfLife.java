package GOL;

import java.util.*;
import java.io.*;

/**
 * @see <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">The Game of Life</a>
 * 
 * @author Wojtas 
 * @version 1.0
 */
public class TheGameOfLife {

	public static void main(String[] args) {
		
		Game GOL = new Game(15, 15);
		GOL.fillBoard();
		
		while(true)
		{
			GOL.display();
			GOL.updateBoard();
			Scanner sc = new Scanner(System.in);
			if(!sc.nextLine().equalsIgnoreCase("n"))
			break; 	                    
		}
	}
} 	

/**
 * Game rules: <br>
 * 1. Any live cell with fewer than two live neighbors 
 * dies, as if by underpopulation. <br>
 * 2. Any live cell with two or three live neighbors 
 * lives on to the next generation. <br>
 * 3. Any live cell with more than three live neighbors 
 * dies, as if by overpopulation. <br>
 * 4. Any dead cell with exactly three live neighbors 
 * becomes a live cell, as if by reproduction. <br>
 * 
 * @author Wojtas
 * @version 1.0
 * 
 */
class Game {
	
	/**
	 * number of columns
	 */
	int x;
	/**
	 * number of rows
	 */
	int y;
	
	/**
	 * board for current generation
	 */
	private boolean board[][];
	/**
	 * board for next generation
	 */
	private boolean board2[][];
	
	/**
	 * Creation of the board
	 * @param a columns
	 * @param b rows
	 */
	Game(int a, int b) 
	{
		this.x=a;
		this.y=b;
		board = new boolean[a][b];
		board2 = new boolean[a][b];
	}
	
	/**
	 * first generation (starting pattern)
	 */
	public void fillBoard(){
		board[5][5] = true; board[5][7] = true; board[5][9] = true;
		board[6][5] = true; board[6][9] = true;
		board[7][5] = true; board[7][9] = true;
		board[8][5] = true; board[8][9] = true;
		board[9][5] = true; board[9][7] = true; board[9][9] = true;
	}  
	
	/**
	 * method for checking number of alive neighbours for each cell and taking actions: <br>
	 * if cell is dead and has 3 alive neighbours, cell become alive else remain dead<br>
	 * if cell is alive and has 2 or 3 alive neighbours, cell remain alive else dies<br>
	 * @param c columns
	 * @param d rows
	 */
	public void check(int c, int d) 
	{
		int alive_cells = 0;
		for (int i=c-1; i<=c+1; i++)
		{
			for (int j=d-1; j<=d+1; j++)
			{
				if (i<0 || j<0)
					alive_cells = alive_cells;
				else if (i>x || j>y)
					alive_cells = alive_cells;
				else if(i==c && j==d)
					alive_cells = alive_cells;
				else if( i >=0 && i<x && j >=0 && j<y && board[i][j]==false )
					alive_cells = alive_cells;
				else if( i >=0 && i<x && j >=0 && j<y && board[i][j]==true )
					alive_cells += 1;
			}
		}
		
		if(board[c][d] == false)	// action for dead cells
		{  
			if (alive_cells == 3)
				board2[c][d] = true;  
			else
				board2[c][d] = false; 
		} 
		
		if(board[c][d] == true)  // action for alive cells
		{  
			if (alive_cells == 3 || alive_cells ==2)
				board2[c][d] = true;  
			else
				board2[c][d] = false; 
		} 
	}  

	/**
	 * method displays current board where '#' means alive cell
	 */
	public void display()
	{
		for(int i=0; i< x; i++)
		{
			for(int j=0; j< y; j++) 
			{
				if(board[i][j]==true)
					System.out.print("#");
				else
					System.out.print(" ");
		  		}
				System.out.println();
		}
	}
	
	/**
	 * method counting new generation using method {@link #check(int, int) check()}
	 * and moving next generation to board for current one
	 */
	public void updateBoard()
	{
	for(int i=0; i< x; i++)
		for(int j=0; j< y; j++) 
			this.check(i, j);
	
	for(int i=0; i< x; i++)
		for(int j=0; j< y; j++) 
			board[i][j]=board2[i][j];
	}
}
