package DataStructures_n_Algo;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/*
An implementation of HuffmanCode coding with
comparision of time consumption for coding and decoding.

Based on http://home.cse.ust.hk/faculty/golin/COMP271Sp03/Notes/MyL17.pdf and
[Communication Systems Principles Using MATLAB] chp5.6.1.2;
adapted from https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
 */
public class HuffmanCode {
    static double encodingT;//time in milliseconds
    static double decodingT;
    public static void main(String[] args) throws IOException {
        /*first test used to show an encoding example*/
        //printCodes(getCodeList(input1));

        /*second test uses long text*/
        String input = htmlExtractor(url2);
        encodingT = System.currentTimeMillis();
        HuffmanNode[] codeTable = getCodeTable(input);
        String encoded = encodeText(input,codeTable);
        encodingT = System.currentTimeMillis()- encodingT;
        printCodes(codeTable);
        System.out.println("\n---------------------------------");
        System.out.println("Num of chars/codes: "+ codeTable.length);
        System.out.println("Encoding time in ms: " + encodingT);

        //System.out.println(encoded);

        decodingT = System.currentTimeMillis();
        String decoded = decodeText(encoded,codeTable);
        decodingT = System.currentTimeMillis() - decodingT;
        System.out.println("\n---------------------------------");
        System.out.println("Decoding time in ms: " + decodingT );
        //System.out.println(decoded);

        System.out.println("-------------------------");
        boolean same = (input.compareTo(decoded)==0);
        if(same){
            System.out.println("Exactly the same!");
        }else {
            System.out.println("Not the same.");
        }
    }

    static HuffmanNode[] getCodeTable(String input){//ArrayList is chosen for the ease of searching

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new FreqComp());//to store all nodes for generating Huffman tree
        Hashtable freqList = CharFrequency.checkFreq(input);
        Set<Character> keySet = freqList.keySet();
        for (char key:keySet) {
            queue.add(new HuffmanNode((CharFrequency.Struct) freqList.get(key)));
        }

        while (queue.size()>1){//there are nodes to be combined
            HuffmanNode upper = new HuffmanNode(queue.poll(),queue.poll());//-
            queue.add(upper);
        }//until the root is left

        HuffmanNode root = queue.poll();
        PriorityQueue<HuffmanNode>codeList = new PriorityQueue<>(new FreqComp());
        long start = System.currentTimeMillis();//-
        assignCodes(root,codeList);
        long end = System.currentTimeMillis()-start;//-
        System.out.println("Assigning codes time: "+end);//-

        int n = codeList.size();//num of codes
        HuffmanNode[] codeTable = new HuffmanNode[n];

        PriorityQueue<HuffmanNode> copy = new PriorityQueue<>(new FreqComp());//for restoration since clone method is not supported
        HuffmanNode node;//for ref
        for (int i = 0; i <n ; i++) {
            node = codeList.poll();
            codeTable[n-1-i] =node;//add them reversely so that the frequent chars appear in the front
            copy.add(node);//for restoration
        }
        while (!copy.isEmpty()){//ditto
            codeList.add(copy.poll());
        }

        return codeTable;
    }

    static void printCodes(HuffmanNode[] codeTable){
        HuffmanNode ref;
        for (int i = 0; i <codeTable.length ; i++) {
            ref = codeTable[i];
            System.out.println("Char:\""+ref.c + "\", freq:" + ref.num + ", code:"+ref.code);
        }
    }

    static void assignCodes(HuffmanNode root,  PriorityQueue<HuffmanNode> codeList){//alternative 0s and 1s
        if(root.left!=null && root.right!=null){
            root.left.code += root.code+0;//quotation marks not needed
            root.right.code += root.code+1;

            assignCodes(root.left, codeList);//the recursion shall be triggered
            assignCodes(root.right, codeList);//inside the non-ending block
        }
        else if(!codeList.contains(root)){//prevent infinite loop of repeating addition
            codeList.add(root);//add the elements to be coded which are end nodes
        }
    }

    static String encodeText(String input, HuffmanNode[] codeTable){
        String output = "";
        int n = codeTable.length;
        for (int i = 0; i <input.length() ; i++) {
            char c = input.charAt(i);
            boolean found=false;
            for (int j = 0; j <n ; j++) {//quick process since most often used ones will be fetched first
                HuffmanNode ref = codeTable[j];
                if(ref.c==c){//search in codeTable so j is used
                    output += ref.code;//successfully coded
                    found = true;
                }
            }
            if(!found) {
                throw new NoSuchElementException("Uncoded or new char: "+c);
            }
        }

        return output;
    }

    static String decodeText(String encoded, HuffmanNode[] codeTable){
        String decoded = "";
        boolean found = false;
        while (encoded.length()>0){
            for (int j = 0; j <codeTable.length ; j++) {
                HuffmanNode ref = codeTable[j];
                String codeRef = ref.code;
                if(codeRef.length()<=encoded.length() && encoded.substring(0,codeRef.length()).compareTo(codeRef)==0){
                    decoded += ref.c;//successfully decoded
                    found = true;
                    encoded = encoded.substring(codeRef.length(),encoded.length());
                }
            }

            if(!found) {
                throw new NoSuchElementException("Wrongly or new code at index: "+decoded.length());
            }
        }

        return decoded;
    }

    static class HuffmanNode {
        char c;
        int num;
        //new added variables
        String code;//full code
        HuffmanNode left;
        HuffmanNode right;
        public HuffmanNode(CharFrequency.Struct origin) {
            this.c = origin.c;
            this.num = origin.num;
            code ="";
            left = null;
            right = null;
        }

        public HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.num = left.num+right.num;
            code ="";
            this.left = left;
            this.right = right;
        }
    }

    public static class FreqComp implements Comparator<HuffmanNode> {
        @Override
        public int compare(HuffmanNode o1, HuffmanNode o2) {
            return o1.num - o2.num;
        }
    }

    //using https://stackoverflow.com/questions/3036638/how-to-extract-web-page-textual-content-in-java
    static String htmlExtractor(String url) throws IOException {
        StringBuilder longStr = new StringBuilder();
        for(Scanner sc = new Scanner(new URL(url).openStream()); sc.hasNext(); )
            longStr.append(sc.nextLine()).append('\n');
        return longStr.toString();
    }

    static String input1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    static String input2 = "据《新唐书》记载李白为兴圣皇帝（凉武昭王李暠）九世孙[6]，如果按照这个说法李白与李唐诸王实际上同宗，应是唐太宗李世民的同辈族弟。亦有野史说其祖是李建成或李元吉，因为被李世民族灭而逃往西域；但此说缺乏佐证，且李建成、李元吉诸子尚在幼年即在玄武门之变后全数被害，留有亲生后嗣的可能性很小。据《旧唐书》记载，李白之父李客为任城尉。更为了学习而隐居。\n" +
            "\n" +
            "李白于武则天大足元年（701年）[7]出生，关于其出生地有多种说法，现在主要有剑南道绵州昌隆县（今四川省江油市）[8]青莲乡（今青莲镇）和西域的碎叶（Suyab，位于今日的吉尔吉斯托克马克附近）[9]这两种说法，其中后一种说法认为李白直到四岁时（705年）才跟随他的父亲李客迁居蜀地，入籍绵州。李白自四岁（705年）接受启蒙教育，从景云元年（710年）开始，李白开始读诸子史籍[10]，开元三年时十四岁（715年）——喜好作赋、剑术、奇书、神仙：“十五观奇书，做赋凌相如”。在青年时期开始在中国各地游历。开元五年左右，李白曾拜撰写《长短经》的赵蕤为师，学习一年有余，这段时期的学习对李白产生了深远的影响。开元六年，在戴天山（约在四川昌隆县北五十里处）大明寺读书。二十五岁时只身出四川，开始了广泛漫游，南到洞庭湘江，东至吴、越，寓居在安陆（今湖北省安陆市）、应山（今湖北省广水市）。 ";

    static String url1= "https://en.wikipedia.org/wiki/Lorem_ipsum";

    static String url2 = "https://en.wikipedia.org/wiki/Li_Bai";
}
