/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author Tobias
 */
public interface ITextualInterface extends Closeable
{
    public void sendMessage(String msg) throws IOException;
    public String askForString(String question) throws IOException;
    public String askForPassword(String question) throws IOException;
    public String askForEmail(String question) throws IOException;
    public int askForInteger(String question) throws IOException;
    public int askForIntegerMinMax(String question, int min, int max) throws IOException;
    public int makeSingleChoice(String question, String[] choices) throws IOException;
}
