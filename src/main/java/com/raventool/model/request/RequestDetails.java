package com.raventool.model.request;

// import java.net.URL; // remember to remove URI once in production
import java.net.URI; 
import tools.jackson.databind.JsonNode;

public record RequestDetails(
    URI url,
    String method,
    JsonNode headers,
    JsonNode body 
) { }