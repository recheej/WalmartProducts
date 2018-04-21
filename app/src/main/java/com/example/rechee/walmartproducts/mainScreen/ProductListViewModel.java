package com.example.rechee.walmartproducts.mainScreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rechee.walmartproducts.ProductRepository;
import com.example.rechee.walmartproducts.models.Product;

import java.util.List;

public class ProductListViewModel extends ViewModel {

    private final ProductRepository repository;
    private MutableLiveData<List<Product>> products;
    private int lastPageRequested = 0;
    public ProductListViewModel(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProducts(int page) {
        this.lastPageRequested = page;
        return repository.getProducts(page, 20);
    }
}
