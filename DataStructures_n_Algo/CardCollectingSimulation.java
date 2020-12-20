import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/*
a simple simulation of collecting trade cards;
inspired by [Statistical Computing in Pascal] chp7
 */
public class CardCollectingSimulation {
    static double prob=0.6;//_ability of getting a card
    static int num=10;//of total cards to be collected

    public static void main(String[] args) {
        Set collection = new TreeSet<Integer>();//of cards represented by numbers
        for (int i = 0; i <num ; i++) {
            collection.add(i);
        }

        boolean allCollected = false;
        Random random = new Random(88);
        int count = 0;
        System.out.println("Start collecting cards...");
        while (!allCollected){
            if (Math.random()<=prob){//there is a card in the good
                int card = random.nextInt(10);//get the card number
                collection.remove(card);//remains cards to be allCollected
                allCollected = collection.isEmpty();
            }
            count++;//whenever there is a card inside
        }

        System.out.println(num+ " cards collected after "+count +" trials.");
    }
}
