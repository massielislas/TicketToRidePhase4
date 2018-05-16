<<<<<<< HEAD
package communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import clientModel.Command;
import clientResult.Result;

/**
 * Created by Topper on 5/16/2018.
 */

public class ClientCommunicator {
    private static final ClientCommunicator instance = new ClientCommunicator();
    private String port;
    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }
    private ClientCommunicator(){}

    public static ClientCommunicator getInstance(){
        return instance;
    }
    public Object send(String requestMethod, Command requestCommand)
    {
        try {
            URL url = new URL("http://" + host +":" +port);
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("Accept","application/json");
            connection.setDoOutput(true);
            connection.connect();
            writeString(Encoder.Encode(requestCommand),connection.getOutputStream());
            InputStream respBody = connection.getInputStream();
            String respData = readString(respBody);
            Object toReturn =Encoder.Decode(respData, Result.class);
            return toReturn;
        }
        catch (MalformedURLException e)
        {
            System.out.print(e.getStackTrace());
        }
        catch (IOException e)
        {
            System.out.print(e.getStackTrace());
        }
        return null;
    }
    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

}
=======
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
>>>>>>> a8039c440cefc5a524a1c6b0c92ffbd4f50b4668
