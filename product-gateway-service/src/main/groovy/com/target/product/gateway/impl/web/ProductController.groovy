package com.target.product.gateway.impl.web

import com.target.product.gateway.api.GetProductRequest
import com.target.product.gateway.api.GetProductResponse
import com.target.product.gateway.api.UpdateProductPriceRequest
import com.target.product.gateway.api.UpdateProductPriceResponse
import com.target.product.gateway.impl.service.ProductService
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.*

import javax.inject.Inject
import javax.validation.Valid

@CompileStatic
@RestController
@RequestMapping("/products")
class ProductController {

    private final ProductService productService

    @Inject
    ProductController(ProductService productService) {
        this.productService = productService
    }
    @RequestMapping(value = '/{id}', method = RequestMethod.GET, produces = ['application/json'])
    GetProductResponse getProduct(GetProductRequest request){
        productService.getProduct(request)
    }

    @RequestMapping(value = '/{id}', method = RequestMethod.PUT, produces = ['application/json'])
    UpdateProductPriceResponse updateProductPrice(@PathVariable Long id, @Valid @RequestBody UpdateProductPriceRequest request) {
        productService.updateProductPrice(id, request)
    }

}
