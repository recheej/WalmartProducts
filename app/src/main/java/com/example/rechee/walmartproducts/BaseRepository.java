package com.example.rechee.walmartproducts;

import android.arch.lifecycle.LiveData;

import com.example.rechee.walmartproducts.mainScreen.ErrorMessage;

interface BaseRepository {
    LiveData<ErrorMessage> getError();
}
