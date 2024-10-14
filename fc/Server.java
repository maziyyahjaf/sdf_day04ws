package fc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    // java -cp fortunecookie.jar fc.Server 12345 cookie_file.txt
    public static void main(String[] args) throws IOException {

        int port = 12345;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        ServerSocket respondClientRequest = new ServerSocket(port);
        System.out.printf("Listening on port %d\r\n", port);

        System.out.println("Waiting for connection");

        // Wait for incoming connection, block
        Socket connectedToClient = respondClientRequest.accept();
        System.out.println("Got a client connection");

        // how to get the get_cookie command from terminal
        // from client to the server?
        // how is the info passed from terminal to the client
        // and from client to the server?

        // character? bytes?

        InputStream is = connectedToClient.getInputStream();
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        String clientRequest;

        // the while loop will continuously observe the input stream of the client for incoming instructions
        while ((clientRequest = br.readLine()) != null) {

            Cookie runningClientRequest = new Cookie();
            String randomCookieQuote;

            if (clientRequest.startsWith("close")) {
                connectedToClient.close();
                break;

            }

            if (clientRequest.startsWith("get-cookie")) {

                // getCookie is a field in the Cookie class
                // where do I initialise this?
                // now i have the random cookie quote
                randomCookieQuote = runningClientRequest.getCookie();
                System.out.println(randomCookieQuote);
                // i need to set a way for the server to send the
                // the random cookie text back to the client
                // with the prefix cookie-text
    
                // getting the output stream
                OutputStream os = connectedToClient.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(writer);
    
                bw.write("cookie-text" + randomCookieQuote + "\n");
                bw.flush();
    
            }
            respondClientRequest.close();

        }

       

    }

}
