package com.ade.step2.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ade.step2.R;
import com.ade.step2.viewmodel.LoginVM;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginVM viewModel;

    private EditText edt_email, edt_password;
    private RelativeLayout btn_skip_login, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        viewModel = ViewModelProviders.of(this).get(LoginVM.class);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_skip_login = findViewById(R.id.btn_skip_login);
        btn_login = findViewById(R.id.btn_login);

        btn_skip_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Can't skip login fow now!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                String username;
                String password;

                username = edt_email.getText().toString();
                password = edt_password.getText().toString();

                viewModel.checkUser(LoginActivity.this, username, password);

                break;

            default:
                break;
        }
    }
}
