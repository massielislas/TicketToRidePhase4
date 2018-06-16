package root.tickettorideserver;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import static org.junit.Assert.*;

import DataPersistence.FileDAOFactory;
import DataPersistence.IUserDAO;

/**
 * Created by madeleineaydelotte on 6/15/18.
 */

public class testFileDAOFactory {

    public static List getClasseNames(String jarName) {
        ArrayList classes = new ArrayList();

        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(
                    jarName));
            JarEntry jarEntry;

            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                if (jarEntry.getName().endsWith(".class")) {
                        System.out.println("Found "
                                + jarEntry.getName().replaceAll("/", "\\."));
                    classes.add(jarEntry.getName().replaceAll("/", "\\."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("We done");
        return classes;
    }


    @Test
    public void test () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();

        getClasseNames("serverlibrary/libs/FlatFilePlugin.jar");

        IUserDAO userDAO = fileDAOFactory.createUserDAO();

        assertNotNull(userDAO);
    }

}
