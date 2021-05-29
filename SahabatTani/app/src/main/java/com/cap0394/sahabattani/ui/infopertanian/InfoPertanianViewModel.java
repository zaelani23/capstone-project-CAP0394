package com.cap0394.sahabattani.ui.infopertanian;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InfoPertanianViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public InfoPertanianViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is info pertanian fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}