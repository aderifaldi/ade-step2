package com.ade.step2.data.api;

import com.ade.step2.model.api.Product;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aderifaldi on 08/08/2016.
 */
public interface ApiService {

    @GET("products.json")
    Call<Product> loadProduct();

}
