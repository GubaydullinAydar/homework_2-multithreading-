package ru.digitalhabbits.homework2.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.entry;

class LetterCountMergerImplTest {

    @Test
    void merge() {
        LetterCountMerger letterCountMerger = new LetterCountMergerImpl();

        Map<Character, Long> firstMap = new HashMap<>();
        firstMap.put('a', 3L);
        firstMap.put('f', 2L);
        firstMap.put('q', 8L);
        firstMap.put('s', 8L);

        Map<Character, Long> result = new HashMap<>();
        result.put('a', 3L);
        result.put('d', 2L);
        result.put('e', 1L);

        Assertions.assertThat(letterCountMerger.merge(firstMap, result)).containsOnly(
                entry('a', 6L),
                entry('d', 2L),
                entry('e', 1L),
                entry('f', 2L),
                entry('q', 8L),
                entry('s', 8L)
        );
    }

    @Test
    void mergeWithEmptyResultMap() {
        LetterCountMerger letterCountMerger = new LetterCountMergerImpl();

        Map<Character, Long> firstMap = new HashMap<>();
        firstMap.put('a', 3L);
        firstMap.put('f', 2L);
        firstMap.put('q', 8L);
        firstMap.put('s', 8L);

        Map<Character, Long> result = new HashMap<>();

        Assertions.assertThat(letterCountMerger.merge(firstMap, result)).containsOnly(
                entry('a', 3L),
                entry('f', 2L),
                entry('q', 8L),
                entry('s', 8L)
        );
    }
}