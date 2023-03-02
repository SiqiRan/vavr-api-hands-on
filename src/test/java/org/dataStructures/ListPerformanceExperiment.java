package org.dataStructures;

import io.vavr.collection.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ListPerformanceExperiment {
    List<String> vavrList = List.of("one", "two", "three");
    java.util.List<String> arrayList = new ArrayList<>(Arrays.asList("one", "two", "three"));
    java.util.List<String> linkedList = new LinkedList<>(Arrays.asList("one", "two", "three"));

    void insertionEfficiency() {
        long n = 1000;
        long startTime = System.nanoTime();
        //10000 times insertion in vavrList
        for (int i = 0; i < n; i++) {
            vavrList = vavrList.append("ok");
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int floorHalf = Math.floorDiv(i, 2);
            arrayList.add(floorHalf, "ok");
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime);
        //10000 times insertion in javaList

        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int floorHalf = Math.floorDiv(i, 2);
            linkedList.add(floorHalf, "ok");
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime);
    }
}
