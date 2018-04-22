package com.example.rechee.walmartproducts;

import android.arch.lifecycle.LiveData;

import com.example.rechee.walmartproducts.dagger.viewmodel.RepositoryScope;
import com.example.rechee.walmartproducts.models.Product;

import java.util.List;

@RepositoryScope
public interface ProductRepository {
    LiveData<List<Product>> getProducts(int page, int pageSize);
}
