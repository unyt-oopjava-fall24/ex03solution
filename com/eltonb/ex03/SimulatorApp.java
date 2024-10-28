package com.eltonb.ex03;

import java.util.Scanner;

public class SimulatorApp {

    public static final int ROLLS = 210_000;

    public static void main(String[] args) {
        Die die = constructDie();

        System.out.println("Trying individual rolls");
        int[] faces = doSingle(die, ROLLS);
        double[] faceStats = computeStatistics(toDoubleArray(faces));
        displayStats(faceStats);

        System.out.println("Trying 10 roll samples");
        double[] samples = doSample(die, ROLLS);
        double[] sampleStats = computeStatistics(samples);
        displayStats(sampleStats);
    }

    private static void displayStats(double[] stats) {
        System.out.println("Mean is " + stats[0]);
        System.out.println("Variance is " + stats[1]);
        System.out.println("Std. Dev is " + stats[2]);
        System.out.println();
    }

    private static double[] computeStatistics(double[] values) {
        double mean, variance, stdDev;
        double sum = 0, sumOfSquares = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            sumOfSquares += values[i]*values[i];
        }
        int n = values.length;
        mean = sum / n;
        variance = (sumOfSquares - n*mean*mean) / (n - 1);
        stdDev = Math.sqrt(variance);

        return new double[] {mean, variance, stdDev};
    }

    private static double[] toDoubleArray(int[] ints) {
        double[] doubles = new double[ints.length];
        for (int i = 0; i < ints.length; i++) {
            doubles[i] = (double) ints[i];
        }
        return doubles;
    }

    private static Die constructDie() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter weights: ");
        int w1 = input.nextInt();
        int w2 = input.nextInt();
        int w3 = input.nextInt();
        int w4 = input.nextInt();
        int w5 = input.nextInt();
        int w6 = input.nextInt();
        Die die = new Die(w1, w2, w3, w4, w5, w6);
        return die;
    }

    // repeats die roll a number of times
    private static int[] doSingle(Die die, int numRolls) {
        int[] faces = new int[numRolls];
        for (int i = 0; i < ROLLS; i++)
            faces[i] = die.roll();
        return faces;
    }

    // repeats sampling a number of times
    private static double[] doSample(Die die, int numRolls) {
        double[] sampleAverages = new double[numRolls];
        for (int i = 0; i < ROLLS; i++)
            sampleAverages[i] = die.sampleRoll(10);
        return sampleAverages;
    }
}
