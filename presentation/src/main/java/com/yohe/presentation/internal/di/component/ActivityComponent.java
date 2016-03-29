/*
 * Copyright (c) 2016. Team Yohe from Southeast University
 * All rights reserved.
 */

package com.yohe.presentation.internal.di.component;

import android.app.Activity;
import com.yohe.presentation.internal.di.PerActivity;
import dagger.Component;

/**
 * Author: Jeremy Xu on 2016/3/29 21:55
 * E-mail: jeremy_xm@163.com
 */
@PerActivity
@Component(dependencies = ActivityComponent.class, modules = ActivityComponent.class)
public interface ActivityComponent {
    //Exposed to sub-graph
    Activity activity();
}
