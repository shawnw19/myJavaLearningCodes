package Person_Serialization;
/*an example showing how to implement serialization,
  based on Data Abstraction and Problem Solving with Java: Walls and Mirrors 3rd Edition*/

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private double salary;
    private Person[] dependents;
    private int numDepend =0;

    public Person(String name, double salary) {
        this.name = name;
        this.salary = salary;
        //set a imagined large list of dependents
        dependents= new Person[25];
    }

    public void addDependent(Person person){
        numDepend++;
        dependents[numDepend]=person;
    };

    public String getName() {
        return name;
    }
}
