package com.morton.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author MortonShaw
 * @date 2021/7/25 17:44
 */
public class RawHTTPServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        // Main Thread
        while (true) {
            // Blocking
            // Thread ---> Sleep ---> Other Threads
            Socket socket = serverSocket.accept();
            System.out.println("A socket created");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while (!(line = bufferedReader.readLine()).isEmpty()) {
                stringBuilder.append(line).append("\n");
            }

            String request = stringBuilder.toString();
            System.out.println(request);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("HTTP/1.1 200 ok\n\nHello World!\n");
            bufferedWriter.flush();
            socket.close();
        }
    }

}
