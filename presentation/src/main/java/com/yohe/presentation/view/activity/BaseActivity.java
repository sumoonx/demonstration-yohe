/*
 * Copyright (c) 2016. Team Yohe from Southeast University
 * All rights reserved.
 */

package com.yohe.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yohe.presentation.MyApplication;
import com.yohe.presentation.internal.di.component.ApplicationComponent;
import com.yohe.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Author: Jeremy Xu on 2016/3/29 21:27
 * E-mail: jeremy_xm@163.com
 */
public class BaseActivity extends AppCompatActivity{
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }



    /**
     * Add a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection
     *
     * @return A {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }
}
