package com.target.product.gateway.impl.service

import com.target.product.gateway.api.GetProductRequest
import com.target.product.gateway.api.GetProductResponse
import com.target.product.gateway.api.UpdateProductPriceRequest
import com.target.product.gateway.api.UpdateProductPriceResponse
import com.target.product.gateway.api.domain.Price
import com.target.product.gateway.impl.dao.ProductPriceDao
import com.target.product.gateway.impl.external.productapi.ProductApi
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import retrofit2.Call

import javax.inject.Inject

@CompileStatic
@Service
class ProductService {
    @Value('${productApi.excludes}')
    String excludes

    HashMap<String, String> queryParams = [:]
    private ProductPriceDao productPriceDao
    private EntityValidator entityValidator
    private ProductApi productApi

    @Inject
    ProductService(ProductPriceDao productPriceDao, EntityValidator entityValidator, ProductApi productApi) {
        this.productPriceDao = productPriceDao
        this.entityValidator = entityValidator
        this.productApi = productApi
    }

    GetProductResponse getProduct(GetProductRequest request) {
        entityValidator.validate(request)
        return new GetProductResponse(
                name: getProductDescription(request.id),
                id: request.id,
                current_price: getProductPrice(request.id))
    }

    private String getProductDescription(Long id) {
        HashMap<String, String> queryParams = [:]
        queryParams.put('excludes', excludes)

        //Using map for simplicity ..typically we have a strongly typed domain api provided by the client lib
        Call<Map<String, Object>> response = productApi.getProductDetails(id, queryParams)
        Map<String, Object> responseData = response.execute().body()
        Map<String, Object> item = (Map) ((Map) responseData.get('product')).get('item')

        return ((Map)item.get('product_description')).get('title') ?: 'Product description not found'
    }

    private Price getProductPrice(Long id) {
        productPriceDao.getProductPrice(id)
    }

    UpdateProductPriceResponse updateProductPrice(Long id, UpdateProductPriceRequest request) {
        entityValidator.validate(request)
        if (id != request.id) {
            throw new IllegalArgumentException('Product id is not valid')
        }
        //verify if the product id is valid. Not sure if the provided endpoint is meant for that.
        productPriceDao.saveProductPrice(request)
    }

}
