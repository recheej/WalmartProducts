package com.example.rechee.walmartproducts;

import android.arch.lifecycle.LiveData;

import com.example.rechee.walmartproducts.dagger.viewmodel.ViewModelScope;
import com.example.rechee.walmartproducts.models.Product;

import java.util.List;

@ViewModelScope
public interface ProductRepository extends BaseRepository {
    LiveData<List<Product>> getProducts(int page, int pageSize);
}
