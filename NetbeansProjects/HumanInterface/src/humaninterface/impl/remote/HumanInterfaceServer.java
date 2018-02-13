/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.impl.remote;

import dk.tobiasgrundtvig.util.socket.ConnectionHandler;
import dk.tobiasgrundtvig.util.socket.SocketConnection;
import dk.tobiasgrundtvig.util.socket.impl.Server;
import humaninterface.impl.ConsoleTextualInterface;
import humaninterface.remote.HumanInterfaceConnectionImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import humaninterface.ITextualInterface;

/**
 *
 * @author mathiasjepsen
 */
public class HumanInterfaceServer implements ConnectionHandler {

    private HumanInterfaceCallSide h;

    public static void main(String[] args) {
        Server server = new Server(new HumanInterfaceServer(), 3456);
        server.run();
    }

    public HumanInterfaceServer() {
        h = null;
    }

    @Override
    public void handleConnection(SocketConnection conn) {
        System.out.println("Handling a new connection");
        if (h == null) {
            h = new HumanInterfaceCallSide(new HumanInterfaceConnectionImpl(conn));
        }

        new Thread(() -> {
            try {
                h.sendMessage("Welcome to the server.\n");
                while (true) {
                    String name = h.askForString("What is your name?: ");
                    int age = h.askForInteger("What is your age?: ");
                    String password = h.askForPassword("What is your password?: ");
                    String email = h.askForEmail("What is your e-mail?: ");
                    String[] food = {"Banana", "Ice cream", "Tomato", "Grapes", "Apple"};
                    int foodChoiceIndex = h.makeSingleChoice("What is your favorite food?", food);
                    h.sendMessage("Thank you very much " + name + ".\n");
                    h.sendMessage("Your age is " + age + ", your e-mail is " + email + " and your favorite food is " + food[foodChoiceIndex] + ".\n");
                    h.sendMessage("Shutting down.");
                    h.close();
                    h = null;
                    break;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(HumanInterfaceServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }

}
