package com.treebricks.ewuhub.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.utility.ChromeCustomTab;

public class AboutFragment extends PreferenceFragment {

  ChromeCustomTab chromeCustomTab;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.about);

    chromeCustomTab = new ChromeCustomTab(getActivity(), getActivity());

    Preference customPref = findPreference("app");

    PackageManager manager = getActivity().getPackageManager();
    PackageInfo info;
    try {
      info = manager.getPackageInfo(getActivity().getPackageName(), 0);
      customPref.setSummary(info.versionName);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    String key = preference.getKey();
    switch (key)
    {
      case "app":{
        new MaterialDialog.Builder(getActivity())
            .title("EwuHub")
            .content("EwuHub is a non-profit application for the students of EWU by the students of EWU."
                + " We are all proud to be Ewuians(just kidding).")
            .positiveText("Ok")
            .negativeText("Cancel")
            .iconRes(R.mipmap.ic_launcher)
            .maxIconSize(120)
            .show();

        break;
      }

      case "developer":{

        new MaterialDialog.Builder(getActivity())
            .title("treebricks")
            .content(R.string.treebricks_description)
            .positiveText("treebricks Website")
            .onPositive(new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                chromeCustomTab.runOnCustomTab("https://treebricks.github.io/");
              }
            })
            .negativeText("Cancel")
            .iconRes(R.drawable.treebricks_logo)
            .maxIconSize(120)
            .show();

        break;
      }
      case "privacy":{

        new MaterialDialog.Builder(getActivity())
            .title("Privacy Policy")
            .content(R.string.privacy_policy)
            .positiveText("Play Store Privacy Policy")
            .onPositive(new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                chromeCustomTab.runOnCustomTab("https://play.google.com/about/play-terms.html");
              }
            })
            .negativeText("Cancel")
            .iconRes(R.drawable.privacy)
            .maxIconSize(120)
            .show();

        break;
      }
      case "terms":{

        new MaterialDialog.Builder(getActivity())
            .title("Terms of Use")
            .content(R.string.terms_of_use)
            .positiveText("Ok")
            .negativeText("Cancel")
            .iconRes(R.drawable.terms)
            .maxIconSize(120)
            .show();

        break;
      }
      case "credit":{
        new MaterialDialog.Builder(getActivity())
            .title("Credits")
            .content(R.string.credits)
            .positiveText("Ok")
            .negativeText("Cancel")
            .iconRes(R.drawable.credit)
            .maxIconSize(120)
            .show();

        break;
      }
      case "facebook":{
        try {
          Context context = getActivity();
          context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
          Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/182185502168098"));
          startActivity(facebook);
        } catch (Exception e) {
          chromeCustomTab.runOnCustomTab("https://www.facebook.com/treebricks");
        }
        break;
      }
    }

    return super.onPreferenceTreeClick(preferenceScreen, preference);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    chromeCustomTab.serviceUnbind();
  }
}
