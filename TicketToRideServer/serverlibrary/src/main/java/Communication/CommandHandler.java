package Communication;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import Model.Command;
import Results.IResult;
import Results.Result;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;


/**
 * Created by Master_Chief on 5/16/2018.
 */

public class CommandHandler implements HttpHandler {

    public void handle(HttpExchange exchange) {
        try {
            System.out.println("HELLO");
            InputStream IS = exchange.getRequestBody();
            Encoder encode = new Encoder();

            String JSON = readString(IS);

            Gson gs = new Gson();
            Command toExecute = (Command) gs.fromJson(JSON, Command.class);

           //// Command toExecute = (Command) encode.Decode(JSON, Command.class);
            Object o = toExecute.Execute();
            IResult resultO = (IResult) o;

            if (resultO.isSuccess()) {
                System.out.println("Success");
                exchange.sendResponseHeaders(HTTP_OK, 0);
                resultO.setSuccess(true);
                resultO.setMessage("yay");
            }
            else {
                System.out.println("no success");
                exchange.sendResponseHeaders(HTTP_BAD_REQUEST, 0);
                resultO.setSuccess(false);
                resultO.setMessage("nay");
            }

            String jsonStr = encode.Encode(resultO);
            PrintWriter out = new PrintWriter(exchange.getResponseBody());
            out.write(jsonStr);
            out.flush();
            out.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
		The readString method shows how to read a String from an InputStream.
	*/
    protected String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
