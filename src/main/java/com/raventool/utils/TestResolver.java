package com.raventool.utils;
import java.util.Iterator;
import java.util.Map.Entry;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class TestResolver {

    // public static void main(String[] args) {
    //     String json1 = """
    //             {
    //               "name": "Alice",
    //               "age": 30,
    //               "skills": ["Java", "Python"],
    //               "address": { "city": "NY", "zip": "10001" }
    //             }
    //             """;

    //     String json2 = """
    //             {
    //               "name": "Alice",
    //               "age": 30.0,
    //               "skills": ["Java", "Go"],
    //               "address": { "city": "NY", "zip": "10002" }
    //             }
    //             """;

    //     ObjectMapper mapper = new ObjectMapper();

    //     JsonNode node1 = mapper.readTree(json1);
    //     JsonNode node2 = mapper.readTree(json2);
    //     testResults(node1, node2);

    // }

    public static void testResults(JsonNode actualResponseBody, JsonNode expectedRespondeBody) {
        Iterator<Entry<String, JsonNode>> expectedProps = expectedRespondeBody.properties().iterator();

        while (expectedProps.hasNext()) {
            Entry<String, JsonNode> entry = expectedProps.next();
            String key = entry.getKey();
            JsonNode valueE = entry.getValue();
            JsonNode valueA = actualResponseBody.get(key);

            if (valueE != null) {
                // return false;
                String mote = valueE.equals(valueA) ? " SUCCESS" : " FAIL";
                System.out.println(valueE + " == " + valueA + mote);
            }

            System.out.println("==================");

        }
        System.out.println("= = = = FINISHED = = = =");
        // return actualResponseBody.size() == expectedRespondeBody.size();
    }
}