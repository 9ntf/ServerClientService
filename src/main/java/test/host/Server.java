package test.host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8099;

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                System.out.println("New Connection accepted");
                //Запрашиваем имя
                out.println("Write your name");
                //Получаем имя и отправляем ответ с портом
                final String name = in.readLine();
                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                //Запрашиваем возраст
                out.println("You are older than 18?(yes/no)");
                //Получаем возраст, обрабатываем и отправляем ответ
                final String answer = in.readLine();
                if ("yes".equals(answer)) {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                } else if ("no".equals(answer)) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                } else {
                    out.println("Wrong answer");
                }
            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
            }
        }
    }
}
