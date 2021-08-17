import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket clientSocket = new Socket("localhost", Server.S_PORT);
                BufferedReader inwards = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter outwards = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        )
        {
            Scanner userInput = new Scanner(System.in);
//            outwards.write("Хуй");
            String msg;
            while (!(msg = userInput.nextLine()).isEmpty()) {
                outwards.write(msg);
                outwards.newLine();
                outwards.flush();

                System.out.println("Server echo " + inwards.readLine());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
