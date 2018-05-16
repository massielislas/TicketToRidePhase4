package communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientCommunicator {

    public static ClientCommunicator client = new ClientCommunicator();

    public static ClientCommunicator getClient() {
        return client;
    }

    public String post(URL url, String data)
    {
        System.out.println("url: " + url + "\ndata: " + data);
        try
        {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true); //request body!
            http.addRequestProperty("Accept", "application/json");
            http.connect();
            PrintWriter out = new PrintWriter(http.getOutputStream());
            out.write(data);
            //System.out.println(data);
            out.flush();
            out.close();
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                return readString(http.getInputStream());
            }
            else
            {
                System.out.println("Error in post response\nCode: " + http.getResponseMessage());
                return null;
            }
        }
        catch(IOException error)
        {
            System.out.println("Error in post method!\n" + error.getStackTrace());
            return null;
        }
    }

    public String get(URL url)
    {
        try
        {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(false); //no request body
            http.addRequestProperty("Accept", "application/json");
            http.connect();
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                return readString(http.getInputStream());
            }
            else
            {
                System.out.println("Error in getResponseCode: " + http.getResponseMessage());
                return null;
            }
        }
        catch(IOException error)
        {
            System.out.println("Error in post method!\n" + error.getStackTrace());
            return null;
        }
    }

    private String readString(InputStream inputStream) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(inputStream);
        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
