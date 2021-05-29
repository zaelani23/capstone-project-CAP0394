package com.cap0394.sahabattani.ui.findtengkulak;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FindTengkulakViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FindTengkulakViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is find tengkulak fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}