package com.nitinrane.learning.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.GetDeploymentsRequest;
import software.amazon.awssdk.services.apigateway.model.GetDeploymentsResponse;
import software.amazon.awssdk.services.apigateway.model.Deployment;
import software.amazon.awssdk.services.apigateway.model.ApiGatewayException;
import java.util.List;

public class GetDeployments {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetDeployments <restApiId> \n\n" +
                "Where:\n" +
                "    restApiId - The string identifier of an existing RestApi. (for example, xxxx99ewyg).\n" ;

       if (args.length != 1) {
           System.out.println(USAGE);
           System.exit(1);
        }

       String restApiId  =  "inx39975"; // args[0];
       Region region = Region.US_EAST_1;
       ApiGatewayClient apiGateway = ApiGatewayClient.builder()
            .region(region)
            .build();

       getAllDeployments(apiGateway, restApiId);
       apiGateway.close();
    }

    public static void getAllDeployments(ApiGatewayClient apiGateway,  String restApiId) {

        try {
            GetDeploymentsRequest request = GetDeploymentsRequest.builder()
               .restApiId(restApiId)
               .build();

            GetDeploymentsResponse response = apiGateway.getDeployments(request);
            List<Deployment> deployments = response.items();
            for (Deployment deployment: deployments) {
                System.out.println("The deployment id is "+deployment.id());
                System.out.println("The deployment description is "+deployment.description());
            }

        } catch (ApiGatewayException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}