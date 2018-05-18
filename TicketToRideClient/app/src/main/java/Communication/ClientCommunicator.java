package Communication;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import Results.Result;

public class ClientCommunicator {

    public static ClientCommunicator client = new ClientCommunicator();

    public static ClientCommunicator getClient() {
        return client;
    }

//    public String post(URL url, String data)
//    {
//        System.out.println("url: " + url + "\ndata: " + data);
//        try
//        {
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            http.setRequestMethod("POST");
//            http.setDoOutput(true); //request body!
//            http.addRequestProperty("Accept", "application/json");
//            http.connect();
//            PrintWriter out = new PrintWriter(http.getOutputStream());
//            out.write(data);
//            //System.out.println(data);
//            out.flush();
//            out.close();
//            if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
//            {
//                return readString(http.getInputStream());
//            }
//            else
//            {
//                System.out.println("Error in post response\nCode: " + http.getResponseMessage());
//                return null;
//            }
//        }
//        catch(IOException error)
//        {
//            System.out.println("Error in post method!\n" + error.getStackTrace());
//            return null;
//        }
//    }

    public String post(Object[]objects){
        PostTask postTask = new PostTask();
        try {
            postTask.execute(objects).get();
        }
        catch (Exception e) {
            Encoder encoder = new Encoder();

            return encoder.Encode(new Result(false, "Exception while running async task: " + e.getMessage()));
        }
        System.out.println("in post, after execute post task, objects[2]" + (String)(objects[2]));
        return (String) (objects[2]);
    }
    /**
     * First parameter is the URL
     * second parameter is the data string
     * third parameter is the return string
     */
    public class PostTask extends AsyncTask<Object, Void, Void>{
        @Override
        protected Void doInBackground(Object... objects) {
            System.out.println("url: " + ((URL) objects[0]).toString() + "\ndata: " + (String) objects[1]);
            try
            {
                System.out.println("in try");
                URL testURL = (URL) objects[0];
                System.out.println("testURL: " + testURL.toString());
                //HttpURLConnection http = (HttpURLConnection) ((URL) objects[0]).openConnection();

                HttpURLConnection http = (HttpURLConnection) testURL.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true); //request body!
                http.addRequestProperty("Accept", "application/json");
                System.out.println("before connect");
                http.connect();
                System.out.println("after connect");
                PrintWriter out = new PrintWriter(http.getOutputStream());
                out.write(((String) objects[1]));
                //System.out.println(data);
                out.flush();
                out.close();
                System.out.println("closed");
                if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    System.out.println("HTTP_OK");
                    objects[2] = readString(http.getInputStream());
                }
                else
                {
                    System.out.println("Error in post response\nCode: " + http.getResponseMessage());
                    objects[2] = readString(http.getInputStream());
                }
            }
            catch(IOException error)
            {
                System.out.println("Error in post method!\n" + error.getMessage());
                System.out.print("Cause: ");
                System.out.println(error.getCause());
                System.out.println("stack trace: ");
                System.out.println(error.getStackTrace());
                System.out.println(error.getLocalizedMessage());
                //System.out.println("suppresed: ");
                //System.out.print(error.getSuppressed());
                Result result = new Result(false, "IOException: " + error);
                objects[2] = result;
            }

            return null;
        }
    }


//    public String get(URL url)
//    {
//        try
//        {
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            http.setRequestMethod("GET");
//            http.setDoOutput(false); //no request body
//            http.addRequestProperty("Accept", "application/json");
//            http.connect();
//            if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
//            {
//                return readString(http.getInputStream());
//            }
//            else
//            {
//                System.out.println("Error in getResponseCode: " + http.getResponseMessage());
//                return null;
//            }
//        }
//        catch(IOException error)
//        {
//            System.out.println("Error in post method!\n" + error.getStackTrace());
//            return null;
//        }
//    }

    /**
     * First parameter is the URL, second parameter is the return string
     */
    private class GetTask extends AsyncTask<Object, Void, Void>{
        @Override
        protected Void doInBackground(Object... objects) {
            try {
                HttpURLConnection http = (HttpURLConnection) ((URL)objects[0]).openConnection();
                http.setRequestMethod("GET");
                http.setDoOutput(false); //no request body
                http.addRequestProperty("Accept", "application/json");
                http.connect();
                if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    objects[1] = readString(http.getInputStream());
                }
                else {
                    System.out.println("Error in getResponseCode: " + http.getResponseMessage());
                    objects[1] = readString(http.getInputStream());
                }
            }
            catch(IOException error) {
                System.out.println("Error in post method!\n" + error.getStackTrace());
                Result result = new Result(false, "IOException: " + error);
                objects[1] = result;
            }
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
