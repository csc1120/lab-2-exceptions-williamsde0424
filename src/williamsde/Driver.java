/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Demarion williams
 * Last Updated: 9/11/24
 */
package williamsde;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Driver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int numDice;
        int numSides;
        int numRolls;

        while (!validInput) {
            try {
                // Get user input
                int[] inputs = getInput(scanner);
                numDice = inputs[0];
                numSides = inputs[1];
                numRolls = inputs[2];

                // Create dice
                Die[] dice = createDice(numDice, numSides);

                // Roll dice and store frequencies
                int[] frequencies = new int[(numSides * numDice) - (numDice - 1)];
                rollDice(dice, numRolls, frequencies);

                // Find max frequency
                int maxFrequency = findMax(frequencies);

                // Print results
                report(frequencies, maxFrequency);

                validInput = true; // Exit loop on successful execution
            } catch (IllegalArgumentException e) {
                System.out.println("Bad die creation: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: All values must be whole numbers.");
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (DieNotRolledException e) {
                System.out.println("Die not rolled: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static int[] getInput(Scanner scanner) {
        System.out.println("Please enter the number of dice to roll, how many sides the dice have, and how many rolls to complete, separating the values by a space.");
        String inputLine = scanner.nextLine();
        String[] parts = inputLine.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Expected 3 values but received " + parts.length);
        }

        try {
            int numDice = Integer.parseInt(parts[0]);
            int numSides = Integer.parseInt(parts[1]);
            int numRolls = Integer.parseInt(parts[2]);
            return new int[]{numDice, numSides, numRolls};
        } catch (NumberFormatException e) {
            throw new NumberFormatException("All values must be whole numbers.");
        }
    }

    private static Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    private static void rollDice(Die[] dice, int numRolls, int[] frequencies) {
        for (int i = 0; i < numRolls; i++) {
            int total = 0;
            for (Die die : dice) {
                die.roll();
                total += die.getCurrentValue();
            }
            frequencies[total - dice.length]++; // Adjust index based on min value
        }
    }

    private static int findMax(int[] frequencies) {
        int max = 0;
        for (int frequency : frequencies) {
            if (frequency > max) {
                max = frequency;
            }
        }
        return max;
    }

    private static void report(int[] frequencies, int maxFrequency) {
        int scale = maxFrequency / 10;
        for (int i = 0; i < frequencies.length; i++) {
            int value = i + frequencies.length;
            int numStars = frequencies[i] / scale;
            System.out.printf("%2d:%4d  %s%n", value, frequencies[i], "*".repeat(numStars));
        }
    }

}