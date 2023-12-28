package org.example.simplesearch.datacontroller;

import lombok.Getter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DataProcessor {
    @Getter
    private static final Map<String, Set<Integer>> dataWithIndexes = new HashMap<>();
    @Getter
    private static final ArrayList<String> data = new ArrayList<>();

    public static void dataFromFileIntoMap(String fileName) {
        try {
            URL resourceUrl = DataProcessor.class.getClassLoader().getResource(fileName);

            if (resourceUrl == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            Path path = Paths.get(resourceUrl.toURI());

            try (Stream<String> lines = Files.lines(path)) {
                lines.map(line -> line.split(":"))
                        .forEach(parts -> {
                            setDataWithIndexes(parts[0].trim(), parts[1].trim());
                            data.add(parts[1].trim());
                        });
            }
            catch (IOException e) {
                throw new RuntimeException("Error reading file: " + fileName);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException("URI Syntax error: " + e.getMessage());
        }
    }

    public static void setDataWithIndexes(String index, String data) {
        Arrays.stream(data.split(" "))
                .forEach(x -> dataWithIndexes.computeIfAbsent(x.toLowerCase(), k -> new HashSet<>())
                        .add(Integer.valueOf(index)));
    }

    public static void printData() {
        data.forEach(System.out::println);
        System.out.println();
    }
}
