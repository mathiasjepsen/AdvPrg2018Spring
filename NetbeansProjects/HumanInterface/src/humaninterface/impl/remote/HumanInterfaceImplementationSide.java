/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.impl.remote;

import java.io.IOException;
import humaninterface.ITextualInterface;
import humaninterface.remote.IHumanInterfaceConnection;

/**
 *
 * @author mathiasjepsen
 */
public class HumanInterfaceImplementationSide implements Runnable {

    private IHumanInterfaceConnection con;
    private ITextualInterface ti;

    public HumanInterfaceImplementationSide(IHumanInterfaceConnection con, ITextualInterface ti) {
        this.con = con;
        this.ti = ti;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String methodName = con.readString();
                switch (methodName) {
                    case "sendMessage":
                        ti.sendMessage(con.readString());
                        break;
                    case "askForString":
                        con.writeString(ti.askForString(con.readString()));
                        break;
                    case "askForPassword":
                        con.writeString(ti.askForPassword(con.readString()));
                        break;
                    case "askForEmail":
                        con.writeString(ti.askForEmail(con.readString()));
                        break;
                    case "askForInteger":
                        con.writeInt(ti.askForInteger(con.readString()));
                        break;
                    case "askForIntegerRange":
                        String question = con.readString();
                        int min = con.readInt();
                        int max = con.readInt();
                        con.writeInt(ti.askForIntegerMinMax(question, min, max));
                        break;
                    case "makeSingleChoice":
                        String question1 = con.readString();
                        int length = con.readInt();
                        String[] choices = new String[length];
                        for (int i = 0; i < length; i++) {
                            choices[i] = con.readString();
                        }
                        con.writeInt(ti.makeSingleChoice(question1, choices));
                        break;
                    case "close":
                        con.close();
                        return;
                    default:
                        throw new RuntimeException("Unknown method: " + methodName);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } 
        
    }

}
