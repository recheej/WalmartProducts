package com.example.rechee.walmartproducts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.rechee.walmartproducts.ProductRepository;
import com.example.rechee.walmartproducts.dagger.viewmodel.RepositoryScope;
import com.example.rechee.walmartproducts.mainScreen.ErrorMessage;
import com.example.rechee.walmartproducts.models.Product;

import java.util.ArrayList;
import java.util.List;

@RepositoryScope
public class MemoryProductRepository implements ProductRepository {
    @Override
    public LiveData<List<Product>> getProducts(int page, int pageSize) {
        MutableLiveData<List<Product>> productData = new MutableLiveData<>();
        if(page == 1){
            Product productOne = new Product();
            productOne.setProductId("test 1");
            productOne.setProductName("playstation 4");
            productOne.setShortDescription("test description one");
            productOne.setLongDescription("test long description test long description");
            productOne.setPrice("499.99");
            productOne.setProductImage("https://cdn1.techadvisor.co.uk/cmsdata/features/3628024/ps4_thumb800.png");
            productOne.setReviewRating(5.0);
            productOne.setReviewCount(500);
            productOne.setInStock(true);

            List<Product> data = new ArrayList<>();
            data.add(productOne);
            productData.setValue(data);
        }
        else if(page == 2){
            Product productOne = new Product();
            productOne.setProductId("test 2");
            productOne.setProductName("xbox one controller");
            productOne.setShortDescription("test description two");
            productOne.setLongDescription("test long description test long description");
            productOne.setPrice("24.99");
            productOne.setProductImage("https://icdn7.digitaltrends.com/image/xbox-one-x-review-controller-on-system-800x533-c.jpg");
            productOne.setReviewRating(5.0);
            productOne.setReviewCount(500);
            productOne.setInStock(false);

            List<Product> data = new ArrayList<>();
            data.add(productOne);
            productData.setValue(data);
        }
        else{
            List<Product> data = new ArrayList<>();
            productData.setValue(data);
        }

        return productData;
    }

    @Override
    public LiveData<ErrorMessage> getError() {
        return new MutableLiveData<>();
    }
}
