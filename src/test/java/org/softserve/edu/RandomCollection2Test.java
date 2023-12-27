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

class RandomCollection2Test {
    private ArrayList<Integer> myRandomNumbers;

    @BeforeEach
    void setUp() {
        myRandomNumbers = createRandomNumbersCollection();
    }

    @AfterEach
    void tearDown() {
        myRandomNumbers.clear();
    }

    @Test
    void testListSize() {
        assertEquals(20, myRandomNumbers.size(), "Total number of elements should be 20");
    }
    @Test
    void testListMinValue() {
        assertEquals(9, RandomCollection.getMinValue(myRandomNumbers), "The minValue should match");
    }

    @Test
    void testListMaxValue() {
        assertEquals(93, RandomCollection.getMaxValue(myRandomNumbers), "The maxValue should match");
    }

    @Test
    void testListAverageValue() {
        assertEquals(calculateAverageValue(myRandomNumbers), RandomCollection.calculateAverageValue(myRandomNumbers), "The averageValue should match");
    }

    @ParameterizedTest
    @CsvSource({"45", "62", "9"})
    public void testListContainsValue(Integer a) {
        String str = "The index of " + a + " is: " + myRandomNumbers.indexOf(a);
        assertEquals(str, RandomCollection.checkForNumber(a, myRandomNumbers), "The result massage should match");
    }

    @ParameterizedTest
    @CsvSource({"2", "5", "8"})
    public void testListNotContainsValue(Integer a) {
        String str = a + " was not found.";
        assertEquals(str, RandomCollection.checkForNumber(a, myRandomNumbers), "The result massage should match");
    }

    @Test
    void testOddListContent() {
        List<Integer> expectedList = RandomCollection.removeEvenNumbers(myRandomNumbers);
        assertEquals(removeEvenNumbers(myRandomNumbers), expectedList, "Collections should be equal");
    }

    @Test
    void testSortedListContent() {
        assertEquals(sortCollection(myRandomNumbers), RandomCollection.sortCollection(myRandomNumbers), "Collections should be equal");
    }

    private static ArrayList<Integer> createRandomNumbersCollection() {
        //List<Integer> list = Arrays.asList(26, 3, 95, 74, 17, 23, 80, 16, 51, 0, 23, 38, 28, 71, 98, 16, 48, 95, 13, 7);
        List<Integer> addNumbers = List.of(45, 78, 40, 78, 62, 17, 61, 87, 54, 43, 74, 13, 11, 62, 26, 69, 71, 93, 9, 48);
        return new ArrayList<>(addNumbers);
    }

      private static double calculateAverageValue(ArrayList<Integer> randomNumbers) {
        int sumOfNumbers = 0;
        for (Integer n : randomNumbers) {
            sumOfNumbers += n;
        }
        return (double) sumOfNumbers / randomNumbers.size();
    }

    private static ArrayList<Integer> removeEvenNumbers(ArrayList<Integer> randomNumbers) {
        randomNumbers.removeIf(integer -> integer % 2 == 0);
        return randomNumbers;
    }
    public static ArrayList<Integer> sortCollection(ArrayList<Integer> randomNumbers) {
        Collections.sort(randomNumbers);
        return randomNumbers;
    }
//    private static int[] getIntegerArray(List<Integer> modifiedCollection){
//        return modifiedCollection.stream()
//               .mapToInt(Integer::intValue)
//               .toArray();
//    }
}