package com.example.rechee.walmartproducts.dagger.viewmodel;

import com.example.rechee.walmartproducts.ProductNetworkRepository;
import com.example.rechee.walmartproducts.ProductRepository;
import com.example.rechee.walmartproducts.dagger.application.ApplicationComponent;
import com.example.rechee.walmartproducts.dagger.network.ApiComponent;
import com.example.rechee.walmartproducts.dagger.network.NetModule;
import com.example.rechee.walmartproducts.mainScreen.ProductListViewModel;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by reche on 1/1/2018.
 */

@RepositoryScope
@Component(modules={RepositoryModule.class}, dependencies = {ApiComponent.class})
public interface RepositoryComponent {
    ProductRepository productRepository();
}
