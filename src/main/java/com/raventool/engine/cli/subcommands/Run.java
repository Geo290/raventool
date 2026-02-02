package com.raventool.engine.cli.subcommands;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.raventool.engine.file.FileLoader;
import com.raventool.engine.http.RequestService;
import com.raventool.model.request.RequestDetails;
import com.raventool.engine.http.ClientService;

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
            HttpRequest request = new RequestService(requestDetails).buildRequest();
            HttpResponse<String> response = new ClientService(request).sendRequest();
            System.out.println(response.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
