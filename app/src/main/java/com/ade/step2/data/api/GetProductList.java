package com.ade.step2.data.api;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.ade.step2.data.cache.AppDatabase;
import com.ade.step2.model.api.ApiResponse;
import com.ade.step2.model.api.Product;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public abstract class GetProductList extends BaseApi {

    private final Activity context;
    private AppDatabase db;
    private int cacheType;
    private ApiResponse apiResponse;

    protected GetProductList(final Activity activity, AppDatabase db, int cacheType) {
        this.context = activity;
        this.cacheType = cacheType;
        this.db = db;

        Call<Product> call = apiService.loadProduct();
        apiResponse = new ApiResponse();

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                try {
                    apiResponse.setData(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                    apiResponse.setData(null);
                }

                onFinishRequest(true, apiResponse);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(activity, "Failed getting data from server", Toast.LENGTH_SHORT).show();
                Product product = loadCache();
                if (product != null) {
                    apiResponse.setData(product);
                } else {
                    apiResponse.setData(null);
                }

                onFinishRequest(false, apiResponse);
            }
        });
    }

    private Product loadCache() {
        try {
            JsonObject json = new JsonParser().parse(db.cacheDao().loadCacheById(cacheType).json).getAsJsonObject();
            return new GsonBuilder().create().fromJson(json, Product.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
