package com.treebricks.ewuhub.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.treebricks.ewuhub.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void aboutDeveloperOnClickHandler(View view) {

        String web = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<body>\n" +
                "\t\t<p><b>treebricks</b> is an application studio created by some <b>EWU</b> students.\n" +
                "\t\tThe developers of the <b>treebricks</b> love to be anonymous. <b>treebricks</b> loves to solve the problem\n" +
                "\t\t we everyday face. We also love the problem solver. Anyone can solve any problem. You are not alone with any particular problem. There are thousands of people facing the same problem you face everyday. But if you solve the problem then thousands of people will be benifited. <b>treebricks</b> loves problem solver and who try to solve.</p>\n" +
                "\t\t<h2>Goto our website to know about us more. </h2>\n" +
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
        String url = "http://www.treebricks.twomini.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void privacyPolicyOnClickHandler(View view)
    {
        String web = "<h2 style=\"text-align: center;\">PRIVACY POLICY</h2> \n" +
                "<p>This privacy policy governs your use of the software application EwuHub (\"Application\") for mobile devices that was created by TreeBricks. The Application is designed for the students of the East West University, Bangladesh. This application will help on advising, getting result, academic calendar and other stuff of the university.</p> \n" +
                "<h4> </h4> \n" +
                "<h4>What information does the Application obtain and how is it used?</h4> \n" +
                "<p><strong>User Provided Information</strong></p> \n" +
                "<p>The Application obtains the information you provide when you download and register the Application. Registration with us is optional. However, please keep in mind that you may not be able to use some of the features offered by the Application unless you register with us.</p> \n" +
                "<p>When you register with us and use the Application, you generally provide (a) your name, email address, age, user name, password and other registration information; (b) transaction-related information, such as when you make purchases, respond to any offers, or download or use applications from us; (c) information you provide us when you contact us for help; (d) credit card information for purchase and use of the Application, and; (e) information you enter into our system when using the Application, such as contact information and project management information.</p> \n" +
                "<p>We may also use the information you provided us to contact your from time to time to provide you with important information, required notices and marketing promotions.</p> \n" +
                "<p><strong>Automatically Collected Information</strong></p> \n" +
                "<p>In addition, the Application may collect certain information automatically, including, but not limited to, the type of mobile device you use, your mobile devices unique device ID, the IP address of your mobile device, your mobile operating system, the type of mobile Internet browsers you use, and information about the way you use the Application.</p> \n" +
                "<h4> </h4> \n" +
                "<h4>Does the Application collect precise real time location information of the device?</h4> \n" +
                "<p>This Application does not collect precise information about the location of your mobile device.</p> \n" +
                "<h4>Do third parties see and/or have access to information obtained by the Application?</h4> \n" +
                "<p>Only aggregated, anonymized data is periodically transmitted to external services to help us improve the Application and our service. We will share your information with third parties only in the ways that are described in this privacy statement.</p> \n" +
                "<p>We may disclose User Provided and Automatically Collected Information:</p> \n" +
                "<ul> \n" +
                " <li> <p>as required by law, such as to comply with a subpoena, or similar legal process;</p> </li> \n" +
                " <li> <p>when we believe in good faith that disclosure is necessary to protect our rights, protect your safety or the safety of others, investigate fraud, or respond to a government request;</p> </li> \n" +
                " <li> <p>with our trusted services providers who work on our behalf, do not have an independent use of the information we disclose to them, and have agreed to adhere to the rules set forth in this privacy statement.</p> </li> \n" +
                " <li> <p>if TreeBricks is involved in a merger, acquisition, or sale of all or a portion of its assets, you will be notified via email and/or a prominent notice on our Web site of any change in ownership or uses of this information, as well as any choices you may have regarding this information.</p> </li> \n" +
                "</ul> \n" +
                "<h4>What are my opt-out rights?</h4> \n" +
                "<p>You can stop all collection of information by the Application easily by uninstalling the Application. You may use the standard uninstall processes as may be available as part of your mobile device or via the mobile application marketplace or network. You can also request to opt-out via email, at privacy@treebricks.com.</p> \n" +
                "<h4><strong>Data Retention Policy, Managing Your Information</strong></h4> \n" +
                "<p>We will retain User Provided data for as long as you use the Application and for a reasonable time thereafter. We will retain Automatically Collected information for up to 24 months and thereafter may store it in aggregate. If you’d like us to delete User Provided Data that you have provided via the Application, please contact us at privacy@treebricks.comand we will respond in a reasonable time. Please note that some or all of the User Provided Data may be required in order for the Application to function properly.</p> \n" +
                "<h4><strong>Children</strong></h4> \n" +
                "<p>We do not use the Application to knowingly solicit data from or market to children under the age of 13. If a parent or guardian becomes aware that his or her child has provided us with information without their consent, he or she should contact us at privacy@treebricks.com. We will delete such information from our files within a reasonable time.</p> \n" +
                "<h4> </h4> \n" +
                "<h4><strong>Security</strong></h4> \n" +
                "<p>We are concerned about safeguarding the confidentiality of your information. We provide physical, electronic, and procedural safeguards to protect information we process and maintain. For example, we limit access to this information to authorized employees and contractors who need to know that information in order to operate, develop or improve our Application. Please be aware that, although we endeavor provide reasonable security for information we process and maintain, no security system can prevent all potential security breaches.</p> \n" +
                "<h4><strong>Changes</strong></h4> \n" +
                "<p>This Privacy Policy may be updated from time to time for any reason. We will notify you of any changes to our Privacy Policy by posting the new Privacy Policy ppolicy.treebricks.com and you need to check the changes by yourself. You are advised to consult this Privacy Policy regularly for any changes, as continued use is deemed approval of all changes. You can check the history of this policy by ppolicy.treebricks.com.</p> \n" +
                "<h4><strong>Your Consent</strong></h4> \n" +
                "<p>By using the Application, you are consenting to our processing of your information as set forth in this Privacy Policy now and as amended by us. &quot;Processing,” means using cookies on a computer/hand held device or using or touching information in any way, including, but not limited to, collecting, storing, deleting, using, combining and disclosing information, all of which activities will take place in the Bangladesh. If you reside outside the Bangladesh your information will be transferred, processed and stored there under Bangladesh privacy standards.</p> \n" +
                "<h4>Contact us</h4> \n" +
                "<p>If you have any questions regarding privacy while using the Application, or have questions about our practices, please contact us via email at privacy@treebricks.com.</p>";

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
                "\t\t<p>First we like to thank our sir <b>K.M. Imtiaz Ud-Din</b>(Senior Lecterur, Dept. of CSE) for his advice and inspiration. Without his inspiration this application can not here today. Because he taught us how to do anything if you has confidence.</p>\n" +
                "\t\t<p>We specially thanks <b>Sathil Islam</b> for his beautiful photograph.</p>\n" +
                "\t\t<p>We also greatful to our friends who also help us to imporve our algorithm for the application.</p>\n" +
                "\t\t<p>We also greatful to our beta and alpha tester who advice us how we can better this application.</p>\n" +
                "\t\t<p>We are also very greatful who are using this application. Don't bother to feedback about this application. This is for you. You have to act to become it better.</p>\n" +
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


}
