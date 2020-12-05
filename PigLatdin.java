package LanguageFeaturesRelated;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * A demonstration of string processing
 * and two paradigms of programs: stream-based and
 * batch-based.
 *
 * The case is adapted from a case in CS61 AS course.
 * See <a href="Conditional Expressions and Predicates">https://berkeley-cs61as.github.io/textbook/conditional-expressions-and-predicates.html</a>
 *
 * Pig Latdin has a special language Latdin which transforms each
 * world of human language in the following ways:
 * 1. If the word starts with a vowel, "ay" is appended to it.
 * 2. If the word starts with a consonant, all consonants before
 * the first vowel char is moved to the end before "ay" is added.
 * 3. If there is no vowels in the word, it remains intact.
 *
 * The implementation of the program proved to be quite complex, which is
 * not solely caused by my incompetency but the nature of string processing.
 */
public class PigLatdin {
    public static List vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) {
        String[] input = {"Oh.", "Hi, my amigo!", "How do  you do?", "What's your   name?", "Nice to meet you!", "Skr!"};

        System.out.println("In human language: ");
        Arrays.stream(input).forEach(System.out::println);

        System.out.println("\nIn Ladtdin's language: ");
        Arrays.stream(input).map(in -> processByStream(in)).forEach(System.out::println);

        System.out.println("\nAgain: ");
        Arrays.stream(input).map(in -> processByBatch(in)).forEach(System.out::println);

    }

    static String processByStream(String input) {
        //preprocess input
        input = input.trim();

        //temporarily store leading consonants
        StringBuilder buffer = new StringBuilder();
        StringBuilder output = new StringBuilder();
        boolean foundVowel = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c) | c == '\'') {//first char or consonants inside a word
                if (vowels.contains(Character.toLowerCase(c))) {
                    foundVowel = true;
                    output.append(c);
                } else if (!foundVowel) {//a leading consonant
                    buffer.append(c);
                } else {//a consonant after first vowel
                    output.append(c);
                }
            } else {//a punctuation mark indicating the end of the word or the sentence
                if (Character.isLetter(input.charAt(i - 1)))
                //in case consecutive spaces or marks within the sentence
                //pointer safe since first char mustn't be space (after trailing) or marks (no human speaks so)
                {
                    output.append(buffer);//buffered consonants
                    if (foundVowel) {
                        foundVowel = false;//could be true again for next word
                        output.append("ay");//Ladtin's suffix
                    }
                    //purge the buffer for next word
                    buffer.delete(0, buffer.length());

                    output.append(c);//keep the original marks or spaces
                }
                else {
                    //the space is truncated for pretty printing
                }
            }
        }

        return output.toString();
    }

    static String processByBatch(String input) {
        String[] words = input.trim().split("\\s+");
        //parallel() method internally split the tasks into threads
        String output = Arrays.stream(words).parallel().map(w -> processSingleWord(w)).collect(Collectors.joining(" "));
        return output;
    }

    //adapted from processByStream()
    static String processSingleWord(String input) {
        //preprocess input
        input = input.trim();

        //temporarily store leading consonants
        StringBuilder buffer = new StringBuilder();
        StringBuilder output = new StringBuilder();
        boolean foundVowel = false;

        for (int i = 0; i < input.length() - 1; i++) {
            char c = input.charAt(i);
            //assume all chars before the last one should be letters or in-word marks
            if (vowels.contains(Character.toLowerCase(c))) {
                foundVowel = true;
                output.append(c);
            } else if (!foundVowel) {//a leading consonant
                buffer.append(c);
            } else {//a consonant after first vowel
                output.append(c);
            }

        }

        char lastChar = input.charAt((input.length() - 1));

        if (Character.isLetter(lastChar)) {
            if (foundVowel) {
                output.append(lastChar);
                output.append(buffer);
                output.append("ay");
            } else {//all chars before the last one are in buffer
                output.append(buffer);
                output.append(lastChar);
            }
        } else {//a punctuation mark indicating the end of the word
            output.append(buffer);//buffered consonants, including all-consonant case
            if (foundVowel) {
                output.append("ay");
            }
            output.append(lastChar);//keep the original marks or spaces
        }

        return output.toString();
    }
}


/*
In human language:
Oh.
Hi, my amigo!
How do  you do?
What's your   name?
Nice to meet you!
Skr!

In Ladtdin's language:
Ohay.
iHay,my amigoay!
owHay oday ouyay oday?
at'sWhay ouryay amenay?
iceNay otay eetmay ouyay!
Skr!

Again:
Ohay.
iHay, my amigoay!
owHay do ouyay oday?
at'sWhay ouryay amenay?
iceNay to eetmay ouyay!
Skr!
 */