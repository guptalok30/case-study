package com.target.product.gateway.fixtures

import com.target.product.gateway.api.GetProductRequest

class GetProductRequestFixtureBuilder {
    Long id = 1234L

    GetProductRequestFixtureBuilder withId(Long id) {
        this.id = id
        return this
    }

    GetProductRequest build() {
       return new GetProductRequest(id: id)
    }
}
