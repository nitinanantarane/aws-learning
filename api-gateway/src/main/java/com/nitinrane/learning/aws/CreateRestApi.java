package com.nitinrane.learning.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.CreateRestApiRequest;
import software.amazon.awssdk.services.apigateway.model.CreateRestApiResponse;
import software.amazon.awssdk.services.apigateway.model.ApiGatewayException;

public class CreateRestApi {

    public static void main(String[] args) {

        final String USAGE = "\n" +
            "Usage:\n" +
            "    CreateRestApi <restApiId> <restApiName>\n\n" +
            "Where:\n" +
            "    restApiId - The string identifier of an existing RestApi. (for example, xxxx99ewyg).\n" +
            "    restApiName - The name to use for the new RestApi. \n" ;

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }
        String restApiId = args[0];
        String restApiName = args[1];
        Region region = Region.AP_SOUTH_1;
        ApiGatewayClient apiGateway = ApiGatewayClient.builder()
            .region(region)
            .build();

        createAPI(apiGateway, restApiId, restApiName);
        apiGateway.close();
    }

    public static String createAPI( ApiGatewayClient apiGateway, String restApiId, String restApiName) {

        try {
            CreateRestApiRequest request = CreateRestApiRequest.builder()
                .cloneFrom(restApiId)
                .description("Created using the Gateway Java API")
                .name(restApiName)
                .build();

            CreateRestApiResponse response = apiGateway.createRestApi(request);
            System.out.println("The id of the new api is "+response.id());
            return response.id();

        } catch (ApiGatewayException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "";
    }
}
