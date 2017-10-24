package dev.brian.materialbrian.utils.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import dev.brian.materialbrian.BaseApplication;
import dev.brian.materialbrian.R;
import dev.brian.materialbrian.ui.activity.BaseActivity;


/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at 2017/10/23
 * Description: UI工具类
 */
public class UIUtils {

    public static Context getContext() {
        return BaseApplication.getApplication();
    }

    /**
     * 页面跳转
     *
     * @param intent
     */
    public static void startActivity(Intent intent) {
        // 如果不在activity里去打开activity  需要指定任务栈  需要设置标签
/*        if (BaseActivity.activity == null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            BaseActivity.activity.startActivity(intent);
        }*/
    }

    /**
     * 分享
     *
     * @param context
     * @param content 分享内容
     * @param uri     分享图片uri
     */
    public static void share(Context context, String content, Uri uri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        if (uri != null) {
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/*");
            //当用户选择短信时使用sms_body取得文字
            shareIntent.putExtra("sms_body", content);
        } else {
            shareIntent.setType("text/plain");
        }
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_dialog_title)));
    }
}
