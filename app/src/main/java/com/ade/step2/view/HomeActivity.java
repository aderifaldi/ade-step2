package com.ade.step2.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ade.step2.R;
import com.ade.step2.data.cache.AppDatabase;
import com.ade.step2.helper.Constant;
import com.ade.step2.model.api.ApiResponse;
import com.ade.step2.model.api.Product;
import com.ade.step2.model.local.User;
import com.ade.step2.viewmodel.ProductListVM;

public class HomeActivity extends BaseActivity implements BaseActivity.ApiResponseCallback {

    private TextView username;

    private Intent intent;

    private User user;

    private ProductListVM viewModel;
    private AppDatabase db;

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

        username = findViewById(R.id.username);

        if (user != null) {
            username.setText(user.getUsername());
        }

        viewModel.loadProduuctList(HomeActivity.this, db, Constant.CACHE_PRODUCT);

    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    @Override
    public void loadResponse(boolean success, ApiResponse apiResponse) {
        if (success) {
            Product data = (Product) apiResponse.getData();

            if (data != null) {
                if (data.getStatus().equals("OK")) {
                    for (int i = 0; i < data.getProducts().size(); i++) {

                    }
                }
            }

        }
    }

}
