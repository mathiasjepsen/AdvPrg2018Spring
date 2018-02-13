/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;

/**
 *
 * @author Tobias
 */
public interface TicTacToePlayer
{
    public void startNewGame(int playerID) throws IOException;
    public int takeTurn(TicTacToeBoard board, int[] validPositions) throws IOException;
    public void endGame(int result) throws IOException;
}
