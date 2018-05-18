package Communication;

import com.google.gson.Gson;

import java.io.InputStreamReader;


/**
 * Created by Lance on 2/8/2018.
 */

public class Encoder
{
    public String Encode(Object obj)
    {
        Gson gson = new Gson();
        String toRet = gson.toJson(obj);
        return toRet;
    }

    public Object Decode(StringBuilder JSON, Class type)
    {
        Gson gson = new Gson();
        Object toRet = gson.fromJson(JSON.toString(), type);
        return toRet;
    }

    public Object Decode(InputStreamReader JSON, Class type)
    {
        Gson gson = new Gson();
        Object toRet = gson.fromJson(JSON, type);
        return toRet;
    }

    public Object Decode(String JSON, Class type)
    {
        Gson gson = new Gson();
        return gson.fromJson(JSON, type);
    }
}
