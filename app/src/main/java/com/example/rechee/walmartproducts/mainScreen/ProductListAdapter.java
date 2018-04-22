package com.example.rechee.walmartproducts.mainScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rechee.walmartproducts.BaseActivity;
import com.example.rechee.walmartproducts.R;
import com.example.rechee.walmartproducts.models.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {



    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        TextView textViewProductName;
        TextView textViewProductPrice;
        TextView textViewOutOfStock;

        ViewHolder(View itemView) {
            super(itemView);

            productImageView = itemView.findViewById(R.id.imageView_product);
            textViewProductName = itemView.findViewById(R.id.textView_productName);
            textViewProductPrice = itemView.findViewById(R.id.textView_productPrice);
            textViewOutOfStock = itemView.findViewById(R.id.textView_outOfStock);
        }
    }

    private List<Product> products;
    private final Picasso picasso;
    private final BaseActivity activity;

    public ProductListAdapter(List<Product> startingProducts, Picasso picasso, BaseActivity activity){
        this.products = startingProducts;
        this.picasso = picasso;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);

        picasso.load(product.getProductImage()).into(holder.productImageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                activity.onError(ErrorMessage.IMAGE_FAIL);
            }
        });

        holder.textViewProductName.setText(product.getProductName());
        holder.textViewProductPrice.setText(product.getPrice()); //TODO: formatting?

        if(!product.getInStock()){
            holder.textViewOutOfStock.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
