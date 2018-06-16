package DataPersistence;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class FileDAOFactory implements IDAOFactory {

    private String fileGameDAOClass;
    private String fileUserDAOClass;

    private String jarName;
    private String jarLibraryName;

    public FileDAOFactory () {
        this.fileGameDAOClass = "DataPersistence.FileGameDAO";
        this.fileUserDAOClass = "DataPersistence.FileUserDAO";

        this.jarName = "FlatFilePlugin";
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
                Class klass = cl.loadClass(fileGameDAOClass);
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
                Class klass = cl.loadClass(fileUserDAOClass);
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
