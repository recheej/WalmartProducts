package com.example.rechee.walmartproducts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.rechee.walmartproducts.models.Product;
import com.example.rechee.walmartproducts.models.ProductsResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductNetworkRepository implements ProductRepository {

    private final ProductService productService;
    private final String apiKey;

    @Inject
    public ProductNetworkRepository(ProductService productService, String apiKey) {
        this.productService = productService;
        this.apiKey = apiKey;
    }

    @Override
    public LiveData<List<Product>> getProducts(int page, int pageSize) {
        final MutableLiveData<List<Product>> productData = new MutableLiveData<>();

        this.productService.getProducts(apiKey, page, pageSize).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if(response.isSuccessful()){
                    final ProductsResponse body = response.body();
                    if(body != null){
                        final List<Product> products = body.getProducts();
                        productData.postValue(products);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });

        return productData;
    }
}
