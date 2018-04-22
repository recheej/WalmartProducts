package com.example.rechee.walmartproducts.dagger.viewmodel;


import com.example.rechee.walmartproducts.MemoryProductRepository;
import com.example.rechee.walmartproducts.ProductRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by reche on 1/1/2018.
 */

@Module
public class RepositoryModule {

    @Provides
    @ViewModelScope
    public ProductRepository productRepository(MemoryProductRepository productNetworkRepository){
        return productNetworkRepository;
    }

    @Provides
    @ViewModelScope
    public MemoryProductRepository memoryProductRepository(){
        return new MemoryProductRepository();
    }
}