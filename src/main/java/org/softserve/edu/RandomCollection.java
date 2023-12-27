package org.softserve.edu;

import java.util.*;

public class RandomCollection {
    /**
     * Create an ArrayList that will store integers (Integer).
     * Fill the collection with 20 random numbers from 1 to 100.
     * Use the Random class to generate random numbers.
     */

    public static void main(String[] args) {
        ArrayList<Integer> randomNumbers;
        final int ARRAY_SIZE = 20;

        //create random collection of Integer numbers
        randomNumbers=createRandomCollection(ARRAY_SIZE);

        //1. Print all elements of the collection to the console.
        System.out.println(randomNumbers);

        //2. Find and print the minimum and maximum numbers in the collection.
        System.out.println("The min number is: " + getMinValue(randomNumbers));
        System.out.println("The max number is: " + getMaxValue(randomNumbers));

        //3. Calculate and print the average value of the numbers in the collection.

        System.out.println("The Average Value of the numbers is: " + calculateAverageValue(randomNumbers));

        //4. Remove all even numbers from the collection, and then print the updated collection to the console.
        System.out.println(removeEvenNumbers(randomNumbers));

        //5. Check if the collection contains a given number (for example, 50), and print the result to the console.
        System.out.println(checkForNumber(1, randomNumbers));

        //6. Sort the collection in ascending order and print the sorted collection to the console.
        System.out.println(sortCollection(randomNumbers));
    }

    public static ArrayList<Integer> createRandomCollection(int arraySize){
        ArrayList<Integer> randomNumbers = new ArrayList<>();

        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 100
        for (int i = 0; i < arraySize; i++) {
            randomNumbers.add(rand.nextInt(101));
        }
        return randomNumbers;
    }

    public static int getMinValue(ArrayList<Integer> randomNumbers) {
        return Collections.min(randomNumbers);
    }

    public static int getMaxValue(ArrayList<Integer> randomNumbers) {
        return Collections.max(randomNumbers);
    }

    public static double calculateAverageValue(ArrayList<Integer> randomNumbers) {
        int sumOfNumbers = 0;
        for (Integer n : randomNumbers) {
            sumOfNumbers += n;
        }
        return (double) sumOfNumbers / randomNumbers.size();
    }

    public static String checkForNumber(Integer number, ArrayList<Integer> randomNumbers) {
        String str = "";
        if (randomNumbers.contains(number)) {
            str = "The index of " + number + " is: " + randomNumbers.indexOf(number);
        } else {
            str = number + " was not found.";
        }
        return str;
    }

    public static ArrayList<Integer> removeEvenNumbers(ArrayList<Integer> randomNumbers) {
        randomNumbers.removeIf(integer -> integer % 2 == 0);
        return randomNumbers;
    }

    public static ArrayList<Integer> sortCollection(ArrayList<Integer> randomNumbers) {
        Collections.sort(randomNumbers);
        return randomNumbers;
    }
}
