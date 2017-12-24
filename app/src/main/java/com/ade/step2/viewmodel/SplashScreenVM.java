package com.ade.step2.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.os.Handler;

/**
 * Created by aderifaldi on 2017-12-24.
 */

public class SplashScreenVM extends ViewModel {

    public void runSplashDelay(final Activity from, final Class to, int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                from.startActivity(new Intent(from, to));
                from.finish();

            }
        }, delay);
    }

}
