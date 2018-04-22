package com.example.rechee.walmartproducts.dagger.network;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by reche on 1/1/2018.
 */

@Scope
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ApiScope
{

}
