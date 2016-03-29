/*
 * Copyright (c) 2016. Team Yohe from Southeast University
 * All rights reserved.
 */

package com.yohe.presentation;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.yohe.BuildConfig;
import com.yohe.presentation.internal.di.component.ApplicationComponent;
import com.yohe.presentation.internal.di.module.ApplicationModule;

/**
 * Author: Jeremy Xu on 2016/3/29 21:20
 * E-mail: jeremy_xm@163.com
 */
public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDection();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initializeLeakDection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
