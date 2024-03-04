package org.example;

import java.io.*;
import java.net.*;

public class XmlRequestExample4 {
    public static void main(String[] args) {
        try {
            // Connect to the remote device
            Socket socket = new Socket("10.160.98.26", 5015);

            // Send the XML request
            OutputStream os = socket.getOutputStream();
            os.write("<TRANSACTION><FUNCTION_GROUP>ADMIN</FUNCTION_GROUP><COMMAND>PING</COMMAND><SESSION_ID>4444</SESSION_ID><TRAINING_MODE>0</TRAINING_MODE></TRANSACTION>".getBytes());

            // Receive the hexadecimal response
            InputStream is = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
                System.out.println(baos);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
