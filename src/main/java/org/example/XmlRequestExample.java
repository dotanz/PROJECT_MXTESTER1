package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class XmlRequestExample {
    public static void main(String[] args) {
        String xmlData = "<TRANSACTION>\n" +
                "    <FUNCTION_GROUP>ADMIN</FUNCTION_GROUP>\n" +
                "    <COMMAND>PING</COMMAND>\n" +
                "    <SESSION_ID>4444</SESSION_ID>\n" +
                "    <TRAINING_MODE>0</TRAINING_MODE>\n" +
                "</TRANSACTION>";

        String remoteUrl = "http://10.160.98.26:5015";

        try {
            String response = sendXmlRequest(xmlData, remoteUrl);
            System.out.println("Response from remote payment device:\n" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String sendXmlRequest(String xmlData, String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set up the connection
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/xml");
        connection.setDoOutput(true);

        // Send the XML data
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = xmlData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Get the response
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
