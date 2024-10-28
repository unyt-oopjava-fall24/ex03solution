package com.eltonb.ex03;

import java.security.SecureRandom;

public class Die {
    SecureRandom random;

    private int weight1;
    private int weight2;
    private int weight3;
    private int weight4;
    private int weight5;
    private int weight6;

    public Die(int w1, int w2, int w3, int w4, int w5, int w6) {
        random = new SecureRandom();
        weight1 = w1;
        weight2 = w2;
        weight3 = w3;
        weight4 = w4;
        weight5 = w5;
        weight6 = w6;
    }

    private int totalWeight() {
        return weight1 + weight2 + weight3 + weight4 + weight5 + weight6;
    }

    public int roll() {
        int val = random.nextInt(totalWeight());

        if (val < weight1)
            return 1;
        if (val < weight1 + weight2)
            return 2;
        if (val < weight1 + weight2 + weight3)
            return 3;
        if (val < weight1 + weight2 + weight3 + weight4)
            return 4;
        if (val < weight1 + weight2 + weight3 + weight4 + weight5)
            return 5;
        if (val < weight1 + weight2 + weight3 + weight4 + weight5 + weight6)
            return 6;

        throw new IllegalStateException("Should never come here!");
    }

    public double sampleRoll(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += roll();
        return (double) sum / n;
    }

}
