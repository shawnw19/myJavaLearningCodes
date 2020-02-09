package Person_JCFSort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//based on Date Abstraction and Problem Solving with Java
// : Walls and Mirrors 3rd Edition
public class NameAndAgeComparison {
    public static void main(String[] args) {
        String [] names = {"Janet","Michael","Andrew","Kate"};
        List<String> l0 = Arrays.asList(names);
        Collections.sort(l0);
        System.out.println("Simple sample with string array: ");
        System.out.println(l0);
        System.out.println();

        NameComparator nameCamp = new NameComparator();
        AgeComparator ageCamp = new AgeComparator();

        Person [] p = new Person[5];
        p[0] = new Person("Michael",45);
        p[1] = new Person("Janet",39);
        p[2] = new Person("Sarah",17);
        p[3] = new Person("Kate",20);
        p[4] = new Person("Andrew",20);
        List<Person> list = Arrays.asList(p);//serialization is needed

        System.out.println("Sorting by age:");
        Collections.sort(list,ageCamp);//second parameter is a Comparator
        System.out.println(list);

        System.out.println("Sorting by name:");
        Collections.sort(list,nameCamp);
        System.out.println(list);
    }
}
