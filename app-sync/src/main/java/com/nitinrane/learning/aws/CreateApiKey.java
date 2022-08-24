/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.nitinrane.learning.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.appsync.AppSyncClient;
import software.amazon.awssdk.services.appsync.model.AppSyncException;
import software.amazon.awssdk.services.appsync.model.CreateApiKeyRequest;
import software.amazon.awssdk.services.appsync.model.CreateApiKeyResponse;

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class CreateApiKey {

    public static void main(String[] args) {

        final String usage = "\n" +
                "Usage: " +
                "   <apiId> \n\n" +
                "Where:\n" +
                "   apiId - the id of the API (You can get this value from the AWS Management Console). \n\n" ;

        if (args.length != 1) {
             System.out.println(usage);
             System.exit(1);
        }

        String apiId = args[0];
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_EAST_1;
        AppSyncClient appSyncClient = AppSyncClient.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();

        String id = createKey(appSyncClient, apiId) ;
        System.out.println("The Id of the new Key is "+id);
        }

    public static String createKey(AppSyncClient appSyncClient, String apiId) {

        try {
            CreateApiKeyRequest apiKeyRequest = CreateApiKeyRequest.builder()
                .apiId(apiId)
                .description("Created using the AWS SDK for Java")
                .build();

         CreateApiKeyResponse response = appSyncClient.createApiKey(apiKeyRequest);
         return response.apiKey().id();

        } catch (AppSyncException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return "";
    }
}
