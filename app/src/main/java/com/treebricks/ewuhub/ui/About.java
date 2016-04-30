package com.treebricks.ewuhub.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.treebricks.ewuhub.R;

public class About extends AppCompatActivity {
    ChromeCustomTab chromeCustomTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        chromeCustomTab = new ChromeCustomTab(getApplicationContext(), About.this);

    }

    public void aboutDeveloperOnClickHandler(View view) {

        String web = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<body>\n" +
                "<p><b>treebricks</b> is an application studio build by <b>EWU</b> students.\n" +
                "EwuHub is treebricks first application. We worked" +
                " a long time to bring this application before you. treebricks hope " +
                "users will love this application.</p>\n" +
                "<h2>treebricks</h2>\n" +
                "\t</body>\n" +
                "</html>";
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("About Developer");

        WebView wv = new WebView(this);
        wv.loadData(web,"text/html", "UTF-8");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();

    }

    public void officialWebsiteOnClickHandler(View view)
    {
        chromeCustomTab.runOnCustomTab("http://www.treebricks.com");

    }

    public void privacyPolicyOnClickHandler(View view)
    {
        String web = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"UTF-8\">\n" +
                "\t\t<title></title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<h2>Privacy Policy</h2>\n" +
                "\t\t<hr>\n" +
                "\t\t<p>EwuHub does not obtain any kind of personal information from your device and also when"+
                " you use this application. The only information we get about you(Device Model, Android Version)"+
                " is from google, when you downloaded this app from Google Play Store. To know how google collect your information"+
                " goto <a href=\"https://play.google.com/about/play-terms.html\">Play Store Privacy Policy</a> to "+
                "know details about Google Play Store Privacy Policy. EwuSpirit server(On East West University) has "+
                "some certain feature which allow anyone to get anyone's information like credits information, result,"+
                " class routine etc. EwuHub and treebricks is not responsible for such kind information because EwuHub's"+
                " information is based on East West University website and its server. User are responsible what they"+
                " are doing with this Application.</p>\n" +
                "\t</body>\n" +
                "</html>";
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Privacy Policy");

        WebView wv = new WebView(this);
        wv.loadData(web,"text/html", "UTF-8");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();


    }

    public void creditsOnClickHandler(View view)
    {
        String web = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<body>\n" +
                "\t\t<p><b>treebricks</b> is very happy bring this application to main stream. This can not be done without \n" +
                "\t\tthe help of many people.</p>\n" +
                "\t\t<p>First we like to thank our sir <b>K.M. Imtiaz Ud-Din</b>(Senior Lecterur, Dept. of CSE) for his advice and inspiration. Without his inspiration this application can not here today.</p>\n" +
                "\t\t<p>We specially thanks <b>Sathil Islam</b> for his beautiful photograph.</p>\n" +
                "\t\t<p>We also greatful to our friends who also help us to improve our algorithm for the application.</p>\n" +
                "\t\t<p>We also greatful to our beta and alpha tester who advice us how we can better this application.</p>\n" +
                "\t\t<p>Thanks to our friends who helped us to update the database. Without them we could not bring the update of the database so quickly.</p>\n" +
                "\t\t<p>We are also very greatful who are using this application. Don't bother to feedback about this application. This is for you. With your help we can make this application even better..</p>\n" +
                "\t</body>\n" +
                "</html>";


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Credits");

        WebView wv = new WebView(this);
        wv.loadData(web,"text/html", "UTF-8");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();


    }


    public void termsOnClickHandler(View view)
    {
        String web = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"UTF-8\">\n" +
                "\t\t<title></title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<h2>Terms of Use</h2>\n" +
                "\t\t<hr>\n" +
                "\t\t<p>All the information of EwuHub is taken from <b>www.ewubd.edu</b>"+
                " and it is the private property of <b>East West University</b>."+
                " They have all rights to change any information when they want. <b>EwuSpirit</b> "+
                " is private server of <b>East West University</b>. User have to take any kind of"+
                " responsibilities for using EwuSpirit. The privacy policy of EwuSpirit is not will "+
                " not applicable on <b>EwuHub</b>. User are advised to read the privacy policy of EwuHub.</p> \n" +
                "\t</body>\n" +
                "</html>";

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Terms of Use");

        WebView wv = new WebView(this);
        wv.loadData(web,"text/html", "UTF-8");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
