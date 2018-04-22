package com.example.rechee.walmartproducts.dagger.viewmodel;


import android.content.Context;

import com.example.rechee.walmartproducts.ProductNetworkRepository;
import com.example.rechee.walmartproducts.ProductRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by reche on 1/1/2018.
 */

@Module
public class RepositoryModule {

    @Provides
    @RepositoryScope
    public ProductRepository productRepository(ProductNetworkRepository productNetworkRepository){
        return productNetworkRepository;
    }
}