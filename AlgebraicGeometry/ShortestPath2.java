package AlgebraicGeometry;

import java.util.*;

/*
implement "Dijkstra algorithm" to show
1 it is(/can be) a directedness irrelevant;
2 it is single threaded and enumerative.
3 its limited efficiency comes from calculating shortest paths from the origin to all other nodes (which can be a good thing if you need all of them).
 */
public class ShortestPath2 {
    static int inf = 10000;//infinity
    static LinkedList<Node> searchList = new LinkedList<Node>();
    static TreeMap<Integer,Node> allNodes = new TreeMap();
    static int origin = 0;//index
    static int destination =5;//index
    public static void main(String[] args) {
        int n = 6; //vertices A, B, C, D, E, F

        //adjacency matrix of distances
        int[][] C = new int[][]{{0, 30, inf, 50, 40, 100},
                {inf,0, 40, inf, inf, inf},
                {inf, inf, 0, inf, 10, 30},
                {inf, inf,10, 0, inf,inf},
                {inf,inf, inf, 20, 0, 70},
                {inf, inf, inf, inf, inf, 0} };

        for (int i = 0; i <n ; i++) {
            Node node = new Node(i);
            int[] dists = C[i];//its distance vector
            System.out.println(i+"th node created.");//-
            for (int j = 0; j <n ; j++) {//transform data from C to each node
                if(dists[j]!=inf){//there is a connection between them
                    node.addDists(j,dists[j]);//jth node, distance
                    System.out.println("to "+j+"th node distanced "+dists[j]);//-
                }
            }
            searchList.add(node);
            System.out.println(node.getNo()+"th node added to search list.");//-
            allNodes.put(i,node);
            System.out.println(i+"th node added to gamut list.");//-
            System.out.println("------------------");//-
        }
        
        while (!searchList.isEmpty()){
            searchNext();
        }

        /*output section*/
        Node dest = allNodes.get(destination);
        System.out.println("---------------------");//-
        System.out.println("The shortest path from A to "+dest.getName()+": ");
        System.out.print("Through");
        LinkedHashSet parents = dest.getParents();
        Iterator iter = parents.iterator();
        while (iter.hasNext()){
            System.out.print(" "+(char)(65+(int)iter.next())); //
        }
        System.out.println(".");
        System.out.println("Distance: "+ dest.getMinDist()+".");

    }//end of main()

    static void searchNext(){
        Node current = searchList.pop();
        System.out.println("----------------");//-
        System.out.println(current.getNo()+"th node popped.");//-
        System.out.println("Its parents: "+ current.getParents());//-
        TreeSet<Integer> toSearch = current.getToSearch();
        System.out.println(toSearch.size()+" nodes to search.");//-
        while (!toSearch.isEmpty()){
            int no = toSearch.pollFirst();
            Node searchee = allNodes.get(no);
            System.out.println(searchee.getNo()+"th node being investigated.");//-
            if(current.getMinDist() + current.getDist(no) < searchee.getMinDist()){
                searchee.setMinDist(current.getMinDist() + current.getDist(no));
                System.out.println("Min dist to "+searchee.getNo()+"th is "+searchee.getMinDist());//-
                searchee.setParents(current.getParents());//inherit all "grandparents"
                searchee.addParent(current.getNo());

                if(!searchee.getToSearch().isEmpty()){
                    searchList.add(searchee);
                    System.out.println(searchee.getNo()+"th added to search list.");//-
                }
            }
        }
    }

    private static class Node{
        private int no;//node number
        private char name;
        private int minDist;//minimum from the original to this node
        private LinkedHashSet<Integer> parents;
        private TreeMap<Integer,Integer> dists;//among other connectible nodes
        private TreeSet<Integer> allCons;//all connections
        private TreeSet<Integer> toSearch;

        public Node(int no) {
            this.no = no;
            name = (char)(no+65);
            parents = new LinkedHashSet<>();
            dists = new TreeMap<>();
            allCons = new TreeSet<>();
            toSearch = new TreeSet<>();
            minDist = inf;
        }

        public void addDists(int no, int dist){//distances of direct connections
            dists.put(no,dist);
            allCons.add(no);
            if (no!= this.no){
                toSearch.add(no);
            }
            if(no==origin){//direct connections from the origin
                minDist = dist;//to be updated when other nodes linked to this one are investigated
            }
        }
        public int getDist(int no) {
            return dists.get(no);
        }

        public LinkedHashSet<Integer> getParents() {
            return parents;
        }

        public void setParents(LinkedHashSet<Integer> parents) {
            this.parents = (LinkedHashSet<Integer>) parents.clone();
            System.out.println("Its new grandparents: "+ this.parents);//-
            //toSearch = (TreeSet<Integer>) allCons.clone();//
            //toSearch.removeAll(this.parents);//
        }

        public void addParent(int parent) {
            parents.add(parent);
            System.out.println("New parent "+ parent+" added to "+this.no);//-
            toSearch = (TreeSet<Integer>) allCons.clone();//regeneration of toSearch
            toSearch.removeAll(this.parents);/*is to ensure that non-parent nodes
            //connected with this one will be searched again when this node is re-added
            to searchList cf line 82 ! */
        }
        public int getNo() {
            return no;
        }
        public void setNo(int no) {
            this.no = no;
        }
        public int getMinDist() {
            return minDist;
        }
        public void setMinDist(int minDist) {
            this.minDist = minDist;
        }
        public char getName() {
            return name;
        }
        public TreeSet<Integer> getToSearch() {
            return toSearch;
        }
    }
}
