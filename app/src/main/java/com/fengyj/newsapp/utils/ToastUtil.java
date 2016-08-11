package com.fengyj.newsapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Author: fengyj
 * Date: 2016-08-10
 * Time: 14:21
 * Description:工具类
 */
public class ToastUtil {

    private static Toast toast;

    public static void show(Context context, int text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void show(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
