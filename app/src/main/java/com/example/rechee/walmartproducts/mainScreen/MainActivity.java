package com.example.rechee.walmartproducts.mainScreen;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rechee.walmartproducts.R;
import com.example.rechee.walmartproducts.ViewModelFactory;
import com.example.rechee.walmartproducts.WalmartProductsApplication;
import com.example.rechee.walmartproducts.dagger.activity.ActivityComponent;
import com.example.rechee.walmartproducts.dagger.activity.ViewModelModule;
import com.example.rechee.walmartproducts.dagger.repository.NetModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private ProductListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       WalmartProductsApplication.getAppComponent(this)
                .plus(new ViewModelModule()).inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductListViewModel.class);
    }
}
