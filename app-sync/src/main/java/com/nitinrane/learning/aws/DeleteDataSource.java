
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.nitinrane.learning.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.appsync.AppSyncClient;
import software.amazon.awssdk.services.appsync.model.AppSyncException;
import software.amazon.awssdk.services.appsync.model.DeleteDataSourceRequest;

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class DeleteDataSource {

    public static void main(String[] args) {

        final String usage = "\n" +
                "Usage: " +
                "   <apiId> <dsName> \n\n" +
                "Where:\n" +
                "   apiId - the id of the API (You can get this value from the AWS Management Console). \n\n" +
                "   dsName - The name of the data source to delete." ;

        if (args.length != 2) {
            System.out.println(usage);
            System.exit(1);
        }

        String apiId = args[0];
        String dsName = args[1];
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_EAST_1;
        AppSyncClient appSyncClient = AppSyncClient.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
        deleteDS(appSyncClient, apiId, dsName) ;
    }

    public static void deleteDS( AppSyncClient appSyncClient, String apiId, String dsName) {

        try {

            DeleteDataSourceRequest request = DeleteDataSourceRequest.builder()
                    .apiId(apiId)
                    .name(dsName)
                    .build();

            appSyncClient.deleteDataSource(request);
            System.out.println("The data source was deleted.");

        } catch (AppSyncException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
