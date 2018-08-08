package com.target.product.gateway.api

import com.target.product.gateway.fixtures.GetProductResponseFixtureBuilder
import com.target.product.gateway.fixtures.common.JsonHelper
import spock.lang.Specification

class GetProductResponseSpec extends Specification {
    void 'Default fixture get product response converts to expected JSON'() {
        given:
        GetProductResponse response = new GetProductResponseFixtureBuilder().build()
        String expectedJson = JsonHelper.jsonFromFixture('get_product_response')

        when:
        String marshalled = JsonHelper.toJson(response)

        then:
        marshalled == expectedJson
    }
}
