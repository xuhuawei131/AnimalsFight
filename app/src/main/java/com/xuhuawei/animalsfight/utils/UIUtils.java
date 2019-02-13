package com.xuhuawei.animalsfight.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Looper;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xuhuawei.animalsfight.BaseApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UIUtils {
    public static int getWindowWidth(Activity activity) {
        if (activity == null) {
            return 0;
        } else {
            DisplayMetrics metric = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
            return metric.widthPixels;
        }
    }

    public static int getWindowHeight(Activity activity) {
        if (activity == null) {
            return 0;
        } else {
            DisplayMetrics metric = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
            return metric.heightPixels;
        }
    }

    public static DisplayMetrics getWindow(Activity activity) {
        if (activity == null) {
            return null;
        } else {
            DisplayMetrics metric = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
            return metric;
        }
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int dip2px(float dpValue) {
        WindowManager wm = (WindowManager) BaseApp.getAppContext().getSystemService("window");
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        float scale = dm.density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int px2dip(float pxValue) {
        WindowManager wm = (WindowManager)BaseApp.getAppContext().getSystemService("window");
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        float scale = dm.density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static void showInputMethod(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService("input_method");
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.showSoftInput(activity.getCurrentFocus(), 0);
            }

        }
    }

    public static void hideInputMethod(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService("input_method");
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }

        }
    }

    public static void setInputMethodVisibility(Context context, EditText editText, boolean visible) {
        if (context != null && editText != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService("input_method");
            if (visible) {
                imm.toggleSoftInput(0, 2);
            } else {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 2);
            }

            editText.setTag(visible);
        }
    }

    public static void setScreenBrightness(Activity activity, float brightness) {
        if (activity != null) {
            Window window = activity.getWindow();
            window.getAttributes().screenBrightness = brightness;
            window.setAttributes(window.getAttributes());
        }
    }

    public static float getScreenBrightness(Activity activity) {
        return activity == null ? 1.0F : activity.getWindow().getAttributes().screenBrightness;
    }

    public boolean isLauncherOnTop(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ArrayList<String> launcherPackageNames = new ArrayList();
        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 65536);
        Iterator var5 = resolveInfoList.iterator();

        while(var5.hasNext()) {
            ResolveInfo info = (ResolveInfo)var5.next();
            launcherPackageNames.add(info.activityInfo.packageName);
        }

        ActivityManager am = (ActivityManager)context.getSystemService("activity");
        Iterator var10 = am.getRunningTasks(1).iterator();

        while(var10.hasNext()) {
            ActivityManager.RunningTaskInfo t = (ActivityManager.RunningTaskInfo)var10.next();
            if (t != null && t.numRunning > 0) {
                ComponentName componentName = t.baseActivity;
                if (componentName != null && launcherPackageNames.contains(componentName.getPackageName())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isInUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static <T extends View> T findViewById(Activity activity, int id) {
        return activity.findViewById(id);
    }

    public static <T extends View> T findViewById(View view, int id) {
        return view.findViewById(id);
    }

    @SuppressLint({"MissingPermission"})
    public static void doVibrator() {
        Vibrator vibrator = (Vibrator)BaseApp.getAppContext().getSystemService("vibrator");
        vibrator.vibrate(200L);
    }
}
