package ru.digitalhabbits.homework2.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;

import static org.assertj.core.api.Assertions.entry;

class LetterCounterImplTest {

    @Test
    void count() {
        String line = "asdsfqfeq";
        LetterCounter letterCounter = new LetterCounterImpl();
        Map<Character, Long> letterCountMap = letterCounter.count(line);

        Assertions.assertThat(letterCountMap).containsOnly(
                entry('a', 1L),
                entry('d', 1L),
                entry('e', 1L),
                entry('f', 2L),
                entry('q', 2L),
                entry('s', 2L)
        );
    }
}