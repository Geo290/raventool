package com.raventool.engine.http;

import java.net.http.HttpResponse.*;
import java.util.concurrent.CompletableFuture;

import com.raventool.engine.file.FileLoader;
import com.raventool.model.request.RequestDetails;

import tools.jackson.databind.JsonNode;

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

    public CompletableFuture<String> getBody(CompletableFuture<HttpResponse<String>> future) {
        return future.thenApplyAsync(HttpResponse::body);
    }

    public CompletableFuture<Object> getStatus(CompletableFuture<HttpResponse<String>> future) {
        return future.thenApplyAsync(HttpResponse::statusCode);
    }

    public static void getTime(CompletableFuture<HttpResponse<String>> future, long start) {
        future.whenComplete((response, throwable) -> {
            long end = System.nanoTime();
            long durationMs = (end - start) / 1_000_000;

            if (throwable != null) {
                System.err.println("Request Failed after " + durationMs + " ms");
                throwable.printStackTrace();

            } else {
                System.err.println("Response Time: " + durationMs + " ms");
            }
        });
    }
}