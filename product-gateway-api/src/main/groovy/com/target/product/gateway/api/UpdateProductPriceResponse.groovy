package com.target.product.gateway.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.target.product.gateway.api.domain.Price
import groovy.transform.CompileStatic
import org.joda.time.LocalDateTime


@CompileStatic
@JsonIgnoreProperties(ignoreUnknown = true)
class UpdateProductPriceResponse {
    Long id
    String name
    Price current_price
    LocalDateTime update_time
}
