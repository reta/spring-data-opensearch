/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.data.example

import java.security.GeneralSecurityException
import org.apache.hc.client5.http.impl.async.HttpAsyncClientBuilder
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManagerBuilder
import org.apache.hc.client5.http.ssl.ClientTlsStrategyBuilder
import org.apache.hc.client5.http.ssl.TrustSelfSignedStrategy
import org.apache.hc.core5.ssl.SSLContextBuilder
import org.opensearch.client.RestClientBuilder
import org.opensearch.data.example.repository.MarketplaceRepository
import org.opensearch.spring.boot.autoconfigure.RestClientBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableElasticsearchRepositories(basePackageClasses = [MarketplaceRepository::class])
@ComponentScan(basePackageClasses = [MarketplaceConfiguration::class])
class MarketplaceConfiguration {
    /**
     * Allow to connect to the OpenSearch instance which uses self-signed certificates
     */
    @Bean
    fun customizer(): RestClientBuilderCustomizer = object : RestClientBuilderCustomizer {
        override fun customize(builder: HttpAsyncClientBuilder) {
            try {
                val tlsStrategy = ClientTlsStrategyBuilder.create()
                    .setSslContext(
                        SSLContextBuilder()
                            .loadTrustMaterial(null, TrustSelfSignedStrategy())
                            .build(),
                    )

                val connectionManager = PoolingAsyncClientConnectionManagerBuilder
                    .create()
                    .setTlsStrategy(tlsStrategy.build())
                    .build()

                builder.setConnectionManager(connectionManager)
            } catch (ex: GeneralSecurityException) {
                throw RuntimeException("Failed to initialize SSL Context instance", ex)
            }
        }

        override fun customize(builder: RestClientBuilder) {
            // No additional customizations needed
        }
    }
}
