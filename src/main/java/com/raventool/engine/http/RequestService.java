package com.raventool.engine.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

import com.raventool.model.request.RequestDetails;

public class RequestService {

    private String method;
    private URI url;
    private String headerName;
    private String headerValue;
    // private String authorization;
    private String body;

    public RequestService(RequestDetails details) {
        this.method = details.method().toUpperCase();
        this.url = details.url();
        this.headerName = details.headers().get(0).asString(); // for now we will use only one header
        this.headerValue = details.headers().get(1).asString();
        // this.authorization = details.autorization(); // Aurhorization must go inside
        // headers. So we do not need this field. Remove later
        this.body = details.body().asString();
    }

    public HttpRequest buildRequest(RequestDetails details) {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(url) // look forward to add query parameters if any
                .headers(
                    // headers() allow multiple entries unlike header()
                    // so iterate through headers field will be necessary
                    // for now just one header will be used
                    headerName, headerValue);

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
