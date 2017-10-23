package dev.brian.materialbrian.utils.common;

import android.widget.Toast;

import dev.brian.materialbrian.BaseApplication;


/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at 2017/10/23
 * Description: Toast工具类
 */
public class ToastUtils {
    public static void showShort(String msg) {
        Toast.makeText(BaseApplication.getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(BaseApplication.getApplication(), msg, Toast.LENGTH_LONG).show();
    }
}
