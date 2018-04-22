package com.example.rechee.walmartproducts.dagger.viewmodel;

import com.example.rechee.walmartproducts.ProductRepository;
import com.example.rechee.walmartproducts.dagger.network.ApiComponent;
import com.example.rechee.walmartproducts.mainScreen.MainActivity;

import dagger.Component;

/**
 * Created by reche on 1/1/2018.
 */

@ViewModelScope
@Component(modules={RepositoryModule.class}, dependencies = {ApiComponent.class})
public interface ViewModelComponent {
    ProductRepository productRepository();
}
