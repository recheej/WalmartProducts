package com.example.rechee.walmartproducts.dagger.activity;

import android.content.Context;

import com.example.rechee.walmartproducts.dagger.application.ApplicationComponent;
import com.example.rechee.walmartproducts.dagger.viewmodel.ViewModelComponent;
import com.example.rechee.walmartproducts.dagger.viewmodel.ViewModelScope;
import com.example.rechee.walmartproducts.mainScreen.MainActivity;
import com.example.rechee.walmartproducts.productDetailScreen.ProductDetailActivity;

import javax.inject.Named;

import dagger.Component;

/**
 * Created by reche on 1/1/2018.
 */

@ActivityScope
@Component(modules={ActivityContextModule.class}, dependencies = {ViewModelComponent.class})
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(ProductDetailActivity productDetailActivity);

    @Named("activityContext")
    Context getActivityContext();
}
