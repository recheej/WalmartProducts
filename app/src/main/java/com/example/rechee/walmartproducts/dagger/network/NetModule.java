package com.example.rechee.walmartproducts.dagger.network;

import android.content.Context;

import com.example.rechee.walmartproducts.ProductService;
import com.example.rechee.walmartproducts.R;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by reche on 1/1/2018.
 */

@Module
public class NetModule {
    private static final String BASE_URL = "https://walmartlabs-test.appspot.com/_ah/api/walmart/v1/";

    @Provides
    @ApiScope
    public ProductService productService(Retrofit retrofit) {
        return retrofit.create(ProductService.class);
    }

    @Provides
    @ApiScope
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @ApiScope
    public String apiKey(@Named("applicationContext") Context applicationContext){
        return applicationContext.getString(R.string.api_key);
    }
}