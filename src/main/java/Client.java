import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    private static final String host = "netology.homework";

    final static InetSocketAddress socketAddress = new InetSocketAddress(host, Server.S_PORT);
// подключаемся к серверу

    public static void main(String[] args) {
        try (

                final SocketChannel socketChannel = SocketChannel.open();
//                Socket clientSocket = new Socket(host, Server.S_PORT);
//                BufferedReader socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                Scanner userInput = new Scanner(System.in)
        )
        {
            socketChannel.connect(socketAddress);

//            listenToServer(socketReader);
            System.out.println("So what");
            String str;

            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

            while ((str = socketChannel.read(inputBuffer)) != -1)
                System.out.println(str);

            String msg = userInput.nextLine();
                socketChannel.write(msg);
                socketChannel.newLine();
                socketChannel.flush();

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
