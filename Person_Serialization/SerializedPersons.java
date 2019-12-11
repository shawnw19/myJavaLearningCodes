package Person_Serialization;
//The code is supplemented and corrected based on the design rationale

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedPersons {
    public static Person searchFileSequentially(String fileName, String desiredName) throws Exception{
        ObjectInputStream ioStream = null;
        Person nextPerson =null;
        boolean found = false;

        try{
            ioStream = new ObjectInputStream(new FileInputStream(fileName));
            while (!found && (nextPerson=(Person)ioStream.readObject())!=null ){
          if (nextPerson.getName().compareTo(desiredName)==0)
              found=true;
            }//end while
        }// try

        catch(IOException e){
            System.out.println("Error processing file");
            return null;
        }//end catch

        finally {
            //clost the ObjectInputStream
            try {
                if(ioStream!= null){
                    ioStream.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }//end catch
        }//end finally

        if (found)
            return nextPerson;
        else
            return null;
    }

    public static void main(String[] args) throws Exception{
        Person p01 = new Person("James",3000);
        Person p02 = new Person("Fazimah",3200);
        Person p03 = new Person("Emily",2000);
        p01.addDependent(p03);
        Person p04 = new Person("Uraciq",2600);
        p02.addDependent(p04);
        //output serialized objects
        ObjectOutput ooStream = new ObjectOutputStream(new FileOutputStream("EmployeeDB.dat"));

        //Following is a wrong way of adding multiple objects into output stream: remember that arraylist and other ADT cannot be cast into Person object c.f. line 16.//
//        List ppl =new ArrayList<Person>();
//        ppl.add(p01);
//        ppl.add(p02);
//        ppl.add(p03);

        ooStream.writeObject(p01);
        ooStream.writeObject(p02);
        ooStream.writeObject(p03);
//        ooStream.writeObject(p04);//see annotation in the end
        ooStream.close();

        //read the file
        Person searchResult= searchFileSequentially("EmployeeDB.dat","Fazimah");
        Person searchResult2= searchFileSequentially("EmployeeDB.dat","Emily");
        Person searchResult3= searchFileSequentially("EmployeeDB.dat","Uraciq");
        System.out.println(searchResult.getName()+" found.");
        System.out.println(searchResult2.getName()+" found.");
        System.out.println(searchResult3.getName()+" found.");
//It is seen that without writing p04 (line 63) to output stream the query for Uraciq will be "Error processing file" although the object is inside the serialized file in the form of referenced Person object; it won't work after making the array "dependent" public - because the query only works on the set of pure objects.
    }

}
