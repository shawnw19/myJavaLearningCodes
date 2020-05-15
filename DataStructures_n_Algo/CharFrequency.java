package DataStructures_n_Algo;

import java.util.Hashtable;
import java.util.Set;

/*
An implementation of frequency counting for characters with rationale
that can be used for counting any string element, like words.
Note: it is case-sensitive.
 */
public class CharFrequency {
    public static void main(String[] args) {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        Hashtable freqList = checkFreq(input);
        printFreqList(freqList);
    }

    public static Hashtable<Character, Struct> checkFreq(String input){//K,V
        Hashtable<Character,Struct> freqList = new Hashtable<>();
        for (int i = 0; i <input.length() ; i++) {
            char theC = input.charAt(i);
            Struct ref;//pointing to existing Structs
            if(!freqList.containsKey(theC)){
                Struct struct = new Struct(theC);
                freqList.put(theC,struct);
            }else {
                ref = freqList.get(theC);
                ref.num++;//update num of occurrence
            }
        }

        return freqList;
    }

    static void printFreqList(Hashtable<Character, Struct> list){
        Set<Character> keySet = list.keySet();
        for (char key:keySet) {
            System.out.println("Char:\""+key+"\", freq:"+list.get(key).num);
        }
    }

    public static class Struct{
        char c;
        int num;//of occurrence

        public Struct(char c) {
            this.c = c;
            this.num = 1;
        }
    }


    /*    static TreeSet<Character> checkElements(String input){
        TreeSet<Character> set = new TreeSet<>();
        for (int i = 0; i <input.length() ; i++) {
            set.add(input.charAt(i));
        }
        return set;
    }*/

}
