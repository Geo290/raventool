package com.raventool.engine.http;

import java.net.http.HttpResponse.*;
import java.util.concurrent.CompletableFuture;

import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

public class ClientService {
    private HttpRequest request;
    private HttpClient client;
    private BodyHandler<String> responseBodyHandlers;

    public ClientService(HttpRequest request) {
        this.request = request;
        this.client = HttpClient.newHttpClient();
        this.responseBodyHandlers = BodyHandlers.ofString();
    }

    public CompletableFuture<HttpResponse<String>> sendRequest() {
        return client.sendAsync(request, responseBodyHandlers);
    }
}