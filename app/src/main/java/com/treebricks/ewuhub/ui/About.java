package com.treebricks.ewuhub.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.treebricks.ewuhub.R;


public class About extends AppCompatActivity {
    ChromeCustomTab chromeCustomTab;
    TextView version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        chromeCustomTab = new ChromeCustomTab(getApplicationContext(), About.this);
        version = (TextView) findViewById(R.id.version_info);

        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            String versionName = "V " + info.versionName;
            version.setText(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void aboutDeveloperOnClickHandler(View view) {


        new MaterialDialog.Builder(this)
                .title(R.string.app_name)
                .content(R.string.treebricks_description)
                .positiveText("Treebricks.com")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        chromeCustomTab.runOnCustomTab("http://treebricks.com/");
                    }
                })
                .negativeText("Cancel")
                .iconRes(R.drawable.treebricks_dev_logo)
                .maxIconSize(120)
                .show();


    }

    public void privacyPolicyOnClickHandler(View view)
    {
        new MaterialDialog.Builder(this)
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
                .iconRes(R.drawable.treebricks_dev_logo)
                .maxIconSize(120)
                .show();


    }

    public void creditsOnClickHandler(View view)
    {

        new MaterialDialog.Builder(this)
                .title("Credits")
                .content(R.string.credits)
                .positiveText("Ok")
                .negativeText("Cancel")
                .iconRes(R.drawable.treebricks_dev_logo)
                .maxIconSize(120)
                .show();


    }


    public void termsOnClickHandler(View view)
    {

        new MaterialDialog.Builder(this)
                .title("Terms of Use")
                .content(R.string.terms_of_use)
                .positiveText("Ok")
                .negativeText("Cancel")
                .iconRes(R.drawable.treebricks_dev_logo)
                .maxIconSize(120)
                .show();

    }
}
