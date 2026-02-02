package com.raventool.engine.file;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;

public class FileLoaderTests {

    public static String filePath = "raven/tests/test.json";
    public static JsonNode root = FileLoader.loadFile(filePath);

    @Test
    void testIsFileLoaded() {
        try {
            assertTrue(root.has("url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testIsJsonParsedIntoRequestDetails() {
        try {
            var requestDetails = FileLoader.parseRequestDetails(root);
            assertTrue(requestDetails.getClass() == com.raventool.model.request.RequestDetails.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
