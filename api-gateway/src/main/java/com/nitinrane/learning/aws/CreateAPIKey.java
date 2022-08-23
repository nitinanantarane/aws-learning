package com.nitinrane.learning.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.*;

public class CreateAPIKey {

    public static void main(String[] args) {

        Region region = Region.AP_SOUTH_1;
        ApiGatewayClient apiGateway = ApiGatewayClient.builder()
                .region(region)
                .build();

        createApiKey(apiGateway);
        apiGateway.close();
    }

    public static void createApiKey(ApiGatewayClient apiGateway) {

        try {
            CreateApiKeyRequest apiKeyRequest = CreateApiKeyRequest.builder()
                    .name("TestKey66")
                    .description("Key description")
                    .enabled(true)
                    .generateDistinctId(true)
                    .build();

            //Creating a api key
            CreateApiKeyResponse response = apiGateway.createApiKey(apiKeyRequest);

            // If we have a plan for the api keys, we can set it for the created api key.
            CreateUsagePlanKeyRequest planRequest = CreateUsagePlanKeyRequest.builder()
                    .usagePlanId("q18zxs")
                    .keyId(response.id())
                    .keyType("API_KEY")
                    .build();

            apiGateway.createUsagePlanKey(planRequest);
            apiGateway.close();

        } catch (ApiGatewayException e) {
          System.err.println(e.awsErrorDetails().errorMessage());
          System.exit(1);
        }
    }
}