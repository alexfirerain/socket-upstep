import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static final int S_PORT = 8111;
    static Logger logger = new Logger("log.log");
    static final String RYUSHECHKI = """
            _________________________________________
            |   |   |   |   |   |   |   |   |   |   |
            \\__/\\__/\\__/\\__/\\__/\\__/\\__/\\__/\\__/\\__/
            """;


    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(S_PORT);
        serverStatement("Привет, Сервер запустился и слушает.");

        try (
                Socket socket = serverSocket.accept();
                PrintWriter outSocket = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            serverStatement("Новое соединение по запросу от: " +
                    socket.getRemoteSocketAddress().toString() +
                    "\n установлено через разъём №" +
                    socket.getPort());


            outSocket.write(RYUSHECHKI + "Здравствуйте, мы Сервер с Рюшечками. Представьтесь:");
            outSocket.flush();
            final String clientName = inSocket.readLine();
            serverStatement("Клиент представился как " + clientName);

            outSocket.println(String.format("Привет, %s, мы соединились через разъём №%d",
                    clientName, socket.getPort()));

//            String clientsInput;
//            while ((clientsInput = in.readLine()) != null) {
//                System.out.println("receive: " + clientsInput);
//                int x = Integer.parseInt(clientsInput);
//                out.println(x + 1);
//                System.out.println("send: " + (x + 1));
//            }

            String msg;
            while ((msg = inSocket.readLine()) != null) {
                System.out.println("Сервер принял: " + msg);
                outSocket.write(msg + " отправил");
                outSocket.flush();
            }

            serverStatement("Сервер закрывается.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }

    }

    static void serverStatement(String message) {
        logger.log(message);
        System.out.println(message);
    }
}
