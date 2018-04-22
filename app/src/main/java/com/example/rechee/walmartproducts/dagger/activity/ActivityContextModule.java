package com.example.rechee.walmartproducts.dagger.activity;

import android.content.Context;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rechee on 1/2/2018.
 */

@Module
public class ActivityContextModule {

    private final Context context;

    public ActivityContextModule(Context context){
        this.context = context;
    }

    @Provides
    @Named("activityContext")
    @ActivityScope
    Context context() {
        return this.context;
    }

    @Provides
    @ActivityScope
    Picasso picasso() {
        return new Picasso.Builder(context).build();
    }
}