package org.example.simplesearch;

import org.example.simplesearch.datacontroller.DataProcessor;
import org.example.simplesearch.seachengine.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


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
   void searchBasedOnStrategyAll() {
        DataProcessor.dataFromFileIntoMap("Data.txt");

        String strategy = "all";
        String[] input = {"dwight", "joseph"};
        Set<String> output = Set.of("Dwight Joseph djo@gmail.com");

        assertEquals(output, menu.searchBasedOnStrategy(strategy,input));
   }

    @Test
    void searchBasedOnStrategyNone() {
        DataProcessor.dataFromFileIntoMap("Data.txt");

        String strategy = "none";
        String[] input = {"dwight", "joseph"};

        Set<String> actual = menu.searchBasedOnStrategy(strategy,input);

        assertEquals(1, actual.size());
    }

    @Test
    void searchBasedOnStrategyAny() {
        DataProcessor.dataFromFileIntoMap("Data.txt");

        String strategy = "any";
        String[] input = {"dwight", "joseph"};
        Set<String> output = Set.of("Dwight Joseph djo@gmail.com");

        assertEquals(output, menu.searchBasedOnStrategy(strategy,input));
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
    void printResultEmptyCase() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Set<String> emptyResult = new HashSet<>();
        menu.printResult(emptyResult);

        String expectedEmpty = "Nothing to show\n";

        assertEquals(expectedEmpty, outContent.toString());
    }

    @Test
    void printResultNonEmptyCase(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Set<String> notEmptyResult = new HashSet<>();
        notEmptyResult.add("Erick Burgess");
        notEmptyResult.add("Erick Harrington harrington@gmail.com");
        menu.printResult(notEmptyResult);

        String expectedNotEmpty = "Number of persons found: 2\n"
                + "Erick Harrington harrington@gmail.com\n"
                + "Erick Burgess\n";

        assertEquals(expectedNotEmpty, outContent.toString());
    }
}