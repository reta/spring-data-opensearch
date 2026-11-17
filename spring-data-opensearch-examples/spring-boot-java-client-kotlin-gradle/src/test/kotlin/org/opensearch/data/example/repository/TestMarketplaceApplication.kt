/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.data.example.repository

import org.opensearch.data.example.MarketplaceApplication
import org.opensearch.testcontainers.OpenSearchContainer
import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean

@TestConfiguration(proxyBeanMethods = false)
class ContainerConfig {
    @Bean
    @ServiceConnection
    fun opensearchContainer(): OpenSearchContainer<*> =
        OpenSearchContainer<Nothing>("opensearchproject/opensearch:3.0.0")
}

fun main(args: Array<String>) {
    fromApplication<MarketplaceApplication>()
        .with(ContainerConfig::class)
        .run(*args)
}
