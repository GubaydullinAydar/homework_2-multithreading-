package ru.digitalhabbits.homework2;

import java.io.File;

import static com.google.common.io.Resources.getResource;

public class FileUtils {

    public static File getFile(String name) {
        return new File(getResource(name).getPath());
    }
}
