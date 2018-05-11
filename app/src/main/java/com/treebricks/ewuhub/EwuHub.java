package com.treebricks.ewuhub;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

public class EwuHub extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        LogUtils.d("On Create EwuHub");
    }
}
