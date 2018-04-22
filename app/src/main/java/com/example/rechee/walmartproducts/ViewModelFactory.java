package com.example.rechee.walmartproducts;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.rechee.walmartproducts.mainScreen.ProductListViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProductListViewModel productListViewModel;

    @Inject
    public ViewModelFactory(ProductListViewModel productListViewModel){
        this.productListViewModel = productListViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ProductListViewModel.class)){
            return (T) productListViewModel;
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
