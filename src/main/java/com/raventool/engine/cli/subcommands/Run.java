package com.raventool.engine.cli.subcommands;

import com.raventool.engine.file.FileLoader;
import com.raventool.model.request.RequestDetails;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import tools.jackson.databind.JsonNode;

@Command(name = "run", description = "Excute a test case")
public class Run implements Runnable {
    @Parameters(index = "0", description = "Path tho the test case file")
    private String testCasePath;

    @Override
    public void run() {
        try {
            JsonNode rootNode = FileLoader.loadFile(testCasePath);
            RequestDetails requestDetails = FileLoader.parseRequestDetails(rootNode);
            System.out.println(requestDetails.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
