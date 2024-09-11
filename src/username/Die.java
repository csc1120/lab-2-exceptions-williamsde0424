/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Demarion w
 * Last Updated: 9/11/24
 */
package username;

import java.util.Random;

public class Die {
    public final static int MIN_Sides = 2;
    public final static int Max_Sides = 100;
    private int currentValue;
    private final  int numSides;
    private final Random random = new Random();

    public Die(int numSides) {
        this.numSides = numSides;
    }

    public int getCurrentValue(){
        return currentValue;
    }

    public void roll(){
        int result = random.nextInt(numSides)+ 1;
        System.out.println("you rolled a "+ result);
    }
}