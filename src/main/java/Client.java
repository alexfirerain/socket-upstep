import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String host = "netology.homework";
    public static void main(String[] args) {
        try (
                Socket clientSocket = new Socket(host, Server.S_PORT);
                BufferedReader socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                Scanner userInput = new Scanner(System.in)
        )
        {
            listenToServer(socketReader);
            System.out.println("So what");
//            String str;
//            while ((str = socketReader.readLine()) != null)
//                System.out.println(str);
            String msg = userInput.nextLine();
                socketWriter.write(msg);
                socketWriter.newLine();
                socketWriter.flush();

            System.out.println("Сервер говорит: " + socketReader.readLine());

            //            String msg;
//            while (!(msg = userInput.nextLine()).isEmpty()) {
//                socketWriter.write(msg);
//                socketWriter.newLine();
//                socketWriter.flush();
//
//                System.out.println("Сервер говорит: " + socketReader.readLine());
//            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void listenToServer(BufferedReader source) throws IOException {
        String str;
        while (!(str = source.readLine()).isEmpty())
            System.out.println(str);
        System.out.println();
    }
}
