
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.nitinrane.learning.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.appsync.AppSyncClient;
import software.amazon.awssdk.services.appsync.model.AppSyncException;
import software.amazon.awssdk.services.appsync.model.GraphqlApi;
import software.amazon.awssdk.services.appsync.model.ListGraphqlApisResponse;
import java.util.List;

/**
 * Before running this Java V2 code example, set up your development environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class ListGraphqlApis {

public static void main(String[] args) {

    ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
    Region region = Region.US_EAST_1;
    AppSyncClient appSyncClient = AppSyncClient.builder()
            .region(region)
            .credentialsProvider(credentialsProvider)
            .build();

    getApis(appSyncClient);
}

public static void getApis(AppSyncClient appSyncClient) {

    try {
        ListGraphqlApisResponse apisResponse = appSyncClient.listGraphqlApis();
        List<GraphqlApi> graphList = apisResponse.graphqlApis();
        for (GraphqlApi graph: graphList) {
            System.out.println("The name of the graph api is "+graph.name());
            System.out.println("The API Id is "+graph.apiId());
            System.out.println("The API URI is "+graph.uris());
        }

    } catch (AppSyncException e) {
        System.out.println(e.getMessage());
        System.exit(1);
    }
  }
}
