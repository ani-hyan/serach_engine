package org.example.simplesearch.searchstrategy;

import org.example.simplesearch.datacontroller.DataProcessor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchNone implements Search {
    @Override
    public Set<String> result(String[] input) {
        Set<String> anyResult = new HashSet<>();
        Set<String> noneResult = new HashSet<>();

        for (String str: input) {
            if (DataProcessor.getDataWithIndexes().get(str) != null) {
                DataProcessor.getDataWithIndexes().get(str)
                        .forEach(x -> anyResult.add(DataProcessor.getData().get(x)));
            }

        }

        noneResult.add(DataProcessor.getData()
                .stream()
                .filter(x -> !anyResult.contains(x)).collect(Collectors.joining("\n")));

        return noneResult;
    }
}
