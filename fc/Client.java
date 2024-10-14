package fc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    // java -cp fortunecookie.jar fc.Client localhost:12345;
    public static void main(String[] args) throws UnknownHostException, IOException {

        // get host:port
        String[] terms = args[0].split(":");
        String host = terms[0];
        Integer port = Integer.parseInt(terms[1]);

        Socket sendClientRequest = new Socket(host, port);
        System.out.printf("connected to server\r\n");

        // the terminal should always wait for input from the user to the client as
        // long as the input is not "close"

        // Getting the output stream
        OutputStream os = sendClientRequest.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        // I need a way to get client request from the terminal to the client
        Console console = System.console();

        while (true) {

            String clientRequest = console.readLine("Input request >>>>> ");

            bw.write(clientRequest + "\n");
            bw.flush();

            if (clientRequest.equals("close")){
                break;
            }

            // Getting the input stream
            InputStream is = sendClientRequest.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            String serverResponse = br.readLine();

            if (serverResponse.startsWith("cookie-text")) {
                System.out.println(serverResponse.substring(11));
            }

           

        }
        sendClientRequest.close();

    }

}
