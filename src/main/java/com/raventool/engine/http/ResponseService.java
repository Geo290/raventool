package com.raventool.engine.http;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import com.raventool.model.response.ResponseDetails;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class ResponseService {
    private ObjectMapper mapper;

    public ResponseService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ResponseDetails parseResponseDetails(CompletableFuture<HttpResponse<String>> future, long start) {
        return new ResponseDetails(
                getStatus(future),
                getBody(future),
                getTime(future, start));
    }

    public JsonNode getBody(CompletableFuture<HttpResponse<String>> future) {
        return mapper.stringNode(
                future.thenApplyAsync(HttpResponse::body).join());
    }

    public int getStatus(CompletableFuture<HttpResponse<String>> future) {
        return future.thenApplyAsync(HttpResponse::statusCode).join();
    }

    public long getTime(CompletableFuture<HttpResponse<String>> future, long start) {
        return future.handle((response, throwable) -> {
            long end = System.nanoTime();
            return (end - start) / 1_000_000;
        }).join();
    }
}
