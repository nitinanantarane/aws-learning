package com.nitinrane.learning.aws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.core.SdkBytes;
// snippet-start:[apigateway.java2.import_api.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.ImportRestApiRequest;
import software.amazon.awssdk.services.apigateway.model.ImportRestApiResponse;
import software.amazon.awssdk.services.apigateway.model.ApiGatewayException;

public class ImportRestApi {

    public static void main(String[] args) {

        final String USAGE = "\n" +
            "Usage:\n" +
            "    ImportRestApi <swaggerFilePath>\n\n" +
            "Where:\n" +
            "    swaggerFilePath - The file path of a swagger specifications file. (for example, example-api.json - also provided in the project - from the console example api).\n" +
            "    If swaggerFilePath is not provided it will default to example-api.json. \n" +
            "    If constructing own api.json files might need to improve the json file. \n" +
            "    Also useful to add x-amazon-apigateway-integration as shown in the example-api.json. \n" ;
        String swaggerFilePath="example-api.json";
        if (args.length == 0) {
            System.out.println(USAGE);
            
        }
        else
        {
        	swaggerFilePath = args[0];
        }
        System.out.println("Using  swaggerFilePath="+swaggerFilePath);
  
       
        Region region = Region.US_EAST_1;
        ApiGatewayClient apiGateway = ApiGatewayClient.builder()
            .region(region)
            .build();

        importAPI(apiGateway, swaggerFilePath);
        apiGateway.close();
    }

    public static String importAPI( ApiGatewayClient apiGateway, String swaggerFilePath) {
    	Map<String, String> parameters= new HashMap<>();
    	parameters.put("endpointConfigurationTypes", "REGIONAL");
        try( FileInputStream fis=new FileInputStream(new File(swaggerFilePath))) {
        	ImportRestApiRequest request = ImportRestApiRequest.builder()
                .failOnWarnings(true)
                .parameters(parameters)
                .body(SdkBytes.fromInputStream(fis))
                .build();

        	ImportRestApiResponse response = apiGateway.importRestApi(request);
            System.out.println("The id of the new api is "+response.id());
            return response.id();

        } catch (ApiGatewayException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        } catch (IOException e) {
        	System.err.println(e.getMessage());
            System.exit(1);
		} 
        return "";
    }
}
