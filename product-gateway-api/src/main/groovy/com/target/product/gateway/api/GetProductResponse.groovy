package com.target.product.gateway.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.target.product.gateway.api.domain.Price
import groovy.transform.CompileStatic

@CompileStatic
@JsonIgnoreProperties(ignoreUnknown = true)
class GetProductResponse {
    Long id
    String name
    Price current_price
}
