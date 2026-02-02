package com.raventool.engine.http;

import java.net.http.HttpResponse.*;

import com.raventool.engine.file.FileLoader;
import com.raventool.model.request.RequestDetails;

import tools.jackson.databind.JsonNode;

import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.io.IOException;

public class ClientService {
    private HttpRequest request;
    private HttpClient client;
    private BodyHandler<String> responseBodyHandlers;

    public ClientService(HttpRequest request) {
        this.request = request;
        this.client = HttpClient.newHttpClient();
        this.responseBodyHandlers = BodyHandlers.ofString();
    }

    // public static void main(String[] args) { Remeber to delete it
    //     try {
    //         JsonNode rootNode = FileLoader.loadFile("C:/Users/Geo29/Documents/raventool/raven/tests/test.json");
    //         RequestDetails requestDetails = FileLoader.parseRequestDetails(rootNode);
    //         HttpRequest request = new RequestService(requestDetails).buildRequest();
    //         HttpResponse<String> response = new ClientService(request).sendRequest();
    //         System.out.println(response.body().toString());

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public HttpResponse<String> sendRequest() {
        try {
            HttpResponse<String> response = client.send(request, responseBodyHandlers);
            return response;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}