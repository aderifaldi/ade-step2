package com.ade.step2.viewmodel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.ade.step2.data.api.GetProductList;
import com.ade.step2.data.cache.AppDatabase;
import com.ade.step2.helper.AppUtility;
import com.ade.step2.model.api.ApiResponse;
import com.ade.step2.view.activity.HomeActivity;

/**
 * Created by aderifaldi on 2017-12-24.
 */

public class ProductListVM extends ViewModel {

    private ProgressDialog pd;

    public void loadProductList(final Activity activity, AppDatabase db, int cacheType) {

        pd = AppUtility.showLoading(pd, activity, "Loading data...");

        new GetProductList(activity, db, cacheType) {
            @Override
            public void onFinishRequest(boolean success, ApiResponse response) {

                if (pd != null){
                    pd.dismiss();
                }

                ((HomeActivity) activity).loadResponse(success, response);
            }
        };
    }

}
