package DataPersistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Communication.Encoder;
import Model.User;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class tFileUserDAO implements IUserDAO {

    private String userFileName;
    private Encoder encoder;

    public tFileUserDAO (String userFileName) {
        this.userFileName = userFileName;
        this.encoder = new Encoder();
    }

    @Override
    public List<User> loadUsers() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(userFileName));
            String response = new String();
            for (String line; (line = br.readLine()) != null; response += line);
            br.close();

            List<User> users = (ArrayList<User>) encoder.Decode(response, new TypeToken<ArrayList<User>>(){}.getType());

            if (users == null) {
                return new ArrayList<User>(0);
            }

            return users;

        } catch (Exception e) {
            System.out.println("Exception loading users in FileUserDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(userFileName));
            String response = new String();
            for (String line; (line = br.readLine()) != null; response += line);
            br.close();

            List<User> users = (ArrayList<User>) encoder.Decode(response, new TypeToken<ArrayList<User>>(){}.getType());

            if (users == null) {
                users = new ArrayList<User>(0);
            }

            users.add(user);

            String usersJSON = encoder.Encode(users);

            BufferedWriter bw = new BufferedWriter(new FileWriter(userFileName));
            bw.write(usersJSON);
            bw.close();

            return true;

        } catch (Exception e) {
            System.out.println("Exception adding user in FileUserDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    @Override
    public boolean clearUsers() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(userFileName));
            bw.write("");
            bw.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Exception clearing users in FileUserDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }

}
