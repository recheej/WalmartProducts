package com.example.rechee.walmartproducts.mainScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rechee.walmartproducts.BaseActivity;
import com.example.rechee.walmartproducts.R;
import com.example.rechee.walmartproducts.ViewModelFactory;
import com.example.rechee.walmartproducts.WalmartProductsApplication;
import com.example.rechee.walmartproducts.dagger.network.ApiComponent;
import com.example.rechee.walmartproducts.dagger.network.DaggerApiComponent;
import com.example.rechee.walmartproducts.dagger.network.NetModule;
import com.example.rechee.walmartproducts.dagger.viewmodel.DaggerRepositoryComponent;
import com.example.rechee.walmartproducts.dagger.viewmodel.RepositoryModule;
import com.example.rechee.walmartproducts.models.Product;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    private static final String CURRENT_PAGE_KEY = "current_page";
    @Inject
    ViewModelFactory viewModelFactory;

    private ProductListViewModel viewModel;
    private RecyclerView productRecyclerView;
    private LinearLayoutManager layoutManager;
    private ProductListAdapter productListAdapter;
    private List<Product> products;
    private int currentPage = 1;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.products = new ArrayList<>();

        if(savedInstanceState != null){
            currentPage = savedInstanceState.getInt(CURRENT_PAGE_KEY, 1);
        }

        ApiComponent apiComponent = DaggerApiComponent.builder()
                .netModule(new NetModule())
                .applicationComponent(WalmartProductsApplication.getAppComponent(this))
                .build();

        DaggerRepositoryComponent.builder()
                .repositoryModule(new RepositoryModule())
                .apiComponent(apiComponent)
                .build().inject(this);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        productRecyclerView = findViewById(R.id.recyclerView_products);
        productRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        productRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(productRecyclerView.getContext(),
                layoutManager.getOrientation());
        productRecyclerView.addItemDecoration(dividerItemDecoration);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductListViewModel.class);

        setViewModel(viewModel);

        currentPage = viewModel.getCurrentPage();

        final EndlessRecyclerViewScrollListener endlessListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                progressBar.setVisibility(View.VISIBLE);

                viewModel.getNextProducts().observe(MainActivity.this, new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable List<Product> newProducts) {
                        progressBar.setVisibility(View.INVISIBLE);

                        if (newProducts != null) {
                            updateProducts(newProducts);
                        }
                    }
                });
            }
        };
        endlessListener.setStartingPage(currentPage);
        productRecyclerView.addOnScrollListener(endlessListener);

        viewModel.getProducts().observe(this, new Observer<List<Product>>() {

            @Override
            public void onChanged(@Nullable List<Product> newProducts) {
                progressBar.setVisibility(View.INVISIBLE);

                if(newProducts != null){
                    MainActivity.this.products.addAll(newProducts);
                    productListAdapter = new ProductListAdapter(MainActivity.this.products);
                    productRecyclerView.setAdapter(productListAdapter);
                    endlessListener.setStartingPage(viewModel.getCurrentPage());
                }
            }
        });

        swipeRefreshLayout = findViewById(R.id.view_swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainActivity.this.products.clear();
                viewModel.reset();
                viewModel.getProducts().observe(MainActivity.this, new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable List<Product> newProducts) {
                        updateProducts(newProducts);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void updateProducts(@NonNull List<Product> newProducts) {
        MainActivity.this.products.addAll(newProducts);

        int currentSize = MainActivity.this.products.size();
        MainActivity.this.productListAdapter.notifyItemRangeInserted(currentSize, newProducts.size());
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(CURRENT_PAGE_KEY, currentPage);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onError(ErrorMessage errorMessage) {
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this, R.string.error_general_products, Toast.LENGTH_LONG).show();
    }
}
