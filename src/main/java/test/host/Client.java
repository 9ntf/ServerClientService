package test.host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8099;
        Scanner scanner = new Scanner(System.in);

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            //Выводим первый запрос на имя
            System.out.println(in.readLine());
            //Отвечаем на первый запрос
            out.println(scanner.nextLine());
            //Получаем ответ с именем и портом
            System.out.println(in.readLine());
            //Выводим второй запрос на возраст
            System.out.println(in.readLine());
            //Отвечаем на второй запрос
            out.println(scanner.nextLine());
            //Результат выводим на экран
            System.out.println(in.readLine());

        } catch (IOException ex) {
            System.out.println("Client Exception: " + ex.getMessage());
        }
    }
}
