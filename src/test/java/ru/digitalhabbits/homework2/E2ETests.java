package ru.digitalhabbits.homework2;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.impl.AsyncFileLetterCounter;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static ru.digitalhabbits.homework2.FileUtils.getFile;

public class E2ETests {

    @Test
    void async_file_letter_counting_should_return_predicted_count() {
        var file = getFile("test.txt");
        var counter = new AsyncFileLetterCounter();

        Map<Character, Long> count = counter.count(file);

        assertThat(count).containsOnly(
                entry('a', 2697L),
                entry('b', 2683L),
                entry('c', 2647L),
                entry('d', 2613L),
                entry('e', 2731L),
                entry('f', 2629L)
        );
    }
}
