package com.example.rechee.walmartproducts.mainScreen;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;


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
        return Transformations.map(repository.getProducts(this.currentPage + 1, PAGE_SIZE), new Function<List<Product>, List<Product>>() {
            @Override
            public List<Product> apply(List<Product> input) {
                if(input != null && input.size() > 0){
                    currentPage++;
                }

                return input;
            }
        });
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
