/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Demarion w
 * Last Updated: 9/11/24
 */
package williamsde;

import java.util.Random;

public class Die {
    public static final int MIN_SIDES = 2;
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private final Random random = new Random();
    private boolean rolled; // Flag to check if the die has been rolled

    public Die(int numSides) {
        if (numSides < MIN_SIDES || numSides > MAX_SIDES) {
            throw new IllegalArgumentException(" illegal number of sides cant be more than " + MAX_SIDES + ".");
        }
        this.numSides = numSides;
        this.currentValue = 0;
        this.rolled = false;
    }

    public int getCurrentValue() {
        if (!rolled) {
            throw new DieNotRolledException();
        }
        return currentValue;
    }

    public void roll() {
        this.currentValue = random.nextInt(numSides) + 1;
        this.rolled = true;
        System.out.println("You rolled a " + currentValue);
    }
}