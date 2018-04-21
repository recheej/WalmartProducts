package com.example.rechee.walmartproducts;

import android.arch.lifecycle.LiveData;

import com.example.rechee.walmartproducts.models.Product;

import java.util.List;

public interface ProductRepository {
    LiveData<List<Product>> getProducts(int page, int pageSize);
}
