package com.nitinrane.learning.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.*;
import java.util.List;

public class GetStages {
    public static void main(String[] args) {

        final String USAGE = "\n" +
            "Usage:\n" +
            "    GetStages <restApiId> \n\n" +
            "Where:\n" +
            "    restApiId - The string identifier of an existing RestApi. (for example, xxxx99ewyg).\n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String restApiId = args[0];
        Region region = Region.US_EAST_1;
        ApiGatewayClient apiGateway = ApiGatewayClient.builder()
            .region(region)
            .build();

        getAllStages(apiGateway, restApiId);
        apiGateway.close();
    }

    // snippet-start:[apigateway.java2.get_stages.main]
    public static void getAllStages(ApiGatewayClient apiGateway, String restApiId) {

        try {
            GetStagesRequest stagesRequest = GetStagesRequest.builder()
                .restApiId(restApiId)
                .build();

            GetStagesResponse response = apiGateway.getStages(stagesRequest);
            List<Stage> stages = response.item();
            for (Stage stage: stages) {
                System.out.println("Stage name is: "+stage.stageName());
            }

        } catch (ApiGatewayException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    // snippet-end:[apigateway.java2.get_stages.main]
}
