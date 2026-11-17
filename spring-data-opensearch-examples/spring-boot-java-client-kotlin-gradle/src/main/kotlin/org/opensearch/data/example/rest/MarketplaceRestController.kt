/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.data.example.rest

import java.math.BigDecimal
import org.opensearch.data.example.model.Product
import org.opensearch.data.example.repository.MarketplaceRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/marketplace")
class MarketplaceRestController(private val repository: MarketplaceRepository) {
    @GetMapping(value = ["/search"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun search(
        @RequestParam(value = "name", required = false, defaultValue = "") name: String,
        @RequestParam(value = "price", required = false, defaultValue = "0.0") price: BigDecimal,
    ): List<Product> = repository.findByNameLikeAndPriceGreaterThan(name, price)
}
