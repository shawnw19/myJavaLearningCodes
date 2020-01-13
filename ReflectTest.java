package Chp00;

//based on book Concept of Programming Languages (Robert Sebesta) 11e pp53
//reflection is considered a dynamic method calling
//this is also an example of suitable handling of potential exceptions

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) {
        Object[] birdList = new Object[3];
        birdList[0]= new Brid1();
        birdList[1]= new Brid2();
        birdList[2]= new Brid3();
        Reflect.callDraw(birdList[0]);
        Reflect.callDraw(birdList[1]);
        Reflect.callDraw(birdList[2]);
    }
}

class Reflect {
    public static void callDraw(Object birdObj){
        Class cls = birdObj.getClass();
        try{
            Method method=cls.getMethod("draw");
            method.invoke(birdObj);
        }
        catch (NoSuchMethodException e){
            throw new IllegalArgumentException(cls.getName() + " does not support draw");
        }
        catch (IllegalAccessException e){
            throw new IllegalArgumentException("Insufficient access permission to call"+" \"draw\" in class "+ cls.getName());
        }
        catch (InvocationTargetException e){
            throw new RuntimeException(e);
        }

    }
}

class Brid1{
    public void draw(){
        System.out.println("This a draw from Bird1");
    }
}

class Brid2{
    public void draw(){
        System.out.println("This a draw from Bird2");
    }
}class Brid3{
    public void draw(){
        System.out.println("This a draw from Bird3");
    }
}
