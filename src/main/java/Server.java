import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static final int S_PORT = 8111;
    static Logger logger = new Logger("log.log", false);
    static final String RYUSHECHKI = """
            _________________________________________
            |   |   |   |   |   |   |   |   |   |   |
            \\__/\\__/\\__/\\__/\\__/\\__/\\__/\\__/\\__/\\__/
            """;


    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(S_PORT);
        serverStatement("Привет, Сервер запустился и слушает.");
        String clientName;
        int clientAge;
        String clientPayment;
        Customer client;

        try (
                Socket socket = serverSocket.accept();
                PrintWriter outSocket = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            serverStatement("Новое соединение по запросу от: " +
                    socket.getRemoteSocketAddress().toString() +
                    "\n установлено через разъём №" +
                    socket.getPort());

//            outSocket.println(RYUSHECHKI);
            outSocket.println("Здравствуйте, мы Сервер с Рюшечками. Представьтесь:\r\n");

            clientName = inSocket.readLine();
            serverStatement("Клиент представился как " + clientName);

            outSocket.println(String.format("Привет, %s, мы соединились через разъём №%d",
                    clientName, socket.getPort()));

            client = new Customer(clientName);
            outSocket.println(clientName + ", добро пожаловать в наше заведение!\n" +
                    "Для выхода введите '-'.\n" +
                    "Введите ваш возраст: \r\n");
            String ageStr = inSocket.readLine();

            boolean canceled = ageStr.equals("-");

            clientAge = Integer.parseInt(ageStr);
            client.setAge(clientAge);
            serverStatement(clientName + " " + clientAge + " лет." +
                    " Категория " + client.getCategory().toString());

            switch (client.getCategory()) {
                case KID -> {
                    outSocket.println("Вы зачислены в комнату для детей");
                    client.setPayment("хорошее настроение");
                }
                case ADULT -> {
                    outSocket.println("Вы зачислены в комнату для взрослых");
                    outSocket.println("Какой платёж вы готовы делать ежемесячно?\r\n");
                    clientPayment = inSocket.readLine();
                    client.setPayment(clientPayment);
                    if (clientPayment.equals("-")) canceled = true;
                }
                case SENIOR -> {
                    outSocket.println("Вы зачислены в комнату для старших");
                    client.setPayment("мудрые советы");
                }
            }

            if (!canceled) {
                outSocket.println("Поздравляем!\n" +
                        "Вот ваша карточка:\n" + client);
                serverStatement("Карта заведена");
            } else {
                outSocket.println("Отмана");
                serverStatement("Карта не заведена");
            }
            outSocket.println("До свидания!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
        serverStatement("Сервер закрывается.");
    }

    static void serverStatement(String message) {
        logger.log(message);
        System.out.println(message);
    }
}
