package org.example.simplesearch;

import lombok.Getter;
import java.io.IOException;
import java.net.URISyntaxException;
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

    public static void dataFromFileIntoMap(String fileName){
        try{
            Path path = Paths.get(Objects.requireNonNull(DataProcessor.class.getClassLoader()
                    .getResource(fileName)).toURI());


            Stream<String> lines = Files.lines(path);
            lines.map(line -> line.split(":"))
                    .forEach(parts -> {
                        setDataWithIndexes(parts[0].trim(), parts[1].trim());
                        data.add(parts[1].trim());
                    });

            lines.close();

        } catch (NullPointerException | URISyntaxException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void setDataWithIndexes(String index, String data){
        Arrays.stream(data.split(" "))
                .forEach(x -> putWordIntoMap(x.toLowerCase(), Integer.valueOf(index)));
    }

    public static void putWordIntoMap(String word, Integer index){
        if(!dataWithIndexes.containsKey(word)){
            dataWithIndexes.put(word, new HashSet<>());
        }
        dataWithIndexes.get(word).add(index);
    }

    public static void printData(){
        data.forEach(System.out::println);
        System.out.println();
    }

}
