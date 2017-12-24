package com.ade.step2.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.ade.step2.helper.Constant;
import com.ade.step2.model.local.User;
import com.ade.step2.view.HomeActivity;

/**
 * Created by aderifaldi on 2017-12-24.
 */

public class LoginVM extends ViewModel {

    public void checkUser(final Activity activity, String username, String password) {

        String loginMessage;

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(activity, "Username must field!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(activity, "Password must field!", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User();

            if (username.equals(Constant.TRUE_USERNAME) && password.equals(Constant.TRUE_PASSWORD)) {
                user.setUsername(Constant.TRUE_USERNAME);
                loginMessage = "Login as true user";
            } else {
                user.setUsername("Guest");
                loginMessage = "Login as guest";
            }

            Toast.makeText(activity, loginMessage, Toast.LENGTH_SHORT).show();

            activity.startActivity(new Intent(activity, HomeActivity.class)
                    .putExtra("user", user));
        }

    }

}
