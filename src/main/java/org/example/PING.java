package org.example;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PING {

    public static void main(String[] args) {
        String xml = "<TRANSACTION><FUNCTION_GROUP>ADMIN</FUNCTION_GROUP><COMMAND>PING</COMMAND><SESSION_ID>DB_Mx_to_Server</SESSION_ID><TRAINING_MODE>0</TRAINING_MODE></TRANSACTION>";

        String ipAddress = "10.160.98.26";
        int port = 5015;

        try {
            sendXml(xml, ipAddress, port);
            System.out.println("XML sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendXml(String xml, String ipAddress, int port) throws Exception {
        URL url = new URL("http://" + ipAddress + ":" + port);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the necessary headers
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/xml");
        connection.setDoOutput(true);

        // Write the XML data to the connection's output stream
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = xml.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Get the response code
        String responseCode = connection.getResponseMessage();
        System.out.println(responseCode);

        /*
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Successful request
            System.out.println("Server response: " + connection.getResponseMessage());
        } else {
            // Handle error
            System.out.println("Error sending XML. Response code: " + responseCode);
        }

         */

        // Disconnect
        connection.disconnect();
    }
}
