package com.nitinrane.learning.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.ApiGatewayException;
import software.amazon.awssdk.services.apigateway.model.DeleteDeploymentRequest;

public class DeleteDeployment {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DeleteDeployment <restApiId> <deploymentId>\n\n" +
                "Where:\n" +
                "    restApiId - The string identifier of an existing RestApi. (for example, xxxx99ewyg).\n" +
                "    deploymentId - The string identifier of an existing deployment. \n" ;

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String restApiId = args[0];
        String deploymentId =  args[1];
        Region region = Region.US_EAST_1;
        ApiGatewayClient apiGateway = ApiGatewayClient.builder()
                .region(region)
                .build();

        deleteSpecificDeployment(apiGateway, restApiId, deploymentId);
        apiGateway.close();
     }

    public static void deleteSpecificDeployment(ApiGatewayClient apiGateway, String restApiId, String deploymentId) {

        try {
            DeleteDeploymentRequest request = DeleteDeploymentRequest.builder()
                .restApiId(restApiId)
                .deploymentId(deploymentId)
                .build();

            apiGateway.deleteDeployment(request);
            System.out.println("Deployment was deleted" );

        } catch (ApiGatewayException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}