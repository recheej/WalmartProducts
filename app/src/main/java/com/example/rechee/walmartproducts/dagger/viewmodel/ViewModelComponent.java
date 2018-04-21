package com.example.rechee.walmartproducts.dagger.viewmodel;

import com.example.rechee.sharkfeed.MainScreen.MainViewModel;
import com.example.rechee.sharkfeed.dagger.activity.ActivityComponent;
import com.example.rechee.sharkfeed.dagger.activity.ViewModelModule;
import com.example.rechee.walmartproducts.dagger.repository.NetModule;

import dagger.Subcomponent;

/**
 * Created by reche on 1/1/2018.
 */

@ViewModelScope
@Subcomponent(modules={RepositoryModule.class, NetModule.class})
public interface ViewModelComponent {
    void inject(MainViewModel mainViewModel);

    ActivityComponent plus(ViewModelModule viewModelModule);
}
