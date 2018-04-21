package com.example.rechee.walmartproducts;

import com.example.rechee.walmartproducts.models.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {

    @GET("walmartproducts/{apiKey}/{pageNumber}/{pageSize}")
    Call<ProductsResponse> getProducts(@Path("apiKey") String apiKey, @Path("pageNumber") int pageNumber,
                                       @Path("pageSize") int pageSize);
}
