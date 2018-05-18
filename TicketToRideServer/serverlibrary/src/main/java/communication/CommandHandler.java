package communication;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;

import Models.Command;


/**
 * Created by Master_Chief on 5/16/2018.
 */

public class CommandHandler implements HttpHandler {

    public void handle(HttpExchange exchange) {
//        try {
//            InputStreamReader ISR = new InputStreamReader(exchange.getRequestBody());
//            Encoder encode = new Encoder();
//            Command toExecute = (Command) encode.Decode(ISR, Command.class);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
