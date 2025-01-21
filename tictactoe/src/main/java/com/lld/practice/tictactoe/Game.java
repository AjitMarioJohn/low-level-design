package com.lld.practice.tictactoe;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Scanner;


@Slf4j
public class Game {

    /**
     * Starts the Tic Tac Toe game by initializing the board, creating players, and facilitating the game loop.
     * Continuously prompts the user to input the board size until a valid size is entered.
     */
    public void start() {
        while (true) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Input the size of board");
                int size;
                do {
                    System.out.println("Input the size of the board (must be greater than 0):");
                    size = scanner.nextInt();
                } while (size <= 0);
                Board board = new Board(size);
                Player[] players = createPlayers(scanner);
                startMoves(players, scanner, board, size);
                break;
            } catch (GameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Starts the game moves and handles the turn-based logic for the players.
     *
     * @param players The array containing the two players.
     * @param scanner A Scanner object for reading user input.
     * @param board   The game board on which moves are made.
     * @param size    The size of the game board.
     */
    private void startMoves(Player[] players, Scanner scanner, Board board, int size) {
        for (int turn = 0; ; turn++) {
            Player currentPlayer = players[turn % players.length];
            System.out.printf("%s's turn (%s)%n", currentPlayer.getName(), currentPlayer.getPieceType().getShape());
            System.out.println("Enter row and column to place your piece:");
            int row = scanner.nextInt();
            int column = scanner.nextInt();

            // Attempt to place the piece
            if (!board.placePiece(currentPlayer.getPieceType(), row, column)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board.printBoard();
            if (board.isWinner(currentPlayer.getPieceType())) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }
            if (turn == size * size - 1) { // All positions filled, it's a draw
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    /**
     * Creates two players for the game and assigns their respective piece types.
     *
     * @param scanner A Scanner object for reading player names.
     * @return An array containing two Player objects.
     * @throws GameException if any of the player names are empty.
     */
    private Player[] createPlayers(Scanner scanner) {
        System.out.println("Input the name of player 1");
        String playerOneName = scanner.next();
        if (ObjectUtils.isEmpty(playerOneName)) {
            throw new GameException("Player name can not be empty");
        }
        System.out.println("Input the name of player 2");
        String playerTwoName = scanner.next();
        if (ObjectUtils.isEmpty(playerOneName)) {
            throw new GameException("Player name cannot be empty");
        }
        if (ObjectUtils.isEmpty(playerTwoName)) {
            throw new GameException("Player name cannot be empty");
        }
        Player playerOne = new Player();
        playerOne.setName(playerOneName);
        playerOne.setPieceType(PieceType.X);
        Player playerTwo = new Player();
        playerTwo.setName(playerTwoName);
        playerTwo.setPieceType(PieceType.O);
        System.out.printf("Assigning %s to %s and %s to %s%n", PieceType.X, playerOneName, PieceType.O, playerTwoName);

        return new Player[]{playerOne, playerTwo};
    }

}
