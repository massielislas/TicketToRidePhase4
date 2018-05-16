package communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStreamReader;


/**
 * Created by Lance on 2/8/2018.
 */

public class Encoder
{
<<<<<<< HEAD
    public static String Encode(Object obj)
=======
    static public String Encode(Object obj)
>>>>>>> integration
    {
        Gson gson = new Gson();
        String toRet = gson.toJson(obj);
        return toRet;
    }

<<<<<<< HEAD
    public static Object Decode(StringBuilder JSON, Class type)
=======
    static public Object Decode(StringBuilder JSON, Class type)
>>>>>>> integration
    {
        Gson gson = new Gson();
        Object toRet = gson.fromJson(JSON.toString(), type);
        return toRet;
    }

<<<<<<< HEAD
    public static Object Decode(InputStreamReader JSON, Class type)
=======
    static public Object Decode(InputStreamReader JSON, Class type)
>>>>>>> integration
    {
        Gson gson = new Gson();
        Object toRet = gson.fromJson(JSON, type);
        return toRet;
    }

    static public Object Decode(String JSON, Class type)
    {
        Gson gson = new Gson();
        Object toRet = gson.fromJson(JSON, type);
        return toRet;
    }
}
