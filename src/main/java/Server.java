import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;

    static final int S_PORT = 8111;

    public static void main(String[] args) throws IOException {

        serverSocket = new ServerSocket(S_PORT);

        try (
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.println("Establish a new connection accepted from: " +
                    clientSocket.getRemoteSocketAddress().toString());

            out.println("Hi, enter your name:");
            final String clientName = in.readLine();
            out.println(String.format("Hi %s, you've connected via port #%d",
                    clientName, clientSocket.getPort()));

            String clientsInput;
            while ((clientsInput = in.readLine()) != null) {
                System.out.println("receive: " + clientsInput);
                int x = Integer.parseInt(clientsInput);
                out.println(x + 1);
                System.out.println("send: " + (x + 1));
            }

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Server add: " + msg);
                out.write(msg + " received");
                out.flush();
            }

        }

    }
}
