package com.example.rechee.walmartproducts.dagger.application;


import com.example.rechee.sharkfeed.dagger.viewmodel.RepositoryModule;
import com.example.rechee.sharkfeed.dagger.viewmodel.ViewModelComponent;
import com.example.rechee.walmartproducts.dagger.repository.NetModule;
import com.example.rechee.walmartproducts.dagger.repository.RepositoryComponent;

import dagger.Component;

/**
 * Created by reche on 1/1/2018.
 */

@ApplicationScope
@Component(modules={ApplicationContextModule.class})
public interface ApplicationComponent {
    ViewModelComponent plus(RepositoryModule repositoryModule);
    RepositoryComponent plus(NetModule netModule);
}
