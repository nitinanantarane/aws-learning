package com.nitinrane.learning.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.GetApiKeysResponse;
import software.amazon.awssdk.services.apigateway.model.ApiKey;
import software.amazon.awssdk.services.apigateway.model.ApiGatewayException;
import java.util.List;

public class GetAPIKeys {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1;
        ApiGatewayClient apiGateway = ApiGatewayClient.builder()
                .region(region)
                .build();

        getKeys(apiGateway);
        apiGateway.close();
    }

    public static void getKeys(ApiGatewayClient apiGateway) {

        try {
            GetApiKeysResponse response = apiGateway.getApiKeys();
            List<ApiKey> keys = response.items();
            for (ApiKey key: keys) {
                System.out.println("key id is: "+key.id());
                System.out.println("key name is: "+key.name());
            }

        } catch (ApiGatewayException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}