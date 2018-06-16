package DataPersistence;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class SQLiteDAOFactory implements IDAOFactory {
    private String gameDAOClass;
    private String userDAOClass;

    private String jarName;
    private String jarLibraryName;

    public SQLiteDAOFactory(){
        this.gameDAOClass = "DataPersistence.SQLiteGameDAO";
        this.userDAOClass = "DataPersistence.SQLiteUserDAO";

        this.jarName = "sqlModule";
        this.jarLibraryName = "serverlibrary/libs";
    }
    @Override
    public IGameDAO createGameDAO() {
        IGameDAO gameDAO = null;

        try
        {
            // Get all the files in mod folder
            File[] mods = new File(jarLibraryName).listFiles();

            for (int i=0; i<mods.length; i++)
            {
                // Skip if the file is not a jar
                if (!mods[i].getName().endsWith(".jar"))
                    continue;

                // Create a JarFile
                JarFile jarFile = new JarFile(mods[i]);

                // Create a URL for the jar

                if (!mods[i].getAbsolutePath().contains(jarName)) {
                    continue;
                }

                URL[] urls = { new URL("jar:file:" + mods[i].getAbsolutePath() + "!/") };
                URLClassLoader cl = URLClassLoader.newInstance(urls);

                // Load the class
                Class klass = cl.loadClass(gameDAOClass);
                Object o = klass.newInstance();
                gameDAO = (IGameDAO) o;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return gameDAO;
    }

    @Override
    public IUserDAO createUserDAO() {
        IUserDAO userDAO = null;
        try
        {
            // Get all the files in mod folder
            File[] mods = new File(jarLibraryName).listFiles();

            for (int i=0; i<mods.length; i++)
            {
                // Skip if the file is not a jar
                if (!mods[i].getName().endsWith(".jar"))
                    continue;

                // Create a JarFile
                JarFile jarFile = new JarFile(mods[i]);

                // Create a URL for the jar

                if (!mods[i].getAbsolutePath().contains(jarName)) {
                    continue;
                }

                URL[] urls = { new URL("jar:file:" + mods[i].getAbsolutePath() + "!/") };
                URLClassLoader cl = URLClassLoader.newInstance(urls);

                // Load the class
                Class klass = cl.loadClass(userDAOClass);
                Object o = klass.newInstance();
                userDAO = (IUserDAO) o;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userDAO;
    }
}
