package com.ade.step2.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ade.step2.R;
import com.ade.step2.data.cache.AppDatabase;
import com.ade.step2.data.cache.CacheManager;
import com.ade.step2.helper.Constant;
import com.ade.step2.model.api.ApiResponse;
import com.ade.step2.model.api.Product;
import com.ade.step2.model.local.User;
import com.ade.step2.view.adapter.ProductListAdapter;
import com.ade.step2.viewmodel.ProductListVM;
import com.google.gson.Gson;

public class HomeActivity extends BaseActivity implements BaseActivity.ApiResponseCallback {

    private RecyclerView listProduct;

    private Intent intent;

    private TextView username;

    private User user;

    private ProductListVM viewModel;
    private AppDatabase db;

    private LinearLayoutManager linearLayoutManager;
    private ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        intent = getIntent();
        if (intent != null) {
            user = (User) intent.getExtras().getSerializable("user");
        }

        viewModel = ViewModelProviders.of(this).get(ProductListVM.class);
        db = AppDatabase.getDatabase(getApplicationContext());

        listProduct = findViewById(R.id.list_product);
        username = findViewById(R.id.username);

        username.setText("Login as " + user.getUsername());

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new ProductListAdapter(this);

        listProduct.setLayoutManager(linearLayoutManager);
        listProduct.setAdapter(adapter);

        viewModel.loadProductList(HomeActivity.this, db, Constant.CACHE_PRODUCT);

    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    @Override
    public void loadResponse(boolean success, ApiResponse apiResponse) {
        Product data = (Product) apiResponse.getData();

        if (success) {
            CacheManager.storeCache(db, Constant.CACHE_PRODUCT, new Gson().toJson(data));
        }

        if (data != null) {
            if (data.getStatus().equals("OK")) {
                for (int i = 0; i < data.getProducts().size(); i++) {
                    adapter.getData().add(data.getProducts().get(i));
                    adapter.notifyItemInserted(adapter.getData().size() - 1);
                }
                adapter.notifyDataSetChanged();
            }
        }

    }

}
