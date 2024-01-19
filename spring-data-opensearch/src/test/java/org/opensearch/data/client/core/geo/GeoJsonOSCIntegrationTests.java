/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.data.client.core.geo;

import org.junit.jupiter.api.DisplayName;
import org.opensearch.data.client.junit.jupiter.OpenSearchTemplateConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.geo.GeoJsonIntegrationTests;
import org.springframework.data.elasticsearch.utils.IndexNameProvider;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {GeoJsonOSCIntegrationTests.Config.class})
@DisplayName("GeoJson integration test with OpenSearchClient")
public class GeoJsonOSCIntegrationTests extends GeoJsonIntegrationTests {
    @Configuration
    @Import({OpenSearchTemplateConfiguration.class})
    static class Config {
        @Bean
        IndexNameProvider indexNameProvider() {
            return new IndexNameProvider("geojson-integration-os");
        }
    }
}
