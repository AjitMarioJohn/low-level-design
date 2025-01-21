package com.lld.practice.tictactoe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Board {

    private final PieceType[][] board;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.board = new PieceType[size][size];
    }
    
    public boolean placePiece(PieceType pieceType, int row, int column) {
        boolean placed = false;
        if (row >= 0 && row < size && column >= 0 && column < size && board[row][column] == null) {
            board[row][column] = pieceType;
            return true;
        }
        return placed;
    }

    public boolean isWinner(PieceType pieceType) {
        boolean columnMatch = true;
        boolean rowMatch = true;
        boolean leftDiagonalMatch = true;
        boolean rightDiagonalMatch = true;
        //check in all direction
        for (int i = 0; i < size; i++) {
            rowMatch = rowMatch && board[i][0] == pieceType;
            columnMatch = columnMatch && board[0][i] == pieceType;
            leftDiagonalMatch = leftDiagonalMatch && board[i][i] == pieceType;
            rightDiagonalMatch = rightDiagonalMatch && board[i][size - 1 - i] == pieceType;
        }

        return rowMatch || columnMatch || leftDiagonalMatch || rightDiagonalMatch;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    System.out.print("-");
                } else {
                    System.out.print(board[i][j].getShape());
                }
                if (j < size - 1) System.out.print("|");
            }
            System.out.println();
            if (i < size - 1) {
                System.out.println("-".repeat(size * 2 - 1));
            }
        }
    }
    
    

}
