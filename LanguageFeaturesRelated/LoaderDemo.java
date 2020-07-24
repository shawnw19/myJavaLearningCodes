package LanguageFeaturesRelated;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoaderDemo {
    public static void main(String[] args) {
        URL url  = null;
        try {
            url = new URL("file:/home/shawn/IdeaProjects/loaderDemo/Files/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //System.out.println(url.getContent());
        //URL url  = new File("/home/shawn/IdeaProjects/loaderDemo/Files/").toURI().toURL();//alternative method
        URL[] urls = new URL[]{url};
        ClassLoader loader = new URLClassLoader(urls);

        try {
            Class cls = loader.loadClass("LoadMe");
            System.out.println(cls.getName());
            Object o = cls.getDeclaredConstructor().newInstance();
            String output = (String) cls.getMethod("output",String.class).invoke(o,"Shawn");
            System.out.println(output);//"Hello Shawn!"
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
