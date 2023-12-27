package org.example.simplesearch.seachengine;

import org.example.simplesearch.datacontroller.DataProcessor;
import org.example.simplesearch.search.Search;
import org.example.simplesearch.search.SearchAll;
import org.example.simplesearch.search.SearchAny;
import org.example.simplesearch.search.SearchNone;

import java.util.*;

public class Menu {
    private final Scanner sc;

    private final String[] options;

    public Menu() {
        sc = new Scanner(System.in);
        options = new String[]{
                "=== Menu ===",
                "1. Find a person",
                "2. Print all people",
                "0. Exit"
        };
    }


    public void start() {
        System.out.println("Enter the file name: ");
        String fileName = sc.next();
        DataProcessor.dataFromFileIntoMap(fileName);

        int option;

        do {
            printMenu();
            option = validateOption(sc.nextInt());

            switch (option) {
                case 1:
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    Set<String> s = searchBasedOnStrategy(validateStrategy(sc.next()));
                    printResult(s);
                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    DataProcessor.printData();
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
            }
        } while (option != 0);

    }


    public Set<String> searchBasedOnStrategy(String strategy) {
        System.out.println("Enter a name or email to search all suitable people.");
        sc.nextLine();
        String[] input = sc.nextLine().toLowerCase().split(" ");

        Search search = createSearch(strategy);

        assert search != null;
        return search.result(input);
    }

    private Search createSearch(String strategy) {
        return switch (strategy.toUpperCase()) {
            case "ANY" -> new SearchAny();
            case "ALL" -> new SearchAll();
            case "NONE" -> new SearchNone();
            default -> null;
        };
    }
    public void printMenu() {
        Arrays.stream(options).forEach(System.out::println);
    }

    private int validateOption(int option) {
        while (option < 0 || option > 2) {
            System.out.println("Incorrect option! Try again.");
            option = sc.nextInt();
        }

        return option;
    }

    public void printResult(Set<String> result){
        if (result != null && !result.isEmpty()) {
            System.out.println(result.size() + " persons found:");
            result.forEach(System.out::println);
        } else {
            System.out.println("Nothing to show");
        }
    }

    private String validateStrategy(String strategy){
        while(!strategy.toUpperCase().matches("\\b(?:ALL|ANY|NONE)\\b")){
            System.out.println("Incorrect strategy! Try again.");
            strategy = sc.nextLine();
        }

        return strategy;
    }
}
