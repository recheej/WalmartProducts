package com.example.rechee.walmartproducts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.rechee.walmartproducts.mainScreen.ErrorMessage;

public class BaseViewModel extends ViewModel {

    private final BaseRepository repository;
    public MutableLiveData<ErrorMessage> error;

    public BaseViewModel(BaseRepository baseRepository) {
        error = new MutableLiveData<>();
        this.repository = baseRepository;
        this.repository.getError().observeForever(new Observer<ErrorMessage>() {
            @Override
            public void onChanged(@Nullable ErrorMessage errorMessage) {
                setError(errorMessage);
            }
        });
    }

    public LiveData<ErrorMessage> getError() {
        return error;
    }

    public void setError(ErrorMessage errorMessage){
        error.setValue(errorMessage);
    }
}
