package com.example.iwb303.tab.MySubjects;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iwb303.R;

public class MySubjectsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MySubjectsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}