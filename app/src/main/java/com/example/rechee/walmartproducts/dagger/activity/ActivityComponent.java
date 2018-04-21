package com.example.rechee.walmartproducts.dagger.activity;

import com.example.rechee.walmartproducts.mainScreen.MainActivity;

import dagger.Subcomponent;

/**
 * Created by reche on 1/1/2018.
 */

@ActivityScope
@Subcomponent(modules={ViewModelModule.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
