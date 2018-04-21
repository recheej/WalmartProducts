package com.example.rechee.walmartproducts;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.rechee.walmartproducts.dagger.activity.ActivityScope;
import com.example.rechee.walmartproducts.dagger.viewmodel.ViewModelScope;
import com.example.rechee.walmartproducts.mainScreen.ProductListViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProductRepository productRepository;

    @Inject
    public ViewModelFactory(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ProductListViewModel.class)){
            return (T) new ProductListViewModel(this.productRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
