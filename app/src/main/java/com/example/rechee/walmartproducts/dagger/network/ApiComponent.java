package com.example.rechee.walmartproducts.dagger.network;

import com.example.rechee.walmartproducts.ProductService;
import com.example.rechee.walmartproducts.dagger.application.ApplicationComponent;
import com.example.rechee.walmartproducts.dagger.network.NetModule;
import com.example.rechee.walmartproducts.dagger.viewmodel.RepositoryModule;
import com.example.rechee.walmartproducts.mainScreen.ProductListViewModel;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;
import retrofit2.Retrofit;

/**
 * Created by reche on 1/1/2018.
 */

@ApiScope
@Component(modules={NetModule.class}, dependencies = {ApplicationComponent.class})
public interface ApiComponent {
    ProductService productService();
    String apiKey();
    Retrofit retrofit();
}
