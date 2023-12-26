package org.example.simplesearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuTest {
    Menu menu;


    @BeforeEach
    void setUp() {
        menu = new Menu();
        DataProcessor.getDataWithIndexes().clear();
        DataProcessor.getData().clear();
    }

    @Test
    void start() {
    }

    @Test
    void searchBasedOnStrategy() {
    }

    @Test
    void printMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        menu.printMenu();

        String expected = "=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit";

        assertEquals(expected, outContent.toString().trim());
    }

    @Test
    void printResult() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Set<String> emptyResult = new HashSet<>();
        menu.printResult(emptyResult);

        String expectedEmpty = "Nothing to show\n";

        assertEquals(expectedEmpty, outContent.toString());

        outContent.reset();
        System.setOut(new PrintStream(outContent));

        Set<String> notEmptyResult = new HashSet<>();
        notEmptyResult.add("Erick Burgess");
        notEmptyResult.add("Erick Harrington harrington@gmail.com");
        menu.printResult(notEmptyResult);

        String expectedNotEmpty = "2 persons found:\n"
                + "Erick Harrington harrington@gmail.com\n"
                + "Erick Burgess\n";

        assertEquals(expectedNotEmpty, outContent.toString());
    }
}