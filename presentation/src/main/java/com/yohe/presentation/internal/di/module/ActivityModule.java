/*
 * Copyright (c) 2016. Team Yohe from Southeast University
 * All rights reserved.
 */

package com.yohe.presentation.internal.di.module;

import android.app.Activity;

import com.yohe.presentation.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Author: Jeremy Xu on 2016/3/29 21:43
 * E-mail: jeremy_xm@163.com
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     *
     * @return The activity.
     */
    @Provides @PerActivity
    Activity activity() {
        return activity;
    }
}
