package com.example.rechee.walmartproducts.mainScreen;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;


import com.example.rechee.walmartproducts.ProductRepository;
import com.example.rechee.walmartproducts.dagger.viewmodel.RepositoryScope;
import com.example.rechee.walmartproducts.models.Product;

import java.util.List;

import javax.inject.Inject;

@RepositoryScope
public class ProductListViewModel extends ViewModel {

    private static final int PAGE_SIZE = 10;

    private final ProductRepository repository;
    private MutableLiveData<List<Product>> products;
    private int currentPage = 0;

    @Inject
    public ProductListViewModel(ProductRepository repository) {
        this.repository = repository;
        products = new MutableLiveData<>();
    }

    public void reset() {
        this.currentPage = 0;
    }

    public LiveData<List<Product>> getProducts() {
        this.currentPage++;
        return repository.getProducts(this.currentPage, PAGE_SIZE);
    }
}
