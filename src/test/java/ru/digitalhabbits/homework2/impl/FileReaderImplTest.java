package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.FileReader;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.digitalhabbits.homework2.FileUtils.getFile;

class FileReaderImplTest {

    @Test
    void readLinesFileWithEmptyString() {
        FileReader fileReader = new FileReaderImpl();
        File fileWithEmptyStrings = getFile("fileWithEmptyString.txt");
        assertThat(fileReader.readLines(fileWithEmptyStrings).count()).isEqualTo(4L);
    }
}