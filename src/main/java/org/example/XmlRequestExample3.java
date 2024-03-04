package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class XmlRequestExample3 {

    public static void main(String[] args) {
        // Your XML request
        String xmlRequest = "<TRANSACTION>\n" +
                "    <FUNCTION_GROUP>ADMIN</FUNCTION_GROUP>\n" +
                "    <COMMAND>PING</COMMAND>\n" +
                "    <SESSION_ID>4444</SESSION_ID>\n" +
                "    <TRAINING_MODE>0</TRAINING_MODE>\n" +
                "</TRANSACTION>";

        try (Socket socket = new Socket("10.160.98.26", 5015)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send XML request to the server
            out.println(xmlRequest);

            // Socket socket1 = new Socket("12.165.98.26", 5015);
            System.out.println("Local Port: " + socket.getLocalPort());

            // Read and print the response
            String response;
            while ((response = in.readLine()) != "0") {
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}