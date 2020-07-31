package LanguageFeaturesRelated.PointMoving_HotDeployment;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PointServer {
    static ClassLoader cl;
    static Class ptClass;

    public static Point createPoint(Point template) {
        if (ptClass == null) {
            reloadIMP();
        }
        Point newPT = null;
        try {
            newPT = (Point) ptClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (template != null) {
            newPT.move(template.getX(), template.getY());
        }
        return newPT;
    }

    public static void reloadIMP() {
        URL[] serverURLs = null;
        try {
            // !trailing slash cannot be omitted!
            serverURLs = new URL[]{new URL("file:/home/shawn/IdeaProjects/Streib/Files/")};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        cl = new URLClassLoader(serverURLs);
        try {
            ptClass = cl.loadClass("PointImpl");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
