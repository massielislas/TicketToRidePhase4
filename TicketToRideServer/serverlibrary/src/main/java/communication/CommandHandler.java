package communication;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.InputStreamReader;
import java.io.PrintWriter;

import Models.Command;


/**
 * Created by Master_Chief on 5/16/2018.
 */

public class CommandHandler implements HttpHandler {

    public void handle(HttpExchange exchange) {

        try {
            System.out.println("HELLO");
            InputStreamReader ISR = new InputStreamReader(exchange.getRequestBody());
            Encoder encode = new Encoder();
            Command toExecute = (Command) encode.Decode(ISR, Command.class);
            Class c = Class.forName(toExecute.getResultClassType());
            Object o = toExecute.Execute();
            //if o is good result exchange.sendResponseHeaders(HTTP_OK, 0);
            //^ how to call method of type C?
            //for example, I'm trying to do LoginRegisterResult.isValid()
            //but LoginRegisterResult is type c
            //else exchange.sendResponseHeaders(HTTP_BAD_REQUEST, 0);

            String jsonStr = encode.Encode(o); //FIX THIS! WE NEED TO TURN THIS OBJECT INTO RESULT CLASS
            //Trying to do something like -> (clientResult.GameResult)object

            PrintWriter out = new PrintWriter(exchange.getResponseBody());
            out.write(jsonStr);
            out.flush();
            out.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
