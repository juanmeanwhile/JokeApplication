package com.example;

import java.util.Random;

public class JavaJokes {
    Random mRandom;

    public JavaJokes() {
         mRandom = new Random(System.currentTimeMillis());
    }

    public Joke generateJoke(){
        return new Joke(JOKES[mRandom.nextInt(JOKES.length)]);
    }

    private static final String[] JOKES = {"There are two guys on a motobike an the middle one falls",
            "Two bytes meet.  The first byte asks, “Are you ill?" + "The second byte replies, “No, just feeling a bit off.”",
            "Q. How did the programmer die in the shower?\n" + "A. He read the shampoo bottle instructions: Lather. Rinse. Repeat.",
            "How many programmers does it take to change a light bulb?\n" + "None – It’s a hardware problem",
            "Why do programmers always mix up Halloween and Christmas? \n" + "Because Oct 31 equals Dec 25.",
            "There are only 10 kinds of people in this world: those who know binary and those who don’t.",
            "A programmer walks to the butcher shop and buys a kilo of meat.  An hour later he comes back upset that the butcher shortchanged him by 24 grams."
    };
}
