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
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
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

        String description = "treebricks is an application studio build by EWU students. " +
                "EwuHub is treebricks first application. We worked" +
                "a long time to bring this application before you. treebricks hope " +
                "users will love this application.\t\ntreebricks";

        new MaterialStyledDialog(this)
                .setTitle("Awesome!")
                .setDescription(description)
                .setStyle(Style.HEADER_WITH_ICON)
                .setHeaderColor(R.color.blue_grey)
                .setIcon(R.drawable.treebricks_dev_logo)
                .withDialogAnimation(true)
                .setCancelable(true)
                .setPositive("treebricks.com", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        chromeCustomTab.runOnCustomTab("http://www.treebricks.com");
                    }
                })
                .setNegative("Ok", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }

    public void privacyPolicyOnClickHandler(View view)
    {
        String description = "EwuHub does not obtain any kind of personal information from your device and also when" +
                " you use this application. The only information we get about you(Device Model, Android Version)" +
                " is from google, when you downloaded this app from Google Play Store. To know how google collect your information" +
                " goto Play Store Privacy Policy to know details about Google Play Store Privacy Policy. EwuSpirit server" +
                "(On East West University) has some certain feature which allow anyone to get anyone's information like credits" +
                " information, result, class routine etc. EwuHub and treebricks is not responsible for such kind information because" +
                " EwuHub's information is based on East West University website and its server. User are responsible what they" +
                " are doing with this Application.";

        new MaterialStyledDialog(this)
                .setTitle("Privacy Policy")
                .setDescription(description)
                .setStyle(Style.HEADER_WITH_TITLE)
                .setHeaderColor(R.color.red)
                .withDialogAnimation(true)
                .setCancelable(true)
                .setPositive("Play Store Privacy Policy", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        chromeCustomTab.runOnCustomTab("https://play.google.com/about/play-terms.html");
                    }
                })
                .setNegative("OK", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })

                .show();


    }

    public void creditsOnClickHandler(View view)
    {
        String description = "treebricks is very happy bring this application to main stream. This can not be done without " +
                "the help of many people. First we like to thank our sir K.M. Imtiaz Ud-Din" +
                " for his advice and inspiration. Without his inspiration this application can not here today. We specially " +
                "thanks Sathil Islam for his beautiful photograph. We also greatful to our friends who also help us to improve " +
                "our algorithm for the application. We also greatful to our beta and alpha tester who advice us how we can " +
                "better this application. Thanks to our friends who helped us to update the database. Without them we could not " +
                "bring the update of the database so quickly. We are also very greatful who are using this application. " +
                "Don't bother to feedback about this application. This is for you. With your help we can make this application " +
                "even better..";

        new MaterialStyledDialog(this)
                .setTitle("Credits")
                .setDescription(description)
                .setStyle(Style.HEADER_WITH_TITLE)
                .setHeaderColor(R.color.green)
                .withDialogAnimation(true)
                .setCancelable(true)
                .setNegative("OK", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .setScrollable(true)
                .show();


    }


    public void termsOnClickHandler(View view)
    {
        String description = "All the information of EwuHub is taken from <www.ewubd.edu> and it is the private property of East West University." +
                " They have all rights to change any information when they want. EwuSpirit is private server of East West University. " +
                "User have to take any kind of  responsibilities for using EwuSpirit. The privacy policy of EwuSpirit is not will not" +
                " applicable on EwuHub. Users are advised to read the privacy policy of EwuHub.";

        new MaterialStyledDialog(this)
                .setTitle("Terms of Use")
                .setDescription(description)
                .setStyle(Style.HEADER_WITH_TITLE)
                .setHeaderColor(R.color.teal)
                .withDialogAnimation(true)
                .setCancelable(true)
                .setNegative("OK", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }
}
