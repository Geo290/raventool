package com.raventool.engine.file;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import com.raventool.model.request.RequestDetails;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;

public class FileLoader {

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode loadFile(String path) throws IllegalArgumentException, JacksonException {
        File file = new File(path);

        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: " + path);
        }

        return mapper.readTree(file);
    }

    public static RequestDetails parseRequestDetails(JsonNode node) throws URISyntaxException {
        return new RequestDetails(
                new URI(node.get("url").asString()),
                node.get("method").asString(),
                node.get("headers"),
                node.get("body"));
    }
}
