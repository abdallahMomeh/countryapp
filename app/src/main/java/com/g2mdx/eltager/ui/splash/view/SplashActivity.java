package com.g2mdx.eltager.ui.splash.view;

import android.content.Intent;
import android.os.Bundle;

import com.g2mdx.eltager.R;
import com.g2mdx.eltager.ui.base.BaseActivity;
import com.g2mdx.eltager.ui.signup.view.SignupActivity;

import androidx.annotation.Nullable;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startActivity(new Intent(this , SignupActivity.class));

    }


}
