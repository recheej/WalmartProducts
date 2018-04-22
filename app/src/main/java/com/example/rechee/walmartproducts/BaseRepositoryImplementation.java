package com.example.rechee.walmartproducts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.rechee.walmartproducts.mainScreen.ErrorMessage;

public class BaseRepositoryImplementation implements BaseRepository {
    protected MutableLiveData<ErrorMessage> error;

    BaseRepositoryImplementation() {
        error = new MutableLiveData<>();
    }

    public MutableLiveData<ErrorMessage> getError() {
        return error;
    }
}
