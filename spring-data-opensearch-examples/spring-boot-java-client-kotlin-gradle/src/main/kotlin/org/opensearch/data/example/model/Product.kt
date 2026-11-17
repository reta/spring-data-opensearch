/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.data.example.model

import java.math.BigDecimal
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "marketplace")
data class Product(
    @Id
    var id: String? = null,

    @Field(type = FieldType.Text, name = "name")
    var name: String? = null,

    @Field(type = FieldType.Double, name = "price")
    var price: BigDecimal? = null,

    @Field(type = FieldType.Integer, name = "quantity")
    var quantity: Int? = null,

    @Field(type = FieldType.Text, name = "description")
    var description: String? = null,

    @Field(type = FieldType.Keyword, name = "vendor")
    var vendor: String? = null,
)
