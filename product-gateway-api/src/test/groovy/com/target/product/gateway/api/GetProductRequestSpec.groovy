package com.target.product.gateway.api

import com.target.product.gateway.fixtures.GetProductRequestFixtureBuilder
import com.target.product.gateway.fixtures.common.JsonHelper
import spock.lang.Specification

class GetProductRequestSpec extends Specification {

    void 'Default fixture get product request converts to expected JSON'() {
        given:
        GetProductRequest response = new GetProductRequestFixtureBuilder().build()
        String expectedJson = JsonHelper.jsonFromFixture('get_product_request')

        when:
        String marshalled = JsonHelper.toJson(response)

        then:
        marshalled == expectedJson
    }
}
