package org.example.simplesearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DataProcessorTest {

    @BeforeEach
    void setUp() {
        DataProcessor.getDataWithIndexes().clear();
        DataProcessor.getData().clear();
    }

    @Test
    void dataFromFileIntoMap() {

        assertThrows(RuntimeException.class, () -> DataProcessor.dataFromFileIntoMap("file.txt"));

        DataProcessor.dataFromFileIntoMap("Data.txt");

        ArrayList<String> data = DataProcessor.getData();
        Map<String, Set<Integer>> dataWithIndexes = DataProcessor.getDataWithIndexes();

        assertFalse(data.isEmpty());
        assertFalse(dataWithIndexes.isEmpty());

        assertEquals(6, data.size());

        assertAll(
                () -> assertEquals("Dwight Joseph djo@gmail.com", data.get(0)),
                () -> assertEquals("Rene Webb webb@gmail.com", data.get(1)),
                () -> assertEquals("Katie Jacobs", data.get(2)),
                () -> assertEquals("Erick Harrington harrington@gmail.com", data.get(3)),
                () -> assertEquals("Myrtle Medina", data.get(4)),
                () -> assertEquals("Erick Burgess", data.get(5))
        );

        assertAll(
                () -> assertTrue(dataWithIndexes.containsKey("dwight")),
                () -> assertTrue(dataWithIndexes.containsKey("harrington")),
                () -> assertTrue(dataWithIndexes.containsKey("erick")),
                () -> assertTrue(dataWithIndexes.containsKey("medina"))
        );

        assertEquals(1, dataWithIndexes.get("dwight").size());
        assertEquals(2, dataWithIndexes.get("erick").size());
        assertArrayEquals(new ArrayList<>(List.of(3, 5)).toArray(), dataWithIndexes.get("erick").toArray());
    }


    @Test
    void putWordIntoMap() {
        DataProcessor.putWordIntoMap("erick", 3);
        Map<String, Set<Integer>> dataWithIndexes = DataProcessor.getDataWithIndexes();

        assertTrue(dataWithIndexes.containsKey("erick"));
        assertEquals(1, dataWithIndexes.get("erick").size());

        DataProcessor.putWordIntoMap("erick", 5);
        assertEquals(2, dataWithIndexes.get("erick").size());
        assertTrue(dataWithIndexes.get("erick").contains(5));
        assertTrue(dataWithIndexes.get("erick").contains(3));

    }


    @Test
    void setDataWithIndexes() {
        DataProcessor.setDataWithIndexes("0", "Dwight Joseph djo@gmail.com");

        Map<String, Set<Integer>> dataWithIndexes = DataProcessor.getDataWithIndexes();

        assertEquals(3, DataProcessor.getDataWithIndexes().keySet().size());

        assertAll(
                () -> assertEquals(new HashSet<>(Set.of(0)), dataWithIndexes.get("dwight")),
                () -> assertEquals(new HashSet<>(Set.of(0)), dataWithIndexes.get("joseph")),
                () -> assertEquals(new HashSet<>(Set.of(0)), dataWithIndexes.get("djo@gmail.com"))
        );
    }


}