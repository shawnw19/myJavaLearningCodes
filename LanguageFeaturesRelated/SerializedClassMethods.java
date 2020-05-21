package Chp00;

import java.io.*;

public class SerializedClassMethods {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FunctorA func = new FunctorA();
        func.setID(30);

        String file = "Files/temp.txt";
        serialize(func,file);

        FunctorA newFunc = (FunctorA) deSerialize(file);
        System.out.println("The ID is: "+newFunc.getID());

        System.out.println("Apply its method: ");
        double x = 1.625;
        System.out.println(x+"^2="+ newFunc.square(x));
    }

    private static class FunctorA implements Serializable{
        private int ID=20;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public double square(double x){
            return x*x;
        }
    }

    static void serialize(Object out, String file) throws IOException {
        ObjectOutput ooStream = new ObjectOutputStream(new FileOutputStream(file));
        ooStream.writeObject(out);
    }

    static Object deSerialize(String file) throws IOException, ClassNotFoundException {
        ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
        Object iO = oiStream.readObject();//input object
        //Class theClass = input.getClass();
        return iO;
    }
}
