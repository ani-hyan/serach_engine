package org.example.simplesearch.search;

import org.example.simplesearch.datacontroller.DataProcessor;

import java.util.*;

public class SearchAll implements Search {
    @Override
    public Set<String> result(String[] input) {
        Set<Integer> commonIndexes = new HashSet<>();
        Set<String> allResult = new HashSet<>();

        if (input.length > 0) {
            commonIndexes.addAll(DataProcessor.getDataWithIndexes().getOrDefault(input[0], Collections.emptySet()));
            for (int i = 1; i < input.length; i++) {
                Set<Integer> indexesForCurrentString = DataProcessor.getDataWithIndexes().getOrDefault(input[i], Collections.emptySet());
                commonIndexes.retainAll(indexesForCurrentString);
            }
        }

        commonIndexes.forEach(x -> allResult.add(DataProcessor.getData().get(x)));

        return allResult;
    }

}
