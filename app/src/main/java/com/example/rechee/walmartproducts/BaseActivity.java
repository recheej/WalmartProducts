package com.example.rechee.walmartproducts;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

    public abstract void onError(ErrorMessage errorMessage);
}
