package com.treebricks.ewuhub.utility;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by fahim on 10/22/16.
 */

public class AppInstalled
{
    Context context;

    public AppInstalled(Context context)
    {
        this.context = context;
    }

    // Only for chrome.
    public boolean isChromeEnabled(String packageName)
    {
        boolean result = false;
        if(isAppInstalled(packageName) && isAppEnabled(packageName) && (appVersion(packageName) >= 45))
        {
            result = true;
        }
        return result;
    }

    private boolean isAppInstalled(String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
    private boolean isAppEnabled(String packageName)
    {
        boolean appStatus = false;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(packageName, 0);
            appStatus = ai.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appStatus;
    }

    //Only for chrome
    private int appVersion(String packageName)
    {
        int versionCode = 38;
        String versionNumber;
        PackageManager pm = context.getPackageManager();
        PackageInfo pInfo = null;
        try {
            pInfo = pm.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo != null) {
            String version = pInfo.versionName;
            versionNumber = version.substring(0,2);
            if(isNumeric(versionNumber))
            {
                versionCode = Integer.parseInt(versionNumber);
            }
        }
        return versionCode;
    }

    private static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}
