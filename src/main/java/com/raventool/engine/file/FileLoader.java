package com.raventool.engine.file;

import java.io.IOException;

import com.raventool.model.request.RequestDetails;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.JsonNode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoader {

    private ObjectMapper mapper;
    private JsonNode root;
    private String basePath;

    public FileLoader(ObjectMapper mapper, JsonNode root) {
        this.mapper = mapper;
        this.root = root;
        this.basePath = resolveBasePath();
    }

    // private String resolveBasePath() {
    //     String prop = System.getProperty("raventool.basepath");
    //     if (prop != null && !prop.isBlank()) {
    //         return prop;
    //     }
    //     String env = System.getenv("RAVENTOOL_BASEPATH");
    //     if (env != null && !env.isBlank()) {
    //         return env;
    //     }
    //     return "raven/tests";
    // }

    protected void openFile(String filepath) throws IOException {
        root = mapper.readTree(
            Files.readString(Paths.get(basePath, filepath))
        );
    }

    public RequestDetails loadFromFile(String filepath) {

    }
}
