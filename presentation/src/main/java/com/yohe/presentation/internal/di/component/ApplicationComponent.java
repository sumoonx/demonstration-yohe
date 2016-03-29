/*
 * Copyright (c) 2016. Team Yohe from Southeast University
 * All rights reserved.
 */

package com.yohe.presentation.internal.di.component;

import android.content.Context;

import com.yohe.presentation.view.activity.BaseActivity;

/**
 * Author: Jeremy Xu on 2016/3/29 21:22
 * E-mail: jeremy_xm@163.com
 */
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graph
    Context context();
}
