import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket clientSocket = new Socket("localhost", Server.S_PORT);
                BufferedReader socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                Scanner userInput = new Scanner(System.in)
        )
        {
//            listenToServer(socketReader);
            String str;
            while ((str = socketReader.readLine()) != null)
                System.out.println(str);
            String msg;
            while (!(msg = userInput.nextLine()).isEmpty()) {
                socketWriter.write(msg);
                socketWriter.newLine();
                socketWriter.flush();

                System.out.println("Сервер говорит: " + socketReader.readLine());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void listenToServer(BufferedReader source) throws IOException {
        String str;
        while ((str = source.readLine()) != null)
            System.out.println(str);
        System.out.println();
    }
}
