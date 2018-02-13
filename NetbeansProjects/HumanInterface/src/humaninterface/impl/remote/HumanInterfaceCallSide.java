package humaninterface.impl.remote;

import java.io.IOException;
import humaninterface.ITextualInterface;
import humaninterface.remote.IHumanInterfaceConnection;

/**
 *
 * @author mathiasjepsen
 */
public class HumanInterfaceCallSide implements ITextualInterface {
    
    private IHumanInterfaceConnection con;

    public HumanInterfaceCallSide(IHumanInterfaceConnection con) {
        this.con = con;
    }

    @Override
    public void sendMessage(String msg) throws IOException {
        con.writeString("sendMessage");
        con.writeString(msg);
    }

    @Override
    public String askForString(String question) throws IOException {
        con.writeString("askForString");
        con.writeString(question);
        return con.readString();
    }

    @Override
    public String askForPassword(String question) throws IOException {
        con.writeString("askForPassword");
        con.writeString(question);
        return con.readString();
    }

    @Override
    public String askForEmail(String question) throws IOException {
        con.writeString("askForEmail");
        con.writeString(question);
        return con.readString();
    }

    @Override
    public int askForInteger(String question) throws IOException {
        con.writeString("askForInteger");
        con.writeString(question);
        return con.readInt();
    }

    @Override
    public int askForIntegerMinMax(String question, int min, int max) throws IOException {
        con.writeString("askForIntegerRange");
        con.writeString(question);
        con.writeInt(min);
        con.writeInt(max);
        return con.readInt();
    }

    @Override
    public int makeSingleChoice(String question, String[] choices) throws IOException {
        con.writeString("makeSingleChoice");
        con.writeString(question);
        con.writeInt(choices.length);
        con.writeStringArray(choices);
        return con.readInt();
    }

    @Override
    public void close() throws IOException {
        con.writeString("close");
        con.close();
    }
    
    
    
}
