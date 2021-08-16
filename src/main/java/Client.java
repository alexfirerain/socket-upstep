import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (
                Socket clientSocket = new Socket("localhost", Server.S_PORT);
                BufferedReader inwards = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter outwards = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        )
        {
            outwards.write("Хуй");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
