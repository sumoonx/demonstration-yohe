/*
 * Copyright (c) 2016. Team Yohe from Southeast University
 * All rights reserved.
 */

package com.yohe.presentation.internal.di.module;

import android.content.Context;

import com.yohe.presentation.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Jeremy Xu on 2016/3/29 21:17
 * E-mail: jeremy_xm@163.com
 */
@Module
public class ApplicationModule {
    private final MyApplication myApplication;

    public ApplicationModule(MyApplication application) {
        myApplication = application;
    }

    @Provides @Singleton
    Context provideContext() {
        return myApplication;
    }
}
