package com.target.product.gateway.client

import com.target.product.gateway.api.GetProductResponse
import com.target.product.gateway.api.UpdateProductPriceRequest
import com.target.product.gateway.api.UpdateProductPriceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductGatewayApi {

    @Headers(['Accept: application/json'])
    @GET('/products/{id}')
    Call<GetProductResponse> getProduct(@Path('id') Long id)

    @Headers(['Accept: application/json'])
    @PUT('/products/{id}')
    Call<UpdateProductPriceResponse> updateProductPrice(@Path('id') Long id, @Body UpdateProductPriceRequest request)
}
