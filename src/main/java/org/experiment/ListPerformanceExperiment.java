package org.experiment;

import io.vavr.collection.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

@Getter
@Setter
public class ListPerformanceExperiment {
    private java.util.List<String> arrayList = new ArrayList<>(Arrays.asList("one", "two", "three"));
    private java.util.List<String> linkedList = new LinkedList<>(Arrays.asList("one", "two", "three"));
    private static Logger logger = LogManager.getLogger(ListPerformanceExperiment.class);

    String middle = "middle";
    String head = "head";
    String tail = "tail";

    public void performance() {
        vavrListCalculateTime(10000, head);
        vavrListCalculateTime(10000, middle);
        vavrListCalculateTime(10000, tail);
        javaListCalculateTime(10000, head, arrayList);
        javaListCalculateTime(10000, middle, arrayList);
        javaListCalculateTime(10000, tail, arrayList);
        javaListCalculateTime(10000, head, linkedList);
        javaListCalculateTime(10000, middle, linkedList);
        javaListCalculateTime(10000, tail, linkedList);
    }

    private void vavrListCalculateTime(long times, String position) {
        List<String> vavrList = List.of("one", "two", "three");
        long startTime = System.nanoTime();
        vavrListExecution(times, vavrList, position);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        logger.info(vavrList.getClass() + " " + position + " " + totalTime);
    }

    private void javaListCalculateTime(long times, String position, java.util.List<String> javaList) {
        long startTime = System.nanoTime();
        javaListExecution(times, javaList, position);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        logger.info(javaList.getClass() + " " + position + " " + totalTime);
    }

    private static void vavrListExecution(long times, List<String> vavrList, String position) {
        for (long i = 0; i < times; i++) {
            switch (position) {
                case "head":
                    vavrList = vavrList.push("new");
                    break;
                case "middle":
                    int half = (int) Math.floorDiv(i, 2);
                    vavrList = vavrList.insert(half, "new");
                    break;
                case "tail":
                    vavrList = vavrList.append("new");
                    break;
                default:
                    break;
            }
        }
    }

    private static void javaListExecution(long times, java.util.List<String> javaList, String position) {
        for (long i = 0; i < times; i++) {
            switch (position) {
                case "head":
                    javaList.add(0, "new");
                    break;
                case "middle":
                    int half = (int) Math.floorDiv(i, 2);
                    javaList.add(half, "new");
                    break;
                case "tail":
                    javaList.add("new");
                    break;
                default:
                    break;
            }
        }
    }

}

