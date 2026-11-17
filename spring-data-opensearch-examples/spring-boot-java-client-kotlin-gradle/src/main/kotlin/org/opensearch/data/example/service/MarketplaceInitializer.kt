/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.data.example.service

import java.math.BigDecimal
import org.opensearch.data.example.model.Product
import org.opensearch.data.example.repository.MarketplaceRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service

@Service
class MarketplaceInitializer(private val repository: MarketplaceRepository) : InitializingBean {
    override fun afterPropertiesSet() {
        repository.save(
            Product(
                id = "1",
                name = "Utopia Bedding Bed Pillows",
                price = BigDecimal(39.99),
                quantity = 2,
                description = "These professionally finished pillows, with high thread counts, provide great comfort against your skin along with added durability " +
                    "that easily resists wear and tear to ensure a finished look for your bedroom.",
                vendor = "Utopia Bedding",
            ),
        )

        repository.save(
            Product(
                id = "2",
                name = "Echo Dot Smart speaker",
                price = BigDecimal(34.99),
                quantity = 10,
                description = "Our most popular smart speaker with a fabric design. It is our most compact smart speaker that fits perfectly into small spaces.",
                vendor = "Amazon",
            ),
        )
    }
}
