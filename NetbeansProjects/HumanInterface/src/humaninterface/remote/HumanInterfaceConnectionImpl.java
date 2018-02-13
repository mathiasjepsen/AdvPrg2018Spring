package humaninterface.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import java.io.IOException;

/**
 *
 * @author mathiasjepsen
 */
public class HumanInterfaceConnectionImpl implements IHumanInterfaceConnection {
    
    private SocketConnection con;

    public HumanInterfaceConnectionImpl(SocketConnection con) {
        this.con = con;
    }

    @Override
    public void writeInt(int i) throws IOException {
        con.writeInt(i);
    }

    @Override
    public void writeStringArray(String[] array) throws IOException {
        for (String s : array) {
            con.writeUTF(s);
        }
    }

    @Override
    public void writeString(String str) throws IOException {
        con.writeUTF(str);
    }

    @Override
    public int readInt() throws IOException {
        return con.readInt();
    }

    @Override
    public String readString() throws IOException {
        return con.readUTF();
    }

    @Override
    public void close() throws IOException {
        con.close();
    }


    
    
}
