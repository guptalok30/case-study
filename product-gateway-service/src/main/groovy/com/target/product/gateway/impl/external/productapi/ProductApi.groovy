package com.target.product.gateway.impl.external.productapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ProductApi {

   //Note: Response type would typically be provided by the dependent library.
   //Using map for simplicity here

    @Headers(['Accept: application/json'])
    @GET('/v2/pdp/tcin/{id}')
    Call<Map<String, Object>> getProductDetails(@Path('id') Long id, @QueryMap Map<String, String> params)

}
