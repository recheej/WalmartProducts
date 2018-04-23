package com.example.rechee.walmartproducts;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.rechee.walmartproducts.mainScreen.ErrorMessage;

public abstract class BaseActivity extends AppCompatActivity {
    private BaseViewModel viewModel;

    public void setViewModel(BaseViewModel viewModel){
        this.viewModel = viewModel;

        viewModel.getError().observe(this, new Observer<ErrorMessage>() {
            @Override
            public void onChanged(ErrorMessage errorMessage) {
                onError(errorMessage);
            }
        });
    }

    public void onError(ErrorMessage errorMessage) {
        int errorToShow;
        switch (errorMessage){
            case IMAGE_FAIL:
                errorToShow = R.string.error_image_load;
                break;
            case GENERAL:
                errorToShow = R.string.error_general_products;
                break;
            default:
                errorToShow = R.string.error_general;
        }

        Toast.makeText(this, errorToShow, Toast.LENGTH_LONG).show();
    }
}
