import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    // %WINDIR%/System32/drivers/etc/hosts содержит строку "127.0.0.1   netology.homework"
    private static final String host = "netology.homework";

    public static void main(String[] args) {
        try (
                Socket clientSocket = new Socket(host, Server.S_PORT);
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter toServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                Scanner console = new Scanner(System.in)
        ) {


            listenToServer(fromServer);

            String usersInput;
            while (clientSocket.isConnected()) { //  && !usersInput.equals("-") ?

                usersInput = console.nextLine();

                toServer.write(usersInput);
                toServer.newLine();
                toServer.flush();

                listenToServer(fromServer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        static void listenToServer(BufferedReader source) throws IOException {
            String str;
            do {
                str = source.readLine();
                System.out.println(str);
            } while (!str.isEmpty() && !str.endsWith("\r\n"));
    }
}
