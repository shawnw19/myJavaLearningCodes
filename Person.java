package Person_JCFSort;

import java.io.Serializable;
import java.util.Comparator;

public class Person implements Serializable {
    //Serializable is for Arrays.asList
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " - " + age ;
    }
}

class NameComparator implements Comparator<Person>, Serializable{
    @Override
    public boolean equals(Object obj) {
        return this== obj;
    }

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class AgeComparator implements Comparator<Person>, Serializable{
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }
}