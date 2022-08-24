
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.nitinrane.learning.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.appsync.AppSyncClient;
import software.amazon.awssdk.services.appsync.model.GetDataSourceRequest;
import software.amazon.awssdk.services.appsync.model.GetDataSourceResponse;
import software.amazon.awssdk.services.appsync.model.AppSyncException;

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class GetDataSource {

    public static void main(String[] args) {

        final String usage = "\n" +
                "Usage: " +
                "   <apiId> <name>\n\n" +
                "Where:\n" +
                "   apiId - The id of the API (You can get this value from the AWS Management Console). \n\n" +
                "   name - The name of the data source. \n\n";

        if (args.length != 2) {
             System.out.println(usage);
             System.exit(1);
         }

        String apiId = args[0];
        String name = args[1];
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_EAST_1;
        AppSyncClient appSyncClient = AppSyncClient.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();

        getDS(appSyncClient, apiId, name);
    }

    public static void getDS(AppSyncClient appSyncClient, String apiId, String name ) {

    try {
        GetDataSourceRequest request = GetDataSourceRequest.builder()
                .apiId(apiId)
                .name(name)
                .build();

        GetDataSourceResponse response = appSyncClient.getDataSource(request);
        System.out.println("The DataSource ARN is "+response.dataSource().dataSourceArn());

    } catch (AppSyncException e) {
        System.out.println(e.getMessage());
        System.exit(1);
    }
  }
}
