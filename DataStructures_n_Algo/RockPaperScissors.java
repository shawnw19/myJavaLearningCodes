package DataStructures_n_Algo;

import java.util.Random;

/*
Simulation of a Rock-paper-scissors game which is
analogous to some situations in counting and games.

The stone is beaten by paper which is beaten by scissors which are beaten by stones.

During the game, each player counts his score of beating others without deducting
scores caused by beaten by others.
 */
public class RockPaperScissors {
    public static void main(String[] args) {
        int[] players = new int[5];
        Random rdm = new Random();
        for (int i = 0; i <players.length ; i++) {
            var t = rdm.nextInt(3);
            players[i] = t;
            System.out.println("Player "+(char)(i+65)+" showed "+getType(t));
        }

        System.out.println("----------I'm the judge--------");

        printScores(getScores(players));
    }

    static String getType(int n){
        String type =null;
        switch (n){
            case 0: type = "Stone";
            break;
            case 1: type = "Paper";
            break;
            case 2: type= "Scissors";
            break;
        }

        return type;
    }

    static int[] getScores(int[] players) {
        int[] scores = new int[players.length];
        for (int i = 0; i < players.length - 1; i++) {//pointing to current player
            /*as for length minus 1, the last player don't need to compute his score
            as it will be updated by previous players*/
            for (int j = i+1; j < players.length; j++) {//pointing to his opponents
                var diff = players[i] - players[j];
                if (diff == 1 | diff == -2) {
                    scores[i]++;//current player wins
                } else if (diff !=0 ) {//-1 or 2
                    scores[j]++;//his opponent wins
                }//else neither wins
            }
        }
        return scores;
    }

    static void printScores(int[] scores){
        for (int i = 0; i <scores.length ; i++) {
            System.out.println("Player "+(char)(i+65)+" got scores "+scores[i]);
        }
    }
}
