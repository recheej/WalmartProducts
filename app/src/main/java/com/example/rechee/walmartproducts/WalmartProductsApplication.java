package com.example.rechee.walmartproducts;

import android.app.Activity;
import android.app.Application;

import com.example.rechee.walmartproducts.dagger.application.ApplicationComponent;
import com.example.rechee.walmartproducts.dagger.application.ApplicationContextModule;
import com.example.rechee.walmartproducts.dagger.application.DaggerApplicationComponent;

public class WalmartProductsApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationContextModule(new ApplicationContextModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public static ApplicationComponent getAppComponent(Activity activity){
        WalmartProductsApplication application = (WalmartProductsApplication) activity.getApplication();

        return application.getComponent();
    }
}
