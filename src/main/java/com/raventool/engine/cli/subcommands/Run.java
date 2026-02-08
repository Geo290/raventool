package com.raventool.engine.cli.subcommands;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

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
        JsonNode rootNode;
        HttpRequest request = null;
        ClientService client;
        RequestDetails requestDetails;
        CompletableFuture<HttpResponse<String>> futureResponse;

        try {
            rootNode = FileLoader.loadFile("raven/tests/test.json");
            requestDetails = FileLoader.parseRequestDetails(rootNode);
            request = new RequestService(requestDetails).buildRequest();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        client = new ClientService(request);
        futureResponse = client.sendRequest();
    }
}
