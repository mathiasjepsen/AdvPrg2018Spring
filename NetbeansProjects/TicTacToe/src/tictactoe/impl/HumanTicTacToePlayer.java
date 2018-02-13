/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl;

import humaninterface.impl.ConsoleTextualInterface;
import java.io.IOException;
import java.util.Random;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToePlayer;
import humaninterface.ITextualInterface;

/**
 *
 * @author Tobias
 */
public class HumanTicTacToePlayer implements TicTacToePlayer {

    private final ITextualInterface console;
    private final Random rnd;
    private int id;
    private String name;

    public HumanTicTacToePlayer() {
        rnd = new Random();
        console = new ConsoleTextualInterface();
    }

    @Override
    public void startNewGame(int playerID) throws IOException {
        id = playerID;
        name = console.askForString("What is your name?: ");
    }

    @Override
    public int takeTurn(TicTacToeBoard board, int[] validPositions) throws IOException {
        console.sendMessage(name + " taking turn:");
        printBoard(board);
        int pos = console.askForInteger("Choose a position: ");
        console.sendMessage("Play at: " + pos);
        console.sendMessage(name + "'s turn ended.\n\n");
        return pos;
    }

    @Override
    public void endGame(int result) {
        System.out.println(name + "'s result: " + result);
    }

    private void printBoard(TicTacToeBoard board) {
        System.out.print(board.get(0));
        System.out.print(board.get(1));
        System.out.println(board.get(2));
        System.out.print(board.get(3));
        System.out.print(board.get(4));
        System.out.println(board.get(5));
        System.out.print(board.get(6));
        System.out.print(board.get(7));
        System.out.println(board.get(8));
    }
}
