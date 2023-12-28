package org.example.simplesearch;

import org.example.simplesearch.datacontroller.DataProcessor;
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
    void dataFromFileIntoMapForDataWithIndexes() {
        DataProcessor.dataFromFileIntoMap("Data.txt");

        Map<String, Set<Integer>> dataWithIndexes = DataProcessor.getDataWithIndexes();

        assertFalse(dataWithIndexes.isEmpty());


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
    void dataFromFileIntoMapForData(){
        DataProcessor.dataFromFileIntoMap("Data.txt");

        ArrayList<String> data = DataProcessor.getData();

        assertFalse(data.isEmpty());

        assertEquals(6, data.size());

        assertAll(
                () -> assertEquals("Dwight Joseph djo@gmail.com", data.get(0)),
                () -> assertEquals("Rene Webb webb@gmail.com", data.get(1)),
                () -> assertEquals("Katie Jacobs", data.get(2)),
                () -> assertEquals("Erick Harrington harrington@gmail.com", data.get(3)),
                () -> assertEquals("Myrtle Medina", data.get(4)),
                () -> assertEquals("Erick Burgess", data.get(5))
        );
    }

    @Test
    void dataFromFileIntoMapForException(){
        String fileName = "file.txt";

        Exception exception = assertThrows(RuntimeException.class,
                () -> DataProcessor.dataFromFileIntoMap(fileName));

        String expectedMessage = "File not found: " + fileName;
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);

        assertTrue(actualMessage.contains(expectedMessage));
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