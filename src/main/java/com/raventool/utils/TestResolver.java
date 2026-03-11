package com.raventool.utils;

import java.util.Iterator;
import java.util.Objects;
import java.util.Map.Entry;

import com.raventool.model.response.ResponseDetails;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class TestResolver {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedNode = mapper.createObjectNode().put("language", "Java");
        JsonNode actualNode = mapper.createObjectNode().put("language", "python");

        ResponseDetails expectedResponse = new ResponseDetails(204, expectedNode, 98);
        ResponseDetails actualResponse = new ResponseDetails(204, actualNode, 100);

        test(expectedResponse, actualResponse);
    }

    public static boolean assertStatus(int expectedStatus, int actualStatus) {
        return expectedStatus == actualStatus;
    }

    public static boolean assertBody(JsonNode expectedResponseBody, JsonNode actualResponseBody) {
        return Objects.equals(expectedResponseBody, actualResponseBody);
    }

    public static boolean assertValue(Object expected, Object actual) {
        return Objects.equals(expected, actual);
    }

    public static void test(ResponseDetails expectedResponse, ResponseDetails actualResponse) {
        int expectedStatus = expectedResponse.status();
        int actualStatus = actualResponse.status();
        JsonNode expectedBody = expectedResponse.body();
        JsonNode actualBody = actualResponse.body();

        System.out.println("= = = = STARTING TESTS = = = =");

        if (!assertStatus(actualStatus, expectedStatus)) {
            System.out.println("STATUS ASSERTION FAILED");

            System.out.printf(
                    "Asserting Status[%d] == [%d] %s\n",
                    expectedStatus,
                    actualStatus,
                    "FAILED");

            System.out.println("ABORTING TESTS");
            return;
        } else {
            System.out.printf(
                    "Asserting Status[%d] == [%d] %s\n",
                    expectedStatus,
                    actualStatus,
                    "SUCCEEDED");
        }

        Iterator<Entry<String, JsonNode>> expectedProps = expectedBody.properties().iterator();

        while (expectedProps.hasNext()) {
            Entry<String, JsonNode> entry = expectedProps.next();
            String key = entry.getKey();
            JsonNode valueE = entry.getValue();
            JsonNode valueA = actualBody.get(key);

            if (valueE != null) {
                String valueAsserted = assertValue(valueE, valueA) ? "PASSED" : "FAILED";
                System.out.printf("Assert %s [%s] == [%s] %s\n",
                        key.toUpperCase(),
                        String.valueOf(valueE),
                        String.valueOf(valueA),
                        valueAsserted);
            }

            System.out.println("==================");

        }
        System.out.println("= = = = FINISHED = = = =");
    }
}