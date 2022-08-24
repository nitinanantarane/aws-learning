/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.nitinrane.learning.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.appsync.AppSyncClient;
import software.amazon.awssdk.services.appsync.model.AppSyncException;
import software.amazon.awssdk.services.appsync.model.DeleteApiKeyRequest;

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class DeleteApiKey {

    public static void main(String[] args) {

        final String usage = "\n" +
                "Usage: " +
                "   <apiId> <keyId> \n\n" +
                "Where:\n" +
                "   apiId - the id of the API (You can get this value from the AWS Management Console). \n\n" +
                "   keyId - The Id of the key to delete." ;

        if (args.length != 2) {
            System.out.println(usage);
            System.exit(1);
        }

        String apiId = args[0];
        String keyId = args[1];
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_EAST_1;
        AppSyncClient appSyncClient = AppSyncClient.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
        deleteKey(appSyncClient, keyId, apiId) ;
    }

    public static void deleteKey(AppSyncClient appSyncClient, String keyId, String apiId) {

        try {
            DeleteApiKeyRequest apiKeyRequest = DeleteApiKeyRequest.builder()
                    .apiId(apiId)
                    .id(keyId)
                    .build();

            appSyncClient.deleteApiKey(apiKeyRequest);
            System.out.println("The API key was deleted.");

        } catch (AppSyncException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
