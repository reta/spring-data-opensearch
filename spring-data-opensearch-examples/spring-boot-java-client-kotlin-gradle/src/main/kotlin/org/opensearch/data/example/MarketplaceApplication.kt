/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.data.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.data.elasticsearch.autoconfigure.DataElasticsearchAutoConfiguration
import org.springframework.boot.elasticsearch.autoconfigure.ElasticsearchClientAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        DataElasticsearchAutoConfiguration::class,
        ElasticsearchClientAutoConfiguration::class,
    ],
)
class MarketplaceApplication

fun main(args: Array<String>) {
    runApplication<MarketplaceConfiguration>(*args)
}
