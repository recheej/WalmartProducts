package com.example.rechee.walmartproducts.mainScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rechee.walmartproducts.R;
import com.example.rechee.walmartproducts.ViewModelFactory;
import com.example.rechee.walmartproducts.WalmartProductsApplication;
import com.example.rechee.walmartproducts.dagger.application.DaggerApplicationComponent;
import com.example.rechee.walmartproducts.dagger.network.ApiComponent;
import com.example.rechee.walmartproducts.dagger.network.DaggerApiComponent;
import com.example.rechee.walmartproducts.dagger.network.NetModule;
import com.example.rechee.walmartproducts.dagger.viewmodel.DaggerRepositoryComponent;
import com.example.rechee.walmartproducts.dagger.viewmodel.RepositoryModule;
import com.example.rechee.walmartproducts.models.Product;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    private ProductListViewModel viewModel;
    private RecyclerView productRecyclerView;
    private LinearLayoutManager layoutManager;
    private ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiComponent apiComponent = DaggerApiComponent.builder()
                .netModule(new NetModule())
                .applicationComponent(WalmartProductsApplication.getAppComponent(this))
                .build();

        DaggerRepositoryComponent.builder()
                .repositoryModule(new RepositoryModule())
                .apiComponent(apiComponent)
                .build().inject(this);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        productRecyclerView = findViewById(R.id.recyclerView_products);
        productRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        productRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(productRecyclerView.getContext(),
                layoutManager.getOrientation());
        productRecyclerView.addItemDecoration(dividerItemDecoration);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductListViewModel.class);
        viewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                progressBar.setVisibility(View.INVISIBLE);

                if(products != null){
                    productListAdapter = new ProductListAdapter(products);
                    productRecyclerView.setAdapter(productListAdapter);
                }
            }
        });
    }
}
