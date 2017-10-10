package dev.brian.materialbrian;

import android.app.Application;

import java.util.List;

import dev.brian.materialbrian.ui.activity.BaseActivity;


/**
 * 作者: jiayi.zhang
 * 时间: 2017/10/9
 * 描述: 主程序
 */

public class BaseApplication extends Application {
    public static final String TAG = "BaseApplication";
    public static final boolean DEBUG = true;
    private static BaseApplication application;
    private static int mainTid;

    /**
     * Activity集合，来管理所有的Activity
     */
    private static List<BaseActivity> activities;


}
