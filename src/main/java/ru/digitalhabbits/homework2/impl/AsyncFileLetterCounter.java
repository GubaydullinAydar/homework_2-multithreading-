package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//todo Make your impl
public class AsyncFileLetterCounter implements FileLetterCounter {

    @Override
    public Map<Character, Long> count(File input) {
        FileReader readerFileByLine = new FileReaderImpl();
        Queue<String> stringLinesFromFile = new ConcurrentLinkedQueue<>();

        readerFileByLine.readLines(input).forEach(stringLinesFromFile::add);

        LetterCounter letterCounter = new LetterCounterImpl();
        LetterCountMerger letterCountMerger = new LetterCountMergerImpl();

        Queue<Map<Character, Long>> mapsLetterCounter = new ConcurrentLinkedQueue<>();
        Map<Character, Long> resultMap = new HashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Lock lock = new ReentrantLock();

        while (!stringLinesFromFile.isEmpty()) {
            executorService.submit(() -> {
                lock.lock();
                mapsLetterCounter.add(letterCounter.count(stringLinesFromFile.poll()));
                lock.unlock();
            });

            executorService.submit(() -> {
                lock.lock();
                letterCountMerger.merge(mapsLetterCounter.poll(), resultMap);
                lock.unlock();
            });
        }
        executorService.shutdown();

        return resultMap;
    }
}
