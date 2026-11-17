/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.data.example.repository

import java.time.Duration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.opensearch.spring.boot.autoconfigure.test.DataOpenSearchTest
import org.opensearch.testcontainers.OpenSearchContainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers(disabledWithoutDocker = true)
@DataOpenSearchTest
@EnableElasticsearchRepositories(basePackageClasses = [MarketplaceRepository::class])
@Tag("integration-test")
class MarketplaceRepositoryIntegrationTests {
    companion object {
        @Container
        @ServiceConnection
        @JvmField
        val opensearch: OpenSearchContainer<*> = OpenSearchContainer<Nothing>("opensearchproject/opensearch:3.0.0").apply {
            withStartupAttempts(5)
            withStartupTimeout(Duration.ofMinutes(2))
        }
    }

    @Test
    fun testMarketplaceRepository(@Autowired repository: MarketplaceRepository) {
        assertThat(repository.findAll()).isEmpty()
    }
}
