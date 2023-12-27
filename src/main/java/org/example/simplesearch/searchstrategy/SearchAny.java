package org.example.simplesearch.searchstrategy;

import org.example.simplesearch.datacontroller.DataProcessor;

import java.util.HashSet;
import java.util.Set;

public class SearchAny implements Search {
    @Override
    public Set<String> result(String[] input) {
        Set<String> anyResult = new HashSet<>();

        for (String str: input) {
            if (DataProcessor.getDataWithIndexes().get(str) != null) {
                DataProcessor.getDataWithIndexes().get(str)
                        .forEach(x -> anyResult.add(DataProcessor.getData().get(x)));
            }

        }

        return anyResult;
    }
}
