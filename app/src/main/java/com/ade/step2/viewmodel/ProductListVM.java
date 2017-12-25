package com.ade.step2.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.ade.step2.data.api.GetProductList;
import com.ade.step2.data.cache.AppDatabase;
import com.ade.step2.model.api.ApiResponse;
import com.ade.step2.view.HomeActivity;

/**
 * Created by aderifaldi on 2017-12-24.
 */

public class ProductListVM extends ViewModel {

    public void loadProductList(final Context context, AppDatabase db, int cacheType) {
        new GetProductList(context, db, cacheType) {
            @Override
            public void onFinishRequest(boolean success, ApiResponse response) {
                ((HomeActivity) context).loadResponse(success, response);
            }
        };
    }

}
