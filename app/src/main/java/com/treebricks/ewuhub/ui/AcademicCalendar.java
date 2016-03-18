package com.treebricks.ewuhub.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.view.MyAdapter;

public class AcademicCalendar extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String undergraduate = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/undergraduate.html";
        final String undergraduatePharmacy = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/pharmacyundergraduate.html";
        final String graduate = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/graduate.html";
        final String graduatePharmacy = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/pharmacygraduate.html";

        final WebView calendarWebView = (WebView) findViewById(R.id.calendar_web_view);
        calendarWebView.setWebViewClient(new WebViewClient());
        calendarWebView.loadUrl(undergraduate);


        // Fab menu

        final FloatingActionMenu fabmenu = (FloatingActionMenu) findViewById(R.id.calendar_fab_menu);


        final com.github.clans.fab.FloatingActionButton undergraduateFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.undergraduate);
        undergraduateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fabmenu.close(true);
                calendarWebView.loadUrl(undergraduate);
            }
        });

        final com.github.clans.fab.FloatingActionButton pharmacyUndergraduateFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.pharmacyundergraduate);
        pharmacyUndergraduateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fabmenu.close(true);
                calendarWebView.loadUrl(undergraduatePharmacy);
            }
        });

        final com.github.clans.fab.FloatingActionButton GraduateFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.graduate);
        GraduateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fabmenu.close(true);
                calendarWebView.loadUrl(graduate);
            }
        });

        final com.github.clans.fab.FloatingActionButton pharmacyGraduateFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.pharmacygraduate);
        pharmacyGraduateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fabmenu.close(true);
                calendarWebView.loadUrl(graduatePharmacy);
            }
        });
        //


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
