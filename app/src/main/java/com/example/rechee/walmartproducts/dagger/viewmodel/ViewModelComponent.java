package com.example.rechee.walmartproducts.dagger.viewmodel;

import com.example.rechee.walmartproducts.dagger.repository.NetModule;

import dagger.Subcomponent;

/**
 * Created by reche on 1/1/2018.
 */

@ViewModelScope
@Subcomponent(modules={RepositoryModule.class, NetModule.class})
public interface ViewModelComponent {
}
