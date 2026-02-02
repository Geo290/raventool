package com.raventool.engine.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Map;

import com.raventool.model.request.RequestDetails;

import tools.jackson.databind.JsonNode;

public class RequestService {

    private String method;
    private URI url;
    private JsonNode headers;
    private String body;

    public RequestService(RequestDetails details) {
        this.method = details.method().toUpperCase();
        this.url = details.url();
        this.headers = details.headers();
        this.body = details.body().toString();
    }

    public HttpRequest.Builder setHeaders(HttpRequest.Builder requestBuilder) {
        if (headers != null && headers.isObject()) {
            for (Map.Entry<String, JsonNode> entry : headers.properties()) {
                requestBuilder.header(
                    entry.getKey(), entry.getValue().asString()
                );
            }
        }
        return requestBuilder;
    }

    public HttpRequest buildRequest() {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(url);
        builder = setHeaders(builder);

        return switch (method) {

            case "GET" -> builder.GET().build();

            case "POST" -> builder.POST(
                    BodyPublishers.ofString(body)) // remember to serialize body to string
                    .build();

            case "PUT" -> builder.PUT(
                    BodyPublishers.ofString(body))
                    .build();

            case "DELETE" -> builder.DELETE().build();

            default -> throw new IllegalArgumentException("Method not supported");
        };
    }
}
