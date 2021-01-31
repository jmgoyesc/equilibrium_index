package com.github.jmgoyesc.equilibriumindex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UT. EquilibriumIndexTest.")
class EquilibriumIndexTest {

    @DisplayName("T1. GIVEN an array with -1, 3, -4, 5, 1, -6, 2, 1, WHEN equilibrium index find, THEN return 1, 3 or 7")
    @Test
    void test1() {
        int[] givenElements = new int[]{-1, 3, -4, 5, 1, -6, 2, 1};
        int actual = new EquilibriumIndex(givenElements).find();
        assertEqualsAny(actual,1, 3, 7);
    }

    @DisplayName("T2. GIVEN an array of size 100000 with max integer, WHEN equilibrium index find, THEN return 49999 = 100000/2 - 1")
    @Test
    void test2() {
        int[] elements = givenArrayOnlyWith(100000, Integer.MAX_VALUE);
        int actual = new EquilibriumIndex(elements).find();
        assertEqualsAny(actual,49999);
    }

    @DisplayName("T3. GIVEN an array of size 100000 with zeros, WHEN equilibrium index find, THEN return 0")
    @Test
    void test3() {
        int[] givenElements = new int[100000];
        int actual = new EquilibriumIndex(givenElements).find();
        assertEqualsAny(actual,0);
    }

    @DisplayName("T4. GIVEN an array of size 100000 with ones, WHEN equilibrium index find, THEN return 49999 = 100000/2 - 1")
    @Test
    void test4() {
        int[] elements = givenArrayOnlyWith(100000, 1);
        int actual = new EquilibriumIndex(elements).find();
        assertEqualsAny(actual,49999);
    }

    @DisplayName("T5. GIVEN an array of size 100000 with sequence of -1 and 1, WHEN equilibrium index find, THEN return -1")
    @Test
    void test5() {
        int[] elements = givenArrayWithSequence(100000, -1, 1);
        int actual = new EquilibriumIndex(elements).find();
        assertEqualsAny(actual,-1);
    }

    @DisplayName("T6. GIVEN an array of size 8 with sequence of 0 and 1, WHEN equilibrium index find, THEN return 4")
    @Test
    void test6() {
        int[] elements = givenArrayWithSequence(8, 0, 1);
        int actual = new EquilibriumIndex(elements).find();
        assertEqualsAny(actual,4);
    }

    private int[] givenArrayOnlyWith(int size, int only) {
        int[] elements = new int[size];
        Arrays.fill(elements, 0, size -1, only);
        return elements;
    }

    private int[] givenArrayWithSequence(int size, int... sequence) {
        int[] elements = new int[size];
        for (int i = 0, j = 0; i < size; i++,j = (j + 1) % sequence.length) {
            elements[i] = sequence[j];
        }
        return elements;
    }

    private void assertEqualsAny(int actual, int... expected) {
        assertTrue(Arrays.stream(expected).anyMatch(e -> e == actual),
                () -> String.format("Actual: %d. Any of these is expected: %s", actual, Arrays.toString(expected)));
    }

}