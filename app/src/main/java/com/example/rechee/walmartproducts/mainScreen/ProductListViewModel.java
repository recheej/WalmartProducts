package com.example.rechee.walmartproducts.mainScreen;

import android.arch.lifecycle.LiveData;


import com.example.rechee.walmartproducts.BaseViewModel;
import com.example.rechee.walmartproducts.ProductRepository;
import com.example.rechee.walmartproducts.dagger.activity.ActivityScope;
import com.example.rechee.walmartproducts.dagger.application.ApplicationScope;
import com.example.rechee.walmartproducts.dagger.viewmodel.ViewModelScope;
import com.example.rechee.walmartproducts.models.Product;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class ProductListViewModel extends BaseViewModel {

    private static final int PAGE_SIZE = 10;

    private final ProductRepository repository;
    private int currentPage = 0;

    @Inject
    public ProductListViewModel(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void reset() {
        this.currentPage = 0;
    }

    public LiveData<List<Product>> getProducts() {
        if(this.currentPage == 0){
            currentPage = 1;
        }
        return repository.getProducts(this.currentPage, PAGE_SIZE);
    }

    public LiveData<List<Product>> getNextProducts() {
        currentPage++;
        return repository.getProducts(this.currentPage, PAGE_SIZE);
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
