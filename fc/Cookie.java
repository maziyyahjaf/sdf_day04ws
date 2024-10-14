package fc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {

    private String storedCookieText;
    private String directory;
    List<String> cookieText;

    public Cookie() {
        storedCookieText = "cookie_file.txt";
        directory = "data";
        cookieText = new ArrayList<>();

    }

    public String getCookie() throws IOException {
        // when server receives the get_cookie command from the client
        // server will call the getCookie method
        // getCookie will open the file, randomly select a line of in the cookie
        // text file

        String dirPathFileName = directory + File.separator + storedCookieText;
        File storedCookieTextFile = new File(dirPathFileName);
     
        if (storedCookieTextFile.exists()) {
            // i need to read a RANDOM line in the cookie text file
            // return that line to the server
            readCookieText(storedCookieTextFile);

        } else {
            System.out.println("File does not exist");
        }

        String randomCookieQuote = retrieveCookieText(cookieText);

        // do i need to consider if there is no file?
        return randomCookieQuote;

    }

    public void readCookieText(File storedCookieTextFile) throws IOException {

        // if cookie text  file exists, i will open the file and read the file
        // add the contents of the cookie text file to an array list
        // idea is that one line is stored in one string??
        // using a random number to call a random index to get a random line of cookie text
        FileReader reader = new FileReader(storedCookieTextFile);
        BufferedReader br = new BufferedReader(reader);
        String line;


        while (((line = br.readLine()) != null)) {
            cookieText.add(line);
        }

        retrieveCookieText(cookieText);

        // what if directory and file name changes?

        br.close();

    }

    public String retrieveCookieText(List<String> cookieText) {

        String randomCookieQuote = "";

        if (cookieText.isEmpty()) {
            System.out.println("no text");

        } else {

            Random random = new Random();
            int randomIndex = random.nextInt(cookieText.size());
            randomCookieQuote = cookieText.get(randomIndex);
        }

        return randomCookieQuote;


    }

}
