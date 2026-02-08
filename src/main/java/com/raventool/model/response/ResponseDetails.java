package com.raventool.model.response;

import tools.jackson.databind.JsonNode;

public record ResponseDetails(
    int status,
    JsonNode body,
    long time
) { }