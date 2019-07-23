package com.g2mdx.eltager.ui.mainscreen.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.g2mdx.eltager.R;
import com.g2mdx.eltager.ui.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MainscreenActivity extends BaseActivity {

    Toolbar toolbar;
    DrawerLayout drawer;
    RecyclerView mFramRV;
    TextView mOnFramTV;
    ImageView plusFramIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    void initViews() {
        plusFramIcon = findViewById(R.id.plusFramIcon);
        mOnFramTV = findViewById(R.id.mOnFramTV);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.openDrawer,R.string.closeDrawer);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        mFramRV = findViewById(R.id.mFramRV);

    }



}


