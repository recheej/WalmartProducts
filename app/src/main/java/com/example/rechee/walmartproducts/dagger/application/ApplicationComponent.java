package com.example.rechee.walmartproducts.dagger.application;

import com.example.rechee.walmartproducts.dagger.activity.ActivityComponent;
import com.example.rechee.walmartproducts.dagger.activity.ViewModelModule;
import com.example.rechee.walmartproducts.dagger.repository.NetModule;
import com.example.rechee.walmartproducts.dagger.repository.RepositoryComponent;
import com.example.rechee.walmartproducts.dagger.viewmodel.RepositoryModule;
import com.example.rechee.walmartproducts.dagger.viewmodel.ViewModelComponent;

import dagger.Component;

/**
 * Created by reche on 1/1/2018.
 */

@ApplicationScope
@Component(modules={ApplicationContextModule.class})
public interface ApplicationComponent {
    ViewModelComponent plus(RepositoryModule repositoryModule);
    RepositoryComponent plus(NetModule netModule);
    ActivityComponent plus(ViewModelModule viewModelModule);
}
