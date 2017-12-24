package com.ade.step2.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ade.step2.model.api.ApiResponse;

/**
 * Created by aderifaldi on 2017-12-24.
 */

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface ApiResponseCallback {
        void loadResponse(boolean success, ApiResponse apiResponse);
    }

}
