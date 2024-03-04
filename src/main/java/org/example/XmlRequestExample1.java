package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class XmlRequestExample1 {

    public static void main(String[] args) {
        String serverIP = "10.160.98.26";
        int serverPort = 5015;

        String xmlRequest = "<TRANSACTION>" +
                "<FUNCTION_GROUP>ADMIN</FUNCTION_GROUP>" +
                "<COMMAND>PING</COMMAND>" +
                "<SESSION_ID>4444</SESSION_ID>" +
                "<TRAINING_MODE>0</TRAINING_MODE>" +
                "</TRANSACTION>";

        try (Socket socket = new Socket(serverIP, serverPort);
             OutputStream out = socket.getOutputStream();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send XML request to the server
            out.write(xmlRequest.getBytes());
            out.flush();

            // Read and print the response
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println("Received response: " + responseLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
