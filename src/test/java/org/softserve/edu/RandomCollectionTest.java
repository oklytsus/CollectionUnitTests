package org.softserve.edu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomCollectionTest {
    private ArrayList<Integer> myRandomNumbers;
    private final int ARRAY_SIZE = 20;


    @BeforeEach
    void setUp() {
        myRandomNumbers = RandomCollection.createRandomCollection(ARRAY_SIZE);
    }

    @AfterEach
    void tearDown() {
        myRandomNumbers.clear();
    }

    @Test
    void testCollectionSize() {
        assertEquals(ARRAY_SIZE, myRandomNumbers.size(), "Total number of elements should match to ARRAY_SIZE");
    }
    @Test
    void testCollectionMinValue() {
        assertEquals(getMinValue(myRandomNumbers), RandomCollection.getMinValue(myRandomNumbers), "The minValue should match");
    }

    @Test
    void testCollectionMaxValue() {
        assertEquals(getMaxValue(myRandomNumbers), RandomCollection.getMaxValue(myRandomNumbers), "The maxValue should match");
    }

    @Test
    void testCollectionAverageValue() {
        assertEquals(calculateAverageValue(myRandomNumbers), RandomCollection.calculateAverageValue(myRandomNumbers), "The averageValue should match");
    }

    @ParameterizedTest
    @CsvSource({"29", "87", "8"})
    public void testCollectionNotContainsValue(Integer a) {
        String str = checkForNumber(a,myRandomNumbers);
        assertEquals(str, RandomCollection.checkForNumber(a, myRandomNumbers), "The result massage should match");
    }

    @Test
    void testOddCollectionContent() {
        List<Integer> expectedList = RandomCollection.removeEvenNumbers(myRandomNumbers);
        assertEquals(removeEvenNumbers(myRandomNumbers), expectedList, "Collectios should be equal");
    }

    @Test
    void testSortedCollectionContent() {
        assertEquals(sortCollection(myRandomNumbers), RandomCollection.sortCollection(myRandomNumbers), "Collections should be equal");
    }

    private static int getMinValue(ArrayList<Integer> randomNumbers) {
        return Collections.min(randomNumbers);
    }

    private static int getMaxValue(ArrayList<Integer> randomNumbers) {
        return Collections.max(randomNumbers);
    }

    private static double calculateAverageValue(ArrayList<Integer> randomNumbers) {
        int sumOfNumbers = 0;
        for (Integer n : randomNumbers) {
            sumOfNumbers += n;
        }
        return (double) sumOfNumbers / randomNumbers.size();
    }
    private static String checkForNumber(Integer number, ArrayList<Integer> randomNumbers) {
        String str = "";
        if (randomNumbers.contains(number)) {
            str = "The index of " + number + " is: " + randomNumbers.indexOf(number);
        } else {
            str = number + " was not found.";
        }
        return str;
    }
    private static ArrayList<Integer> removeEvenNumbers(ArrayList<Integer> randomNumbers) {
        randomNumbers.removeIf(integer -> integer % 2 == 0);
        return randomNumbers;
    }
    public static ArrayList<Integer> sortCollection(ArrayList<Integer> randomNumbers) {
        Collections.sort(randomNumbers);
        return randomNumbers;
    }

}