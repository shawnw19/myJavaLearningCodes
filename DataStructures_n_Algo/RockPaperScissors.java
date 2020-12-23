package DataStructures_n_Algo;

import java.util.Random;

enum Choice {
    STONE("Stone"),
    PAPER("Paper"),
    SCISSORS("Scissors");

    static final Random RANDOM = new Random();
    final String name;

    Choice(String name) {
        this.name = name;
    }

    /**
     * 比较两者
     *
     * @return 当 A 打赢了 B 返回 1，输了返回 2，否则为 0.
     */
    static int compare(Choice choiceA, Choice choiceB) {
        int flag = 0;
        switch (choiceA) {
            case PAPER:
                if (choiceB == STONE) flag = 1;
                if (choiceB == SCISSORS) flag = -1;
                break;
            case SCISSORS:
                if (choiceB == PAPER) flag = 1;
                if (choiceB == STONE) flag = -1;
                break;
            case STONE:
                if (choiceB == SCISSORS) flag = 1;
                if (choiceB == PAPER) flag = -1;
                break;
        }
        return flag;
    }
    
    public String getName() {
        return name;
    }


    static Choice getRandom() {
        return Choice.values()[RANDOM.nextInt(Choice.values().length)];
    }
}

class Player {

    final Choice choice = Choice.getRandom();

    public Choice getChoice() {
        return choice;
    }
}

/**
 * Simulation of a Rock-paper-scissors game which is
 * analogous to some situations in counting and games.
 * The stone is beaten by paper which is beaten by scissors which are beaten by stones.
 * During the game, each player counts his score of beating others without deducting
 * scores caused by beaten by others.
 */
public class RockPaperScissors {
    public static void main(String[] args) {
        Player[] players = new Player[5];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
            printPlayerWithChoice((char) (i + 65), players[i]);
        }

        System.out.println("----------I'm the judge--------");

        printScores(computeScores(players));
    }

    private static void printScores(int[] computedScores) {
        for (int i = 0; i < computedScores.length; i++) {
            System.out.println("Player " + (char) (i + 65) + " got scores " + computedScores[i]);
        }
    }


    static int[] computeScores(Player[] players) {
        int[] scores = new int[players.length];
        for (int i = 0; i < players.length - 1; i++) {//pointing to current player
                /*as for length minus 1, the last player don't need to compute his score
                as it will be updated by previous players*/
            for (int j = i + 1; j < players.length; j++) {//pointing to his opponents
                final Choice choiceA = players[i].getChoice();
                final Choice choiceB = players[j].getChoice();
                switch (Choice.compare(choiceA, choiceB)) {
                    case 1:
                        scores[i]++;
                        break;
                    case -1:
                        scores[j]++;
                        break;
                }
            }
        }
        return scores;
    }

    static void printPlayerWithChoice(char label, Player player) {
        System.out.println("Player " + label + " showed " + player.getChoice().getName());
    }
}
