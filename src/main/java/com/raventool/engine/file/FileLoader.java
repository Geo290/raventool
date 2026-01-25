package com.raventool.engine.file;

import java.io.File;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;

public class FileLoader {

    private static ObjectMapper mapper = new ObjectMapper();
    private static JsonNode rootNode = null;

    public static void main(String[] args) {

        try {
            rootNode = loadFile("C://Users/Geo29/Documents/raventool/raven/tests/test.json");
            System.out.println(rootNode.toPrettyString());

        } catch (JacksonException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static JsonNode loadFile(String path) throws IllegalArgumentException, JacksonException {
        File file = new File(path);

        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: " + path);
        }

        return mapper.readTree(file);
    }

}
