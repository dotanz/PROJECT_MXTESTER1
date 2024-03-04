package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class XmlRequestExample2 {

    public static void main(String[] args) {
        String xmlRequest = "<TRANSACTION>\n" +
                "    <FUNCTION_GROUP>ADMIN</FUNCTION_GROUP>\n" +
                "    <COMMAND>PING</COMMAND>\n" +
                "    <SESSION_ID>4444</SESSION_ID>\n" +
                "    <TRAINING_MODE>0</TRAINING_MODE>\n" +
                "</TRANSACTION>";

        String ipAddress = "10.160.98.26";
        int port = 5015;

        try (Socket socket = new Socket(ipAddress, port);
             OutputStream out = socket.getOutputStream();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {

            // Send the XML request
            out.write(xmlRequest.getBytes(StandardCharsets.UTF_8));
            out.flush();

            System.out.println("XML request sent to the remote terminal:");

            // Read the response
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
