package com.example.rechee.walmartproducts.dagger.application;

import android.content.Context;

import com.example.rechee.walmartproducts.mainScreen.MainActivity;

import javax.inject.Named;

import dagger.Component;

/**
 * Created by reche on 1/1/2018.
 */

@ApplicationScope
@Component(modules={ApplicationContextModule.class})
public interface ApplicationComponent {
    @Named("applicationContext") Context appContext();
    void inject(MainActivity activity);
}
