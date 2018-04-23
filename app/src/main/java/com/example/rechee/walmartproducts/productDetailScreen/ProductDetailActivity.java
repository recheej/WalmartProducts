package com.example.rechee.walmartproducts.productDetailScreen;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rechee.walmartproducts.BaseActivity;
import com.example.rechee.walmartproducts.R;
import com.example.rechee.walmartproducts.WalmartProductsApplication;
import com.example.rechee.walmartproducts.dagger.activity.ActivityContextModule;
import com.example.rechee.walmartproducts.dagger.activity.DaggerActivityComponent;
import com.example.rechee.walmartproducts.dagger.network.ApiComponent;
import com.example.rechee.walmartproducts.dagger.network.DaggerApiComponent;
import com.example.rechee.walmartproducts.dagger.network.NetModule;
import com.example.rechee.walmartproducts.dagger.viewmodel.DaggerViewModelComponent;
import com.example.rechee.walmartproducts.dagger.viewmodel.RepositoryModule;
import com.example.rechee.walmartproducts.dagger.viewmodel.ViewModelComponent;
import com.example.rechee.walmartproducts.mainScreen.ErrorMessage;
import com.example.rechee.walmartproducts.mainScreen.MainActivity;
import com.example.rechee.walmartproducts.mainScreen.ProductListAdapter;
import com.example.rechee.walmartproducts.models.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ProductDetailActivity extends BaseActivity {

    private Product product;

    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ApiComponent apiComponent = DaggerApiComponent.builder()
                .netModule(new NetModule())
                .applicationComponent(WalmartProductsApplication.getAppComponent(this))
                .build();

        ViewModelComponent viewModelComponent = DaggerViewModelComponent.builder()
                .repositoryModule(new RepositoryModule())
                .apiComponent(apiComponent)
                .build();

        DaggerActivityComponent.builder()
                .viewModelComponent(viewModelComponent)
                .activityContextModule(new ActivityContextModule(this))
                .build().inject(this);

        product = getIntent().getParcelableExtra(ProductListAdapter.PRODUCT_EXTRA);
        if(product == null){
            onError(ErrorMessage.GENERAL);
        }
        else{
            TextView textViewProductName = findViewById(R.id.textView_productName);
            ImageView imageView = findViewById(R.id.imageView_product);
            TextView textViewProductPrice = findViewById(R.id.textView_productPrice);
            TextView textViewOutOfStock = findViewById(R.id.textView_outOfStock);
            TextView textViewDescription = findViewById(R.id.textView_description);

            textViewProductName.setText(product.getProductName());
            textViewProductPrice.setText(product.getPrice());
            textViewDescription.setText(product.getLongDescription());

            if(!product.getInStock()){
                textViewOutOfStock.setVisibility(View.VISIBLE);
            }

            picasso.load(product.getProductImage()).into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    ProductDetailActivity.this.onError(ErrorMessage.GENERAL);
                }
            });
        }
    }

    @Override
    public void onError(ErrorMessage errorMessage) {
        super.onError(errorMessage);
    }
}
