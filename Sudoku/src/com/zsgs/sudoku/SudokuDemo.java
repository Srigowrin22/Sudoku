package com.zsgs.sudoku;

import java.util.HashSet;

public class SudokuDemo {
	public static void main(String[] args) {
//		int[][] board = new int[][] {
//            { 1, 2, 0, 3, 0, 4, 0, 5, 6 },
//            { 7, 0, 0, 0, 0, 6, 0, 0, 1 },
//            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//            { 0, 8, 0, 4, 0, 9, 0, 2, 0 },
//            { 0, 0, 0, 0, 6, 0, 0, 0, 0 },
//            { 0, 3, 0, 5, 0, 1, 0, 8, 0 },
//            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//            { 9, 0, 0, 2, 0, 0, 0, 0, 8 },
//            { 8, 4, 0, 6, 0, 7, 0, 1, 9 }};
		
		int[][] board = new int[][] {
			{0, 0, 3, 0},
			{0, 0, 1, 0},
			{0, 0, 0, 1},
			{3, 0, 2, 0}};
                        
    	int n = board[0].length;
    	System.out.println("Question: Sudoku");
        printBoard(board, n);
        
        if(isValid(board, n)) {
	        if(solveSudoku(board, n)) {
				System.out.println("\n----SOLUTION----");
		        printBoard(board, n);
			}else {
				System.out.println("\nAnswer: Invalid sudoku");
			}
        } 
        else {
			System.out.println("\nAnswer: Invalid sudoku");
		}
	}

	private static boolean isValid(int board[][], int n) {
		HashSet<Integer> h = new HashSet<Integer>();
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(board[r][c] !=0 && h.contains(board[r][c])) {
					return false;
				}else {
					h.add(board[r][c]);
				}
			}
			h.clear();
		}
		return true;
		
	}

	private static void printBoard(int[][] board, int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println(" ");
		}
	}

	private static boolean solveSudoku(int[][] board, int n) {
		int row = -1;
		int col = -1;
		boolean isEmpty = false;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(board[i][j]==0) {
					row = i;
					col = j;
					isEmpty = true;
					break;
				}
			}
			if(isEmpty) {
				break;
			}
		}
		
		if(!isEmpty) {
			return true;
		}
		
		for(int num=1; num<=n; num++) {
			if(isSafe(board, n, row, col, num)) {
				board[row][col]=num;
				if(solveSudoku(board, n)) {
					return true;
				}
				else {
					board[row][col]=0;
				}
			}
			
		}
		return false;
	}

	private static boolean isSafe(int[][] board, int n, int row, int col, int num) {
		
		for(int r=0; r<n; r++) {
			if(board[r][col]==num) {
				return false;
			}
		}
		
		
		for(int c=0; c<n; c++) {
			if(board[row][c]==num) {
				return false;
			}
		}
		
		int sqrt = (int) Math.sqrt(n);
		int rowStart = row - row % sqrt;
		int colStart = col - col % sqrt;
		
		for(int i=rowStart; i<rowStart+sqrt; i++) {
			for(int j=colStart; j<colStart+sqrt; j++) {
				if(board[i][j]==num) {
					return false;
				}
			}
		}		
		return true;		
	}
}
