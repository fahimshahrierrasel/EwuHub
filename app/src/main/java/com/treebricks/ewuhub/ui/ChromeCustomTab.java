package com.treebricks.ewuhub.ui;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.treebricks.ewuhub.R;
import java.util.List;

public class ChromeCustomTab
{
    public static String PACKAGE_NAME = "com.android.chrome";
    private CustomTabsClient mClient;
    Context context;
    Uri uri;
    Activity activity;
    public ChromeCustomTab(Context context, Activity activity)
    {
        this.context = context;
        this.activity = activity;
        uri = null;
        CustomTabsServiceConnection mConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                mClient = customTabsClient;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mClient = null;
            }
        };
        CustomTabsClient.bindCustomTabsService(context, PACKAGE_NAME, mConnection);
        if (mClient != null) {
            mClient.warmup(0);
            CustomTabsSession customTabsSession = getSession();
            customTabsSession.mayLaunchUrl(Uri.parse("http://www.ewubd.edu"), null, null);
            customTabsSession.mayLaunchUrl(Uri.parse("http://lib.ewubd.edu/"), null, null);
        }
    }

    private CustomTabsSession getSession() {
        return mClient.newSession(new CustomTabsCallback() {
            @Override
            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                super.onNavigationEvent(navigationEvent, extras);
            }
        });
    }
    public void runOnCustomTab(String url)
    {

        uri = Uri.parse(url);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_arrow_back);
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setShowTitle(true)
                .setCloseButtonIcon(icon)
                .build();
        customTabsIntent.intent.setData(uri);

        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(customTabsIntent.intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolveInfo : resolveInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            if (TextUtils.equals(packageName, PACKAGE_NAME))
                customTabsIntent.intent.setPackage(PACKAGE_NAME);
        }

        customTabsIntent.launchUrl(activity, uri);
    }
}
